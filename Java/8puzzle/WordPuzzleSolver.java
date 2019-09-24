package hw4.puzzle;
import edu.princeton.cs.introcs.StdOut;


public class WordPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
     **********************************************************************/
    public static void main(String[] args) {
        String start = "horse";
        String goal = "nurse";

        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.printf("%s -> ", ws);
        }
        StdOut.println("");
    }
}
