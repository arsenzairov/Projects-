public class LinkedListDeque<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item, Node next, Node previos) {
            this.item = item;
            this.next = next;
            this.prev = previos;
        }
    }

    private Node sentinel;
    private int size=0;

    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null,null,null);
        sentinel.next = new Node(x, null,null);
        size = 1;
    }


    /** Adds an item of type T to the front of the deque */


    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }


    // sentinel.next.prev = lastNode , sentinel.next.next = lastNode

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        if (sentinel.next == null) {
            sentinel.next = new Node(item,null,null);
            size+=1;
            return;
        }

        Node temp = new Node(item, sentinel.next,sentinel.next.prev);

        if (size > 1) {
            sentinel.next.prev.next = temp;
        }
        sentinel.next.prev = temp;
        sentinel.next = temp;

        if (size == 1) {
            sentinel.next.next.next = temp;
            sentinel.next.next.prev = temp;
            sentinel.next.prev = sentinel.next.next;
        }
        size+=1;
    }

    /**   Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (sentinel.next == null) {
            return true;
        }
        return false;
    }


    /** Adds an item of type T to the back of deque */
    public void addLast(T item) {
        if (sentinel.next == null) {
            sentinel.next = new Node(item,null,null);
            size++;
            return;
        }

        Node lastNode = new Node(item, sentinel.next, sentinel.next.prev);
        if (size == 1) {
            sentinel.next.prev = lastNode;
            sentinel.next.next = lastNode;
            lastNode.prev = sentinel.next;
        } else {
            sentinel.next.prev.next = lastNode;
            sentinel.next.prev = lastNode;
        }
        size++;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists , returns null */
    public T removeFirst() {
        T element = sentinel.next.item;
        if (size == 1) {
            sentinel.next = null;
        } else {
            sentinel.next.prev.next = sentinel.next.next;
            sentinel.next.next.prev = sentinel.next.prev;
            sentinel.next = sentinel.next.next;
        }

        size--;
        return element;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists , returns null */
    public T  removeLast() {

        T element = sentinel.next.prev.item;

        if (size == 1) {
            sentinel.next = null;
        } else {
            sentinel.next.prev.prev.next = sentinel.next;
            sentinel.next.prev = sentinel.next.prev.prev;
        }
        size--;
        return element;
    }

    /** Returns the item at the given index, where 0 is the front , 1 is the next item, and so forth. If no such item exists return null */
    public T get(int index) {
        Node tempNode;

        if (index>size-1 || index < 0) {
            return null;
        }

        if (index > size/2) {
            index = size-index-1;
            System.out.println("called");
            tempNode = sentinel.next.prev;
            // start from the back
            while(index!=0) {
                tempNode = tempNode.prev;
                index--;
            }
            return tempNode.item;

        } else {
            tempNode = sentinel.next;
            // start from the front
            while(index!=0) {
                tempNode = tempNode.next;
                index--;
            }
            return tempNode.item;
        }
    };

    /** Prints the Items in the deque ffrom the first to last, separated by space */
    public void printDeque() {
        Node tempNode = sentinel.next;
        int currentIndex = 0;

        while(currentIndex!=size()) {
            System.out.print(tempNode.item + " ");
            tempNode = tempNode.next;
            currentIndex++;
        }
    }




    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<Integer>();
        list.addLast(2);
        list.addFirst(7);
        list.addLast(10);
        list.addFirst(15);
        list.addFirst(20);
        list.addLast(50);
        list.printDeque();
        System.out.println(list.get(0));
    }
}