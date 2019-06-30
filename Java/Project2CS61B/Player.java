package byog.World;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Player implements Serializable {

    private int currentHealth = 5;
    private int xPos;
    private int yPos;
    private int increment;

    public Player(int currentX, int currentY) {
        xPos = currentX;
        yPos = currentY;
        increment = 0;
    }

    public void increment() {
        increment++;
    }

    public void decrement() {
        if (increment == 0) {
            return;
        }
        increment--;
    }

    public int getIncrement() {
        return increment;
    }


    public void increaseHealthByOne() {
        currentHealth+=1;
    }

    public void decrementHealthByOne() {
        currentHealth-=1;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }

    public TETile drawPlayer() {
        return Tileset.PLAYER;
    }


}
