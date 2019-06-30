package byog.World;


import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/* Helpful class who's sole purpose is to draw corridors */
public class Corridor {

    private int startRow = 0;
    private int startCol = 0;
    private int endRow = 0;
    private int endCol = 0;
    private TETile[][] tiles;
    private Random r;


    public Corridor(TETile[][] tiles, int startRow, int startCol, int endRow, int endCol, Random r) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.tiles = tiles;
        this.r = r;
    }


    /* draw the horizontal corridor */
    private void drawHorizontalCorridor(TETile[][] arr, int startRow , int endRow, int currentCol) {

        if (startRow > endRow) {
            int temp = endRow;
            endRow = startRow;
            startRow = temp;
        }

        for (int i =startRow; i<=endRow; i++) {
            if (arr[i][currentCol-1].equals(Tileset.NOTHING)) {
                arr[i][currentCol-1] = Tileset.WALL;
            }
            if (arr[i][currentCol+1].equals(Tileset.NOTHING)) {
                arr[i][currentCol+1] = Tileset.WALL;
            }

            if (i<endRow) {
                arr[i][currentCol] = Tileset.FLOOR;
            }
        }

        // Checking the edge cases
        if (arr[startRow-1][currentCol-1].equals(Tileset.NOTHING)) {
            arr[startRow-1][currentCol-1] = Tileset.WALL;
        }

        if (arr[endRow+1][currentCol+1].equals(Tileset.NOTHING)) {
            arr[endRow+1][currentCol+1] = Tileset.WALL;
        }

        if (arr[endRow+1][currentCol-1].equals(Tileset.NOTHING)) {
            arr[endRow+1][currentCol-1] = Tileset.WALL;
        }
    }

    /* draw the vertical corridor */
    private static void drawVerticalCorridor(TETile[][] arr, int startCol, int endCol, int currentRow) {

        if (startCol > endCol) {
            int temp = endCol;
            endCol = startCol;
            startCol = temp;
        }

        for (int i=startCol; i<=endCol; i++) {
            if (arr[currentRow+1][i].equals(Tileset.NOTHING)) {
                arr[currentRow+1][i] = Tileset.WALL;
            }
            if (arr[currentRow-1][i].equals(Tileset.NOTHING)) {
                arr[currentRow-1][i] = Tileset.WALL;
            }

            if (i<=endCol) {
                arr[currentRow][i] = Tileset.FLOOR;
            }
        }



        if (arr[currentRow-1][endCol+1].equals(Tileset.NOTHING)) {
            arr[currentRow-1][endCol+1] = Tileset.WALL;
        }

    }


    /* draw horizontal and vertical corridors randomly */
    public void drawRandomCorridor() {
        int val = RandomUtils.uniform(r,0,100);
        if (val<50) {
            drawHorizontalCorridor(tiles,startRow,endRow,startCol);
            drawVerticalCorridor(tiles, startCol, endCol, endRow);
        } else {
            drawVerticalCorridor(tiles, startCol, endCol, startRow);
            drawHorizontalCorridor(tiles,startRow,endRow,endCol);
        }
    }
}
