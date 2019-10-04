import java.util.ArrayList;

public class HuffmanDecoder {


    public static void main(String[] args) {
        ObjectReader reader = new ObjectReader(args[0]);
        Object x = reader.readObject();
        Object y = reader.readObject();

        BitSequence masterSequence = (BitSequence) y;
        BinaryTrie trie = (BinaryTrie) x;
        ArrayList<Character> chars = new ArrayList<Character>();

        Match m = trie.longestPrefixMatch(masterSequence);

        while (masterSequence.length() != 0) {
            chars.add(m.getSymbol());
            masterSequence = masterSequence.allButFirstNBits(m.getSequence().length());
            m = trie.longestPrefixMatch(masterSequence);
        }

        char[] input = new char[chars.size()];
        for (int i = 0; i < input.length; i += 1) {
            input[i] = chars.get(i);
        }

        FileUtils.writeCharArray(args[1],input);
    }
}
