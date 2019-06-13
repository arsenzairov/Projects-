import java.lang.reflect.Array;

public class ArrayDeque<Item> {

    private Item[] items;
    private int size;
    private double RFACTOR = 0;
    private final int STARTING_SIZE = 8;
    private final int SCALE_FACTOR = 2;


    /* creates an empty ArrayDeque */
    public ArrayDeque() {
        items = (Item[]) new Object[STARTING_SIZE];
        setRFACTOR(STARTING_SIZE);
        size = 0;
    }

    /* creates an array with n elements */
    public ArrayDeque(int n) {
        items = (Item[]) new Object[n];
        setRFACTOR(n);
        size = 0;
    }

    /** determine the usage ratio based on n elements */
    private void setRFACTOR(double n) {
        if (n>=16) {
            RFACTOR = 0.25;
        } else {
            RFACTOR = n/16 * 0.25;
        }
    }


    /** Resizes the array to target capacity */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        setRFACTOR(capacity);
        System.arraycopy(items,0,a,0,size);
        items = a;
    }

    /** Returns the i'th element of the array */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the size of the array */
    public int size() {
        return size;
    }

    /** Add the element to the back of the array */
    public void addLast(Item i) {
        if ((double) size/items.length >= RFACTOR) {
            resize( items.length*SCALE_FACTOR);
        }
        items[size] = i;
        size++;
    }

    /** Print the deque elements separated by space */
    public void printDeque() {
        for (int i =0; i<size; i++) {
            System.out.print(items[i]+ " ");
        }
    }

    /** Check if deque is empty */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Add the element to the front of the array */
    public void addFirst(Item i) {
        if ((double) size/items.length >= RFACTOR) {
            resize(items.length*SCALE_FACTOR);
        }
        Item[] temp = (Item[]) new Object[items.length];
        System.arraycopy(items,0,temp,1,size);
        items[size] = null;
        temp[0] = i;
        size++;
        items = temp;
    }

    /** Remove the last element of the array */
    public Item removeLast() {
        if (size==0) {
            setRFACTOR(0);
            return null;
        }
        Item itemToRemove = items[size-1];
        items[size-1] = null;
        size--;
        if (((double) size/items.length <= RFACTOR)) {
            resize(items.length/2);
        }
        return itemToRemove;
    }

    /** Remove the first element of the array */
    public Item removeFirst() {
        if (size==0) {
            setRFACTOR(0);
            return null;
        }
        Item itemToRemove = items[0];
        Item[] temp = (Item[]) new Object[items.length];
        System.arraycopy(items, 1, temp,0,size);
        if (((double) size/items.length <= RFACTOR)) {
            resize(items.length/2);
        }
        size--;
        items = temp;
        return itemToRemove;
    }




    public static void main(String[] args) {
        ArrayDeque<String> test = new ArrayDeque<>();
        System.out.println("Should be true for empty list: " + test.isEmpty());
        System.out.println(test.size());

//        test.addLast("hello");
//        test.addFirst("world");

        // addLast Test
        test.addLast("Greyworm");
        test.addLast("Missandei");
        test.addLast("Daenerys");
        test.addLast("Jon/Aegon");
        test.addLast("Arya");
        test.addLast("Tyrion");
        test.addLast("Sansa");
        test.addLast("Sam");
        test.addLast("Jorah");
//        test.printDeque();

        // addFront Test
        test.addFirst("Ghost");
        test.addFirst("Ned");
        test.addFirst("Bran");
        test.addFirst("Cersei");
        test.addFirst("Robert");
        test.addFirst("Catlyn");
        test.addFirst("Jaime");
        test.addFirst("Bron");
        test.addFirst("Varys");
        test.printDeque();

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeFirst();

        test.printDeque();

        // testing copy constructor

        System.out.println("Testing get: " + test.get(0));
        System.out.println("Testing get: " + test.get(3));

        System.out.println(test.size());
    }
}
