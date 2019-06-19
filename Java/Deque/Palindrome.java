public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ch = new LinkedListDeque<Character>();
        for (int i =0; i<word.length(); i++) {
            char iWord = word.charAt(i);
            ch.addLast(iWord);
        }
        return ch;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }

        Deque<Character> ch = wordToDeque(word);
        for(int i =0; i<length/2;i++) {
            char a = ch.removeFirst();
            char b = ch.removeLast();
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }

        Deque<Character> ch = wordToDeque(word);
        for(int i =0; i<length/2;i++) {
            if (!cc.equalChars(ch.removeFirst(), ch.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
