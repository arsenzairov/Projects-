package byog.World;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {

    private int appWidth;
    private int appHeight;
    private int width;
    private int height;
    private int xPos;
    private int yPos;
    private Random r;

    public Room(int width,int height, Random r) {
        appWidth = width;
        appHeight = height;
        this.r = r;
        this.width = selectRandomValue(4,9);
        this.height = selectRandomValue(4,9);
        xPos = selectRandomValue(0,width);
        yPos = selectRandomValue(0,height);
    }

    public int getCenterX() {
        return xPos + (width / 2);
    }


    public int getCenterY() {
        return yPos + (height / 2);
    }


    /* draw the Room at the specified x and y */
    public void draw(TETile[][] tiles) {
        for (int row =0; row<width; row++) {
            for (int col=0; col<height; col++) {

                if (row == 0 || col == 0 || row == width-1 || col == height - 1) {
                    tiles[row+xPos][col+yPos] = Tileset.WALL;
                } else {
                    tiles[row+xPos][col+yPos] = Tileset.FLOOR;
                }
            }
        }
    }

    /* used for debugging */
    public void printCoordinates() {
        int x2 = xPos+width;
        int y2 = yPos+height;
        System.out.println("The room coordinates are format (" + xPos + "," + yPos + ") , (" + x2 + ")" + "," + "(" + y2 + ")");
    }

    /* selects random value for either x or y to draw Room */
    private int selectRandomValue(int start , int boundary) {
        return RandomUtils.uniform(r,start,boundary);
    }

    /* checks if the room overlaps with any other room in the array */
    public boolean overlap(TETile[][] tiles) {
        if (xPos < 3 || xPos + width > appWidth-2 ||  yPos < 2 || yPos + height > appHeight-2) {
            return true;
        }
        for (int row =0; row<width; row++) {
            for (int col=0; col<height; col++) {
                if (tiles[row+xPos][col+yPos] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }
}
