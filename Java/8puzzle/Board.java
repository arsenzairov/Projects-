

/**
 * Created by Arsen on 10/31/17.
 */
package Puzzle;

import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;
import java.util.Stack;

public class Board {
    private final int N;            // tiles size
    private final int[][] tiles;    // current tiles


    public Board(int[][] blocks) {
        N = blocks.length;
        tiles = copy(blocks, blocks.length);
    }


    private int[][] copy(int[][] y, int M) {
        int[][] out = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                out[i][j] = y[i][j];
            }
        }
        return out;
    }


    public int dimension() {
        return N;
    }


    public int hamming() {
        int numOfDiffBlocks = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != j + i * N  + 1) {
                    numOfDiffBlocks++;
                }
            }
        }
        return --numOfDiffBlocks; // exclude the blank block
    }

    // helper to compute the Manhattan distance for a single block
    private int singleMan(int i, int j) {
        int goalI = tiles[i][j] / N;
        int goalJ = tiles[i][j] % N;
        if (goalJ == 0) {
            goalI -= 1;
            goalJ = N - 1;
        } else {
            goalJ -= 1;
        }
        return Math.abs(i - goalI) + Math.abs(j - goalJ);
    }


    public int manhattan() {
        int manDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != j + i * N  + 1 && tiles[i][j] != 0) {
                    manDist += singleMan(i, j);
                }
            }
        }
        return manDist;
    }


    public boolean isGoal() {
        return manhattan() == 0;
    }

    // helper for swapping two elements in a 2D array
    private void swapBlocks(int[][] x, int i, int j, int m, int n) {
        int swap;
        swap = x[i][j];
        x[i][j] = x[m][n];
        x[m][n] = swap;
    }


    public Board twin() {
        int[][] aTwin = copy(tiles, N);
        if (aTwin[0][0] * aTwin[0][1] == 0) {
            swapBlocks(aTwin, 1, 0, 1, 1);
        } else {
            swapBlocks(aTwin, 0, 0, 0, 1);
        }
        return new Board(aTwin);
    }


    public boolean equals(Object y) {
        //check for reference equality
        if (y == this) return true;

        //use instanceof instead of getClass here for two reasons
        //1. if need be, it can match any super type, and not just one class;
        //2. it renders an explicit check for "y == null" redundant, since
        //it does the check for null already - "null instanceof [type]" always
        //returns false. (See Effective Java by Joshua Bloch.)
        if (!(y instanceof Board)) return false;

        //let the compiler know y is a "Board" instead of an "Object"
        Board that = (Board) y;
        return (this.N == that.N) && (Arrays.deepEquals(this.tiles, that.tiles));

    }

    // helper method to push neighbor board onto a stack
    private void pushBoardOntoStack(Stack<Board> s, int x, int y, int m, int n) {
        int[][] neighborTiles = copy(tiles, N);
        swapBlocks(neighborTiles, x, y, m, n);
        s.push(new Board(neighborTiles));
    }


    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<Board>();
        boolean blankFound = false;
        for (int i = 0; i < N && !blankFound; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    if (i > 0) pushBoardOntoStack(boards, i - 1, j, i, j);
                    if (j > 0) pushBoardOntoStack(boards, i, j - 1, i, j);
                    if (i < N - 1) pushBoardOntoStack(boards, i + 1, j, i, j);
                    if (j < N - 1) pushBoardOntoStack(boards, i, j + 1, i, j);
                    blankFound = true;
                    break;
                }
            }
        }
        return boards;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}