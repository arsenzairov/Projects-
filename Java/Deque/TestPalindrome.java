import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertTrue(palindrome.isPalindrome("abccba"));
        assertFalse(palindrome.isPalindrome("google"));

    }

    @Test
    public void testIsPalindromeByOne() {
        CharacterComparator a = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",a));
        assertTrue(palindrome.isPalindrome("kzabyl",a));
        assertFalse(palindrome.isPalindrome("hzabyl",a));
    }

}
