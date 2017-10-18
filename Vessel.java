package SampleWork;

/**
 * Created by Arsen on 10/14/17.
 */

/** Former type parameter for this class. */
public class Vessel<Blarg> {


    Blarg occupant;

    /** puts x into vessel */
    public void put(Blarg x){
        occupant = x;
    }


    /** returns anything inside the vessel */
    public Blarg peek(){
        return occupant;
    }
}
