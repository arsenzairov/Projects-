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
        if (((double) size/items.length <= RFACTOR && items.length >=4)) {
            resize(items.length/2);
        }
        return itemToRemove;
    }

    /** Remove the first element of the array */
    public Item removeFirst() {
        if (size==0 || items.length == 0) {
            setRFACTOR(0);
            return null;
        }

        Item itemToRemove = items[0];
        Item[] temp = (Item[]) new Object[items.length];

        for (int i =1; i<items.length; i++) {
            temp[i-1] = items[i];
        };

        if (((double) size/items.length <= RFACTOR && items.length >=4)) {
            resize(items.length/2);
        }
        size--;
        items = temp;
        return itemToRemove;
    }

}
