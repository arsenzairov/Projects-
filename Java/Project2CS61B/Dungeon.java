package byog.World;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class Dungeon {


    private Room[] rooms;
    private int width;
    private int height;
    private int nRooms;
    private Random r;
    private TETile[][] tiles;
    private static int doorX;
    private static int doorY;

    public Dungeon(int width, int height, Random random, TETile[][] tiles, int nRooms) {
        this.width = width;
        this.height = height;
        this.nRooms = nRooms;
        rooms = new Room[nRooms];
        r = random;
        this.tiles = tiles;
    }

    public static int[] getDoorLocations() {
        int[] doors = new int[2];
        doors[0] = doorX;
        doors[1] = doorY;
        return doors;
    }


    // given two rooms draw the corridors by (randomly selecting horizontal and vertical corridors)
    public void createCorridors() {
        for (int i =1 ; i<rooms.length; i++) {
            Room prevRoom = rooms[i-1];
            Room currentRoom = rooms[i];

            Corridor corridor = new Corridor(tiles, currentRoom.getCenterX(), currentRoom.getCenterY(), prevRoom.getCenterX(), prevRoom.getCenterY(), r);
            corridor.drawRandomCorridor();
        }
    }

    // public create Player Object On the map
    public Player createPlayer() {
        int randomX = selectRandomValue(3,width);
        int randomY = selectRandomValue(3,height);
        Player player = null;

        while (true) {
            player = new Player(randomX,randomY);
            if (tiles[randomX][randomY] != Tileset.NOTHING && tiles[randomX][randomY] != Tileset.WALL) {
                tiles[randomX][randomY] = Tileset.FLOWER;
                break;
            }
            randomX =  selectRandomValue(3,width);
            randomY = selectRandomValue(3,height);
        }
        return player;
    }

    public void createObjects(int nWaterMarks , int nTrees, int nSand) {
        create(Tileset.WATER,nWaterMarks);
        create(Tileset.TREE,nTrees);
        create(Tileset.SAND,nSand);
        createDoor();
    }


    public void createDoor() {
        int randomX = selectRandomValue(3,width);
        int randomY = selectRandomValue(3,height);

        while (tiles[randomX][randomY] != Tileset.WALL || (tiles[randomX+1][randomY] == Tileset.NOTHING && tiles[randomX][randomY+1] == Tileset.NOTHING) || (tiles[randomX-1][randomY] == Tileset.NOTHING && tiles[randomX][randomY-1] == Tileset.NOTHING)) {
            randomX = selectRandomValue(3,width);
            randomY = selectRandomValue(3,height);
        }
        tiles[randomX][randomY] = Tileset.UNLOCKED_DOOR;
        doorX = randomX;
        doorY = randomY;
        System.out.println("Door location " + randomX + "and y is " + randomY);
    }

    // generate Water Objects on a Map
    private void create(TETile obj, int boundary) {
        int count = 0;
        int randomX = selectRandomValue(3,width);
        int randomY = selectRandomValue(3,height);

        while (count != boundary) {
            if (tiles[randomX][randomY] != Tileset.NOTHING && tiles[randomX][randomY] != Tileset.WALL && tiles[randomX][randomY] != Tileset.PLAYER) {
                tiles[randomX][randomY] = obj;
                count++;
            }
            randomX = selectRandomValue(3,width);
            randomY = selectRandomValue(3,height);
        }
    }

    /* selects random value for either x or y to draw Room */
    private int selectRandomValue(int start , int boundary) {
        return RandomUtils.uniform(r,start,boundary);
    }

    /* Check if rooms overlaps , if not create room
       Otherwise try to generate another room
     */
    public void createRooms() {
        int roomIndex = 0;
        int countNRooms = 0;
        while (countNRooms != nRooms) {
            Room room = new Room(width,height,r);
            if (room.overlap(tiles)) {
                continue;
            }
            room.printCoordinates();
            room.draw(tiles);
            rooms[roomIndex] = room;
            roomIndex+=1;
            countNRooms++;
        }
    }
}
