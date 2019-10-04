import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 *   Words must be at least three letters long, may include singular or plural (or other derived forms), but may not use the same letter cube more than once per word.
 *   You will not have to account for the qu tile
 *   You must support rectangular boards of arbitrary dimensions
 */

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";

    private static Trie<Integer> trie = new Trie<>();
    private static List<String> wordsFound = new ArrayList<>();
    private static int n = Integer.MAX_VALUE;
    private static String[] board;
    private static int width;
    private static int height;
    private static boolean marked[][];

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        if (k < 0) { throw new IllegalArgumentException("k is non-positive"); }
        readWordFile();
        Stack<String> words;
        In in = new In(boardFilePath);
        board = in.readAllStrings();
        height = board.length;
        width = board[0].length();


        for (int i =0; i<height; i+=1) {
            for (int j =0; j<width; j+=1) {
                marked = new boolean[height][width];
                words = new Stack<>();
                boardSolver(words,i,j);
            }
        }

        List<String> sorted = wordsFound.stream().sorted((s1,s2) -> s2.length() - s1.length()).collect(Collectors.toList());
        if (sorted.size() < k) {
            return sorted;
        }
        return sorted.subList(0,k);
    }


    private static void check() {
        System.out.println(trie.contains("American"));
    }

    private static boolean boardSolver(Stack<String> word,int row, int col) {

        if (row < 0 || col < 0 || col > width-1 || row > height-1 || marked[row][col]) {
            return false;
        }

        char curLetter = board[row].charAt(col);
        word.push(Character.toString(curLetter));
        String w = word.toString();
        boolean exist = trie.contains(w);

        // stop if no words with current prefix
        if (!trie.keysWithPrefix(word.toString()) && !exist) {
            word.pop();
            return false;
        }

        if (word.size() > 2 && exist && !wordsFound.contains(w)) {
            wordsFound.add(w);
        }

        marked[row][col] = true;

        boolean res = boardSolver(word,row-1,col) ||
        boardSolver(word,row+1,col) ||
        boardSolver(word,row,col+1) ||
        boardSolver(word,row, col-1) ||
        boardSolver(word,row-1,col-1) ||
        boardSolver(word,row-1,col+1) ||
        boardSolver(word,row+1,col+1) ||
        boardSolver(word,row+1,col-1);

        if (!res) {
            word.pop();
            marked[row][col] = false;
        }
        return res;
    }

    /** read the words file from dictionary */
    private static void readWordFile() {
        In in = new In(dictPath);
        if (!in.exists()) {
            throw new IllegalArgumentException("Dictionary file doesn't exist");
        }

        List<String> words = new ArrayList<>();

        while (!in.isEmpty()) {
            String line = in.readLine();
            words.add(line);
        }

        Collections.sort(words);

        for (int i =0; i<words.size(); i+=1) {
            trie.put(words.get(i),i);
        }
    }


    public static void main(String[] args) {
        System.out.println("Boggle!!");
        Stopwatch timer1 = new Stopwatch();
        List<String> list = Boggle.solve(100,"smallBoard.txt");
        Boggle.check();
        double time1 = timer1.elapsedTime();
        StdOut.printf("%.2f seconds\n",time1);
        System.out.println(list);
    }
}
