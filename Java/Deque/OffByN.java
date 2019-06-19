public class OffByN implements CharacterComparator {

    public int n;

    public OffByN(int N) {
        this.n = N;
    }

    public boolean equalChars(char x, char y) {
        int num1 = x - '0';
        int num2 = y - '0';

        if (num1 - num2 == n || num1 - num2 == -n) {
            return true;
        }
        return false;
    }


}
