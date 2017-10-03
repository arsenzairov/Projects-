package synthesizer;
// TODO: Make sure to make this class a part of the synthesizer package
import java.util.Iterator;
import java.lang.RuntimeException;


//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* SampleWork.Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
        first = this.capacity / 2 - 1;
        last =this.capacity / 2;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        if (last==this.capacity){
            last=0;
            rb[last]=x;
            fillCount+=1;
            last+=1;
        }
        else if (isEmpty()){
            rb[first] = x;
            fillCount++;
        }
        else {
            rb[last] = x;
            fillCount+=1;
            last+=1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T temp;
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        else if (first==this.capacity){
            first=0;
            temp=rb[first];
            rb[first] = null;
            fillCount--;
            first+=1;
            return temp;
        }
        else {
            temp = rb[first];
            rb[first] = null;
            fillCount--;
            first+=1;
            if (first == capacity()){
                first = 0;
            }
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }  // TODO: When you get to part 5, implement the needed code to support iteration.
}




