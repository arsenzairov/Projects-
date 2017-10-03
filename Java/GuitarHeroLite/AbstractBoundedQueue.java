package synthesizer;

/**
 * Created by Arsen on 10/2/17.
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {


    protected int fillCount;
    protected int capacity;


    public int capacity(){
        return capacity;
    }

    public int fillCount(){
        return fillCount;
    }


}


/** ABSTRACT CANNOT BE INSTANTIATED
 * Any method by default is concrete
 * If you want to make method abstract you need to specify public abstract
 *
 * Every AbstractBoundedQueue would have fill count and capacity
 *
 * Every Object that extends abstract should override the abstract methods
 *
 *
 *
 *
 *
 *
 */