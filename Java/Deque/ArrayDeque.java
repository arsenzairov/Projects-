/**
 * Created by Arsen on 9/27/17.
 */
public class ArrayDeque<Type> {

    private Type[] items;
    private int size;
    private int nextFirst=4;
    private int nextLast=5;
    private int lastcounter;
    private int firstcounter;


    /* Cretes an empty list */
    public ArrayDeque(int size) {

        items = (Type[]) new Object[8]; // generic arrays are not allowed
        size=0;
    }

    private void resize(int capacity) {
        Type[] a = (Type[]) new Object[capacity*2];
        // java does not allow to create arrays of generic objects

        for (int i = 0; i<items.length; i++) {
                a[a.length-items.length+i] = items[i];
            }
        nextFirst =  a.length - items.length-1;
        items = a;
        nextLast = 0;
        lastcounter = 0;
        firstcounter = 0;

//        System.arraycopy(items, 0, a, 0, size);
//        items = a;

    }


    /* Adds an item to the front of the Deque. */
    public void addFirst(Type x) {
        if (items.length == size){
            resize(size+1);
        }

        if (items[nextFirst + firstcounter] == null){
            items[nextFirst + firstcounter] = x;
            firstcounter--;
            size+=1;
        }
}


    /* Insert X into the back of the list */
    public void addLast(Type x) {

        if (items.length == size){
            resize(size+1);
        }


        if(items[nextLast+lastcounter] == null) {
            items[nextLast + lastcounter] = x;
            lastcounter++;
            size += 1;
        }
        if(nextLast+lastcounter == items.length){
            lastcounter = 0;
            nextLast = 0;
        }
    }



    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }


    /* Returns the number of items in the Deque. */
    public int size(){
        return size;
    }

    /* Prints the items in the Deque from first to last, separated by a space. */
    public void printDeque(){
        for (int i = 0; i < items.length ; i++){
            System.out.println(i + " item is " + items[i]);
        }
    }

    /* Removes and returns the item at the front of the Deque. If no such item exists, returns null. */
    public Type removeFirst(){
        Type temp = items[nextFirst+firstcounter+1];
        items[nextFirst + firstcounter+1] = null;
        return temp;
    }

    /* Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public Type removeLast(){
        Type temp = items[nextLast + lastcounter-1];
        items[nextLast + lastcounter-1] = null;
        return temp;

    }

    public Type get(int index){
        for (int i =0; i<items.length;i++){
            if (i==index){
                return items[i];
            }
        }
        return null;
    }











    public static void main(String[] args){
        ArrayDeque<Integer> array = new <Integer> ArrayDeque(8);


        
    }
}
