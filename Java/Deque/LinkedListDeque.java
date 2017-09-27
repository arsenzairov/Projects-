/**
 * Created by Arsen on 9/27/17.
 */
public class LinkedListDeque<Type> {

    // Type is some type that we would declare at the beginning
    // it can be int, or strings or other ...

    private StuffNode sentinel;  // middle man    // instance of the sllist class and also pointer to the intNode class
    private int size;
    private StuffNode sentinelback;
    private static int count = 1;

    public class StuffNode {
        public Type item;
        public StuffNode next;

        public StuffNode(Type i , StuffNode n) {
            item = i;
            next = n;
        }
    }



    /* Creates an Empty List */
    public LinkedListDeque(Type x ) {
        sentinel = new StuffNode(null,null);
        sentinel.next = new StuffNode(x,null);
        size = 1;

    }


    /* Adds an item to the front of the Deque */
    public void addFirst(Type x ) {
        sentinel.next = new StuffNode(x,sentinel.next);
        size += 1;
        if (count == 1 && sentinelback==null)
            sentinelback = sentinel.next.next;
            count++;

    }

    /* Adds an item to the back of the Deque */
    public void addLast(Type x){
        if(sentinel.next == null){
            sentinel.next = new StuffNode(x,null);
            sentinelback = sentinel.next;
            size +=1;
        }
        else if (sentinel.next.next == null){
            sentinel.next.next = new StuffNode(x,null);
            sentinelback = sentinel.next.next;
            size +=1;
        }
        else {
            sentinelback.next = new StuffNode(x,null);
            sentinelback = sentinelback.next;
            size+=1;
        }

    }

    /*  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (sentinel.next != null){
            return false;
        }
        else {
            return true;
        }
    }

    /* Returns the number of items in the Deque. */
    public int size(){
        return size;
    }

    /* Prints the items in the Deque from first to last, separated by a space. */
    public void  printDeque(){
        StuffNode temp = sentinel.next;
        while(temp!=null){
            System.out.println(temp.item);
            System.out.println();
            temp = temp.next;
        }
    }


    /* Removes and returns the item at the front of the Deque. If no such item exists, returns null */
    public Type removeFirst(){
        if (sentinel.next != null){
            Type temp = sentinel.next.item;
            sentinel.next.item = null;
            return temp;
        }
        return null;
    }


    /* Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public Type removeLast(){
        if (sentinelback != null){
            Type a = sentinelback.item;
            sentinelback.item = null;
            return a;
        }
        return null;
    }


    /* Gets the item at the given index, where 0 is the front,
    1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public Type get(int index){
        if (index ==0){
            return sentinel.next.item;
        }
        else {
            int i =0;
            StuffNode temp = sentinel.next;
            while(i<index){
                temp = temp.next;
                i++;
                if (temp == null)
                    return null;
            }

            return temp.item;
        }

    }



    public static void main(String[] args) {
        LinkedListDeque<String> y = new LinkedListDeque<String>("fourth");
        // String would be substituted everywhere for Lochness

    }
}











