package byog.World;
import byog.Core.RandomUtils;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;


import javax.swing.*;
import java.io.*;
import java.util.Random;

public class MapGenerator implements Serializable {


    private TERenderer ter;
    private TETile[][] tiles;

    // Width and height of the canvas
    private int width;
    private int nWaterMarks;
    private int nTrees;
    private int nSand;
    private int height;
    private long SEED;
    private Random RANDOM;
    private Player player;
    private int nRooms;

    public MapGenerator(TERenderer ter, int width , int height) {
        this.ter = ter;
        this.width = width;
        this.height = height;
        tiles = new TETile[width][height];
        nRooms = 6;
        nWaterMarks = 5;
        nTrees = 5;
        nSand = 2;
    }

    private void initTiles() {
        for (int x = 0; x < width ; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void setSEED(long seed) {
        SEED = seed;
        RANDOM = new Random(SEED);
    }

    public void startGame() {
        Menu menu = new Menu(width,height,ter);
        menu.initialize();
        Character keyPressed = menu.getMenuKey();
        updateGameState(menu, keyPressed);
    }

    public void startGameWithInputString(long seed, Character initialCommand, String allCommands) {

        if (Character.toLowerCase(initialCommand) == 'l') {
            if (loadGameDataFromFile()) {
                // initTiles();
                setSEED(this.SEED);
                drawPreviousWorld(allCommands);
            }
        } else if (Character.toLowerCase(initialCommand) == 'n') {
            ter.initialize(width,height);
            setSEED(seed);
            initTiles();
            draw2DWorld(allCommands);
        }
    }

    public void updateGameState(Menu menu, Character key) {
        if (key == 'n') {
            long seed = menu.enterSeedText();
            setSEED(seed);
            initTiles();
            draw2DWorld("");
        } else if (key == 'l') {
            if (loadGameDataFromFile()) {
                // initTiles();
                drawPreviousWorld("");
            }
        } else if (key == 'q') {
            System.exit(0);
        } else if (key == 'r') {
            setSEED(this.SEED + 1);
            nRooms+=1;
            initTiles();
            draw2DWorld("");
        }
    }

    public void drawPreviousWorld(String text) {
        ter.initialize(width,height);
        ter.renderFrame(tiles);
        interact(text);
    }


    public void draw2DWorld(String text) {
//        ter.initialize(width,height);
        Dungeon dungeon = new Dungeon(width,height, RANDOM,tiles, nRooms);
        dungeon.createRooms();
        dungeon.createCorridors();
        player = dungeon.createPlayer();
        dungeon.createObjects(nWaterMarks,nTrees,nSand);
        System.out.println(TETile.toString(tiles));
        ter.renderFrame(tiles);
        interact(text);
    }


    public void interact(String commands) {
        State state = new State(tiles, player,ter,width,height);
        boolean doorfound = state.updateGameState(commands);

        if (!doorfound) {
            saveGameDataToFile();
        } else {
            nRooms+=1;
            nWaterMarks *= 2;
            nTrees += 2;
            nSand += 1;
            updateGameState(null, 'r');
        }
    }

    private boolean loadGameDataFromFile(){

        try {
            InputStream fileStream = new FileInputStream("myarray.txt");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            tiles = (TETile[][]) objectStream.readObject();
            SEED = (Long) objectStream.readObject();
            player = (Player) objectStream.readObject();
            objectStream.close();
            fileStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveGameDataToFile() {

        File f = new File("myarray.txt");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }

        try {
            OutputStream fileStream = new FileOutputStream("myarray.txt",false);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(tiles);
            objectStream.writeObject(SEED);
            objectStream.writeObject(player);
            objectStream.flush();
            objectStream.close();
            fileStream.close();
            System.out.println("Serializing Finished");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



