import edu.princeton.cs.introcs.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {

    private static Map<Character,Integer> freqTable = new HashMap<Character, Integer>();

    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {

        for (int i =0; i< inputSymbols.length; i+=1) {
            char curr = inputSymbols[i];
            if (!freqTable.containsKey(curr)) {
                freqTable.put(curr,1);
            } else {
               int count = freqTable.get(curr);
               freqTable.put(curr,count+1);
            }
        }
        return freqTable;
    }


    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        char[] input = FileUtils.readFile(args[0]);
        freqTable = HuffmanEncoder.buildFrequencyTable(input);

        BinaryTrie trie = new BinaryTrie(freqTable);
        ObjectWriter writer = new ObjectWriter(args[0] + ".huf");
        writer.writeObject(trie);
        Map<Character,BitSequence> lookupTable = trie.buildLookupTable();

        List<BitSequence> sequenceList = new ArrayList<>();
        for (Character symbol: input) {
            sequenceList.add(lookupTable.get(symbol));
        }

        BitSequence assembled = BitSequence.assemble(sequenceList);
        writer.writeObject(assembled);
        System.out.println(watch.elapsedTime());
    }
}