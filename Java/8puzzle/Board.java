package hw4.puzzle;


import edu.princeton.cs.introcs.StdOut;

public class Board implements WorldState {

    private static final int BLANK =0;
    private int[][] board;
    private Solver solver;

    // constructs N-by-N board of tiles where tiles[i][j] at row i and column j
    public Board(int[][] board) {
        this.board = this.cloneArr(board);
    }

    public int tileAt(int i, int j) {
        int size = size();
        if (i<0 || i>size || j<0 || j>size) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int[][] cloneArr(int[][] matrix) {
        int [][] myInt = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            myInt[i] = matrix[i].clone();
        return myInt;
    }

    // hamming estimate
    public int hamming() {
        int expected = 1;
        int hamming = 0;
        for (int i=0; i<board.length; i+=1) {
            for (int j=0; j<board[0].length; j+=1) {

                if (board[i][j] == 0) {
                    expected+=1;
                    continue;
                }

                if (board[i][j] != expected && expected <= board.length * board.length) {
                    hamming+=1;
                }
                expected+=1;
            }
        }
        return hamming;
    }




    // helper to get a board in final state
    public Board getFinalState() {
        int N = size();
        int[][] finalArray = new int[N][N];
        int value = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                value++;
                if ((col + 1 == N) && (row + 1 == N)) {
                    finalArray[row][col] = 0;
                } else {
                    finalArray[row][col] = value;
                }

            }
        }

        Board finalState = new Board(finalArray);

        return finalState;
    }

    public int getColRow(int[][] a, int value, String x) {
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == value) {
                    if (x.equals("col")) {
                        return col;
                    } else {
                        return row;
                    }
                }
            }
        }
        return -1;
    }




    public int manhattan() {
        int count = 0;
        int expected = 0;
        Board finalState = getFinalState();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int value = board[row][col];
                expected++;
                if (value != 0 && value != expected) {
                    count += Math.abs(row
                            - getColRow(finalState.board, value,"row"))
                            + Math.abs(col
                            - getColRow(finalState.board, value,"col"));
                }
            }
        }
        return count;
    }


    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        Board x = (Board) y;
        for (int i =0; i<this.size(); i+=1) {
            for (int j=0; j<this.size(); j+=1) {
                if (!(this.tileAt(i,j) == x.tileAt(i,j))) {
                    return false;
                }
            }
        }
        return true;
    }



    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public static void main(String[] args) {

        int[][] board = {{8,7,6},{5,4,3}, {2,1,0}};

        Board initial = new Board(board);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }

}
