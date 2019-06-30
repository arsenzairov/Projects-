package byog.World;

import byog.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Menu {

    private int width;
    private int height;
    private final int TILE_SIZE = 16;
    private TERenderer ter;

    public Menu(int width, int height, TERenderer ter) {
        this.width = width;
        this.height = height;
        this.ter = ter;
    }

    public void initialize() {
        ter.initialize(width,height);
        drawText();
    }

    public void drawText() {
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.show();
        StdDraw.text(width/2 , height - 5, "CS61B: THE GAME");
        StdDraw.text(width/2, height/2-5, "New Game (N)");
        StdDraw.text(width/2, height/2 - 7, "Load Game (L)");
        StdDraw.text(width/2, height/2 -  9, "Quit (Q)");
        StdDraw.show();
    }

    public long enterSeedText() {
        StdDraw.clear(new Color(0, 0, 0));
        StdDraw.text(width/2, height/2, "Enter Seed");
        StdDraw.show();
        StringBuilder str = new StringBuilder();
        char seedNum = 0;
        while (seedNum != 's') {
            seedNum = getSeedKey();
            if (seedNum != 0 && seedNum != 's') {
                str.append(seedNum);
                StdDraw.clear(new Color(0, 0, 0));
                StdDraw.text(width/2, height/2, "Enter Seed");
                StdDraw.text(width/2, height/2-2, str.toString());
                StdDraw.show();
            }
        }
        return Long.parseLong(str.toString());
    }

    public Character getMenuKey() {
        Character key = null;
        while(key == null) {
            key = startMenuKeyPressed();
        }
        return key;
    }

    public Character getSeedKey() {
        return seedKeyPressed();
    }

    public Character startMenuKeyPressed() {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            System.out.println(key);
            if (Character.toLowerCase(key) == 'n') {
                return 'n';
            } else if (Character.toLowerCase(key) == 'q') {
                return 'q';
            } else if (Character.toLowerCase(key) == 'l') {
                return 'l';
            }
        }
        return null;
    }

    public Character seedKeyPressed() {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            System.out.println(key);
            if (Character.isDigit(key)) {
                return key;
            } else if (Character.toLowerCase(key) == 's') {
                return 's';
            }
        }
        return 0;
    }
}
