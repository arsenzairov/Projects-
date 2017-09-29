/**
 * Created by Arsen on 9/29/17.
 */
public class EmptySea extends Ship{
    /* Describes a part of the ocean that does not have a ship in it.
    While it might seem silly to have the lack of a ship be a type of ship,
    this trick does simplify a number of things.
     */

    /* Constructor */
    public EmptySea(){
        super();
        this.length = 1;
    }


    /* get the type */
    @Override
    public String getShipType(){
        return "empty sea";
    }

    /* Override shootAt method */
    @Override
    boolean shootAt(int row, int column){
        if(this.bowRow == row && this.bowColumn == column){
            // update the hit array for empty sea
            this.hit[0] = true;
        }
        return false;
    }

    /* Override isSunk method */
    @Override
    boolean isSunk(){
        return false;
    }

    /* Override toString method used in Ocean */
    @Override
    public String toString(){
        if(hit[0]){
            return "-";
        }else{
            return ".";
        }
    }
}



