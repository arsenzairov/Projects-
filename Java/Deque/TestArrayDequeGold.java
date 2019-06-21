import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {

    public String selectRandomMethod(int num) {



        switch(num) {
            case 1:
                return "addFirst";
            case 2:
                return "addLast";
            case 3:
                return "isEmpty";
            case 4:
                return "size:int";
            case 5:
                return "printDeque:void";
            case 6:
                return "removeFirst:T";
            case 7:
                return "removeLast:T";
            case 8:
                return "get(int index)";
            default:
                return "";
        }
    }




    @Test
    public void test2() {
        ArrayDeque<Integer> sad1 = new ArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();
        int count = 0;
        String message = "";

        for (int i =0; i<50000; i++) {
            int randomNum = StdRandom.uniform(8);

            if (randomNum == 0) {
                sad1.addFirst(i);
                good1.addFirst(i);
                message += "addFirst("+i+")\n";
            } else if (randomNum == 1) {
                sad1.addLast(i);
                good1.addLast(i);
                message += "addLast("+i+")\n";
            } else if (randomNum == 3) {
                boolean expected = good1.isEmpty();
                boolean actual = sad1.isEmpty();
                message += "isEmpty()\n";
                assertEquals(message, expected,actual);
            } else if (randomNum == 4) {
                int expected = good1.size();
                int actual = sad1.size();
                message += "size()\n";
                assertEquals(message,expected,actual);
            } else if (randomNum == 5) {
                if (good1.isEmpty()) {
                    good1.addLast(i);
                    sad1.addLast(i);
                }
                Integer expected = good1.removeFirst();
                Integer actual = sad1.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message,expected,actual);
            }else if (randomNum == 6) {
                if (good1.isEmpty()) {
                    good1.addLast(i);
                    sad1.addLast(i);
                }
                Integer expected = good1.removeLast();
                Integer actual = sad1.removeLast();
                message += "removeLast()\n";
                assertEquals(message,expected,actual);
            } else if (randomNum == 7){
                if (!good1.isEmpty()) {
                    int size = good1.size()/2;
                    Integer expected = good1.get(size/2);
                    Integer actual = sad1.get(size/2);
                    message += "get(" + size/2 + ")\n";
                    assertEquals(message,expected,actual);
                }
            }

        }
    }

}
