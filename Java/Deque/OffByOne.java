
public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int num1 = x - '0';
        int num2 = y - '0';

        if (num1 - num2 == 1 || num1 - num2 == -1) {
            return true;
        }
        return false;
    }
}
