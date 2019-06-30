package byog.World;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class State {

    private TETile[][] tiles;
    private Player player;
    private TERenderer ter;
    private int width;
    private int height;
    private String currentElement = "Floor";

    public State(TETile[][] tiles, Player player, TERenderer ter,int width, int height) {
        this.tiles = tiles;
        this.player = player;
        this.ter = ter;
        this.width = width;
        this.height = height;
    }

    public void updateUI() {
        Font smallFont = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.textLeft(1, height - 1, "Lives: " + player.getCurrentHealth());
        StdDraw.textRight(width - 1, height - 1, currentElement );
        StdDraw.line(0, height - 2, width, height - 2);
        StdDraw.show();
    }

    public void message(String message) {
        StdDraw.clear(new Color(0, 0, 0));
        Font smallFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width/2 , height/2 , message);
        StdDraw.show();
    }


    public boolean gameOver() {
        if (player.getCurrentHealth() == 1) {
            return true;
        }
        return false;
    }

    public boolean foundDoor() {
        int x = player.getxPos();
        int y = player.getyPos();
        int[] doors = Dungeon.getDoorLocations();

        if (doors[0] == x && doors[1] == y) {
            return true;
        }
        return false;
    }

    public boolean updateGameState(String commands) {
        Character keyPressed = ' ';
        int length = commands.length();
        int index = 0;
        updateUI();
        while (true) {
            if (length != 0 && index!=length) {
                keyPressed = Character.toLowerCase(commands.charAt(index));
                index++;
            } else {
                keyPressed = getKeyPressed();
            }
            if (keyPressed == 'w') {
                moveTop();
            } else if (keyPressed == 's') {
                moveBottom();
            } else if (keyPressed == 'a') {
                moveLeft();
            } else if (keyPressed == 'd') {
                moveRight();
            } else if (keyPressed == 'q') {
                message("Why you are leaving us :(");
                break;
            }

            if (foundDoor()) {
                break;
            }
            if (gameOver()) {
                break;
            }
            ter.renderWithoutShow(tiles);
            updateUI();
        }
        if (gameOver()) {
            message("Game over");
        }
        if (foundDoor()) {
            return true;
        }
        return false;
    }

    public void updateHealthAndIncrement(int x, int y) {
        if (hitWater(x,y)) {
            player.decrementHealthByOne();
            player.decrement();
            currentElement = "Water";
        } else if (hitTree(x,y)) {
            player.increaseHealthByOne();
            currentElement = "Tree";
        } else if (hitSand(x,y)) {
            player.increment();
            currentElement = "Sand";
        }
    }



    public void moveRight() {
        int currentX = player.getxPos();
        int currentY = player.getyPos();
        currentElement = "Floor";

        int updatedX = currentX+1+player.getIncrement();
        updateHealthAndIncrement(updatedX,currentY);


        if (!hitBarrier(updatedX,currentY)) {
                tiles[currentX][currentY] = Tileset.FLOOR;
                tiles[updatedX][currentY] = Tileset.FLOWER;
                player.setxPos(updatedX);
        } else if(!hitBarrier(updatedX-player.getIncrement(),currentY)) {
            updatedX -=player.getIncrement();
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[updatedX][currentY] = Tileset.FLOWER;
            player.setxPos(updatedX);
        }
    }

    public void moveLeft() {
        int currentX = player.getxPos();
        int currentY = player.getyPos();
        currentElement = "Floor";

        int updatedX = currentX-1-player.getIncrement();
        updateHealthAndIncrement(updatedX,currentY);

        if (!hitBarrier(updatedX,currentY)) {
                tiles[currentX][currentY] = Tileset.FLOOR;
                tiles[updatedX][currentY] = Tileset.FLOWER;
                player.setxPos(updatedX);
        } else if(!hitBarrier(updatedX+player.getIncrement(),currentY)) {
            updatedX+=player.getIncrement();
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[updatedX][currentY] = Tileset.FLOWER;
            player.setxPos(updatedX);
        }
    }

    public void moveTop() {
        int currentX = player.getxPos();
        int currentY = player.getyPos();
        currentElement = "Floor";

        int updatedY = currentY+1+player.getIncrement();
        updateHealthAndIncrement(currentX,updatedY);

        if (!hitBarrier(currentX,updatedY)) {
                tiles[currentX][currentY] = Tileset.FLOOR;
                tiles[currentX][updatedY] = Tileset.FLOWER;
                player.setyPos(updatedY);
        } else if (!hitBarrier(currentX,updatedY-player.getIncrement())) {
            updatedY-=player.getIncrement();
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[currentX][updatedY] = Tileset.FLOWER;
            player.setyPos(updatedY);
        }
    }

    public void moveBottom() {
        int currentX = player.getxPos();
        int currentY = player.getyPos();
        currentElement = "Floor";

        int updatedY = currentY-1-player.getIncrement();
        updateHealthAndIncrement(currentX,updatedY);

        if (!hitBarrier(currentX,updatedY)) {
                tiles[currentX][currentY] = Tileset.FLOOR;
                tiles[currentX][updatedY] = Tileset.FLOWER;
                player.setyPos(updatedY);
        } else if (!hitBarrier(currentX, updatedY+player.getIncrement())) {
            updatedY+=player.getIncrement();
            tiles[currentX][currentY] = Tileset.FLOOR;
            tiles[currentX][updatedY] = Tileset.FLOWER;
            player.setyPos(updatedY);
        }
    }


    public boolean hitWater(int x, int y) {
        if (tiles[x][y] == Tileset.WATER) {
            return true;
        }
        return false;
    }

    public boolean hitTree(int x, int y) {
        if (tiles[x][y] == Tileset.TREE) {
            return true;
        }
        return false;
    }

    public boolean hitSand(int x, int y) {
        if (tiles[x][y] == Tileset.SAND) {
            return true;
        }
        return false;
    }


    public boolean hitBarrier(int x, int y) {
        if (tiles[x][y] == Tileset.WALL || tiles[x][y] == Tileset.NOTHING) {
            return true;
        }
        return false;
    }


    public Character getKeyPressed() {
        Character key = null;
        while(key == null) {
            key = startMenuKeyPressed();
        }
        return key;
    }


    public Character startMenuKeyPressed() {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            System.out.println(key);
            if (Character.toLowerCase(key) == 'w') {
                return 'w';
            } else if (Character.toLowerCase(key) == 's') {
                return 's';
            } else if (Character.toLowerCase(key) == 'd') {
                return 'd';
            } else if (Character.toLowerCase(key) == 'a') {
                return 'a';
            } else if (Character.toLowerCase(key) == 'q') {
                return 'q';
            }
        }
        return null;
    }

}
