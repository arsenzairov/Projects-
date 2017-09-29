/**
 * Created by Arsen on 9/29/17.
 */
public class Cruiser extends Ship{



    // shadowing
    int length = 3;

    @Override
    public int getLength(){
        return this.length;
    }

    @Override
    /* get the type */
    public String getShipType(){
        return "cruiser";
    }

    /* override toString() */
    @Override
    public String toString(){
        if(this.isSunk()){
            return "x";
        }
        return "S";
    }
}

