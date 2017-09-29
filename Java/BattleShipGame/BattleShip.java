/**
 * Created by Arsen on 9/29/17.
 */
public class BattleShip extends Ship {
    /**
     * @param args
     */

    // shadowing
    int length = 4;

    @Override
    public int getLength(){
        return this.length;
    }

    @Override
    /* get the type */
    public String getShipType(){
        return "battleship";
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
