import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/* 1. Count frequency freq[i] for each char i in input
   2. start with one node corresponding ot each char i (with weight freq[i])
   3. Repeat until single trie formed:
      - select two tries with min weight freq[i] and freq[j]
      - merge into single trie with weight freq[i] + freq[j]
 */

public class BinaryTrie implements Serializable {

    private Map<Character,BitSequence> bitSequence = new HashMap<Character, BitSequence>();
    private Node trie;

    private static class Node implements Comparable<Node>, Serializable {

        private char ch; // Unused for internal nodes
        private int freq;
        private final Node left, right;
        private StringBuilder encoding;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq =freq;
            this.left = left;
            this.right = right;
            encoding = new StringBuilder();
        }

        public void setEncoding(int bit) {
            this.encoding.insert(0,bit);
        }

        public String getEncoding() {
            return this.encoding.toString();
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /** Given a frequency table which maps symbols of type V to their relative frequencies,
     * the constructor should build a Huffman decoding trie according to the procedure discussed in class
     * @param frequencyTable
     */
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        trie = buildTrie(frequencyTable);
    }

    public Node getRoot() {
        return trie;
    }

    /** Builds a huffman decoding trie */
    private Node buildTrie(Map<Character,Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<Node>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            pq.insert(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0',x.freq + y.freq, x, y);
            changeEncoding(parent.left,0);
            changeEncoding(parent.right, 1);
            pq.insert(parent);
        }

        return pq.delMin();
    }

    /** Add the bit to the character encoding */
    private void changeEncoding(Node x, int bit) {
        if (x.isLeaf()) {
            x.setEncoding(bit);
            return;
        }
        changeEncoding(x.left,bit);
        changeEncoding(x.right,bit);
    }

    /* find longest prefix that matches the given bitsequence */
    public Match longestPrefixMatch(BitSequence querySequence) {

        Node temp = trie;
        StringBuilder sequence = new StringBuilder();
        int index = 0;
        while (!temp.isLeaf()) {
            if (querySequence.bitAt(index) == 0) {
                sequence.append('0');
                temp = temp.left;
            } else {
                sequence.append('1');
                temp = temp.right;
            }
            index+=1;
        }
        Match m = new Match(new BitSequence(sequence.toString()), temp.ch);
        return m;
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character,BitSequence> table = new HashMap<Character, BitSequence>();
        buildLookUpTableHelper(trie,table);
        return table;
    }

    private void buildLookUpTableHelper(Node x, Map<Character,BitSequence> table) {
        if (x.isLeaf()) {
            table.put(x.ch,new BitSequence(x.getEncoding()));
            return;
        }
        buildLookUpTableHelper(x.left,table);
        buildLookUpTableHelper(x.right,table);
    }
}