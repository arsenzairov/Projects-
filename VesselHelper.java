package SampleWork;

/**
 * Created by Arsen on 10/14/17.
 */


/* Methods can have type parameter too!
 Specify them BEFORE the return type of the method
 */


public class VesselHelper {

    /** Remove the item from the vessel and return it */
    /** Having the slaggathor here is just a rule , <slaggathor> means define formal parameter*/
    public static <Slaggathor> Slaggathor remove(Vessel <Slaggathor> v){
        Slaggathor rVal = v.peek();
        v.put(null); // displace the occupant
        return rVal;
    }


    public static <ZX extends Comparable>  ZX max(Vessel<ZX> x , Vessel<ZX> y ){
        ZX xVal = x.peek();
        ZX yVal = y.peek();


        if (xVal.compareTo(yVal) > 0){
            return xVal;
        }
        return yVal;
    }

    public static <T1 extends Number, T2  extends Number> double ratio(Vessel<Number> x , Vessel<Number> y ){
        Number xVal = x.peek();
        Number yVal = y.peek();

        return xVal.doubleValue() / yVal.doubleValue();
    }




}
