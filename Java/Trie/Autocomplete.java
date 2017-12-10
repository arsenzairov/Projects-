package Project3;

import edu.princeton.cs.introcs.In;

/**
 * Created by Arsen on 12/9/17.
 */


import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


public class Autocomplete {

    private TernaryTrie tr;
    private int initialCapacity;
    /**
     * Initializes required data structures from parallel arrays.
     * @param terms Array of terms.
     * @param weights Array of weights.
     */
    public Autocomplete(String[] terms, double[] weights) {
        if(terms.length != weights.length){
            String msg = "the lengths of terms and weights are different";
            throw new IllegalArgumentException(msg);
        }
        tr = new TernaryTrie();
        int n = terms.length;
        initialCapacity = n;
        for(int i = 0;  i < n; i++){
            if(tr.exists(terms[i])){
                String msg = "duplicate input: " + terms[i] + " at line " + i;
                throw new IllegalArgumentException(msg);
            }
            tr.insert(terms[i], weights[i]);
        }
    }


    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term
     * @return
     */
    public double weightOf(String term) {
        return tr.search(term);
    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an iterable.
     * If there are less than k matches, return all the matching terms.
     * @param prefix
     * @param k
     * @return
     */
    public Iterable<String> topMatches(String prefix, int k){
        TreeSet<TernaryTrie.Node> matchNodes= topMatchesHelper(prefix, k);
        ArrayList<String> matchStrings = new ArrayList<>();
        for(TernaryTrie.Node node : matchNodes){
            matchStrings.add(node.prefix + node.value);
        }
        return matchStrings;
    }

    public TreeSet<TernaryTrie.Node> topMatchesHelper(String prefix, int k) {
        if(k<=0){
            throw new IllegalArgumentException();
        }

        TernaryTrie.Node currNode = tr.searchPrefix(prefix);
        if(currNode == null){
            return null;
        }
        TreeSet<TernaryTrie.Node> result = new TreeSet<TernaryTrie.Node>(new compareByWeight());
        double minWeight = 0.0;
        if(currNode.weight != -1){
            result.add(currNode);
        }
        currNode = currNode.mid;
        if(currNode == null){
            return result;
        }
        PriorityQueue<TernaryTrie.Node> q = new PriorityQueue<>(initialCapacity, new compareByMaxWeight());
        q.add(currNode);
        while(!q.isEmpty()){
            currNode = q.poll();

            if((result.size() == k && currNode.maxWeight > minWeight) || (result.size() < k)){
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.mid != null){
                    q.add(currNode.mid);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }

                if(currNode.weight != -1){
                    result.add(currNode);
                }
                while(result.size() > k){
                    result.pollLast();
                }
                if(result.size() == k){
                    minWeight = result.last().weight;
                }
            }
        }
        return result;
    }

    /**
     * Returns the highest weighted matches within k edit distance of the word.
     * If the word is in the dictionary, then return an empty list.
     * @param word The word to spell-check
     * @param dist Maximum edit distance to search
     * @param k    Number of results to return
     * @return Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        ArrayList<String> result = new ArrayList<>();
        if(tr.exists(word)){
            return result;
        }
        int[] v0 = new int[word.length() + 1];
        for(int i = 0; i < v0.length; i++){
            v0[i] = i;
        }
        TreeSet<TernaryTrie.Node> midResult = new TreeSet<>(new compareByWeight());
        spellCheckHelper(midResult, word, dist, k, tr.getRoot(), v0, 0, 0.0);
        for (TernaryTrie.Node x : midResult){
            String s = x.prefix + x.value;
            System.out.println(s);
            result.add(s);
        }
        return result;
    }

    private void spellCheckHelper(TreeSet<TernaryTrie.Node> result, String word, int dist, int k, TernaryTrie.Node x, int[] v0, int prefixLength, double minWeight){

        /** stop checking and return if x is null */
        if(x == null){
            return;
        }
        /** stop and return if the branches are >dist distance away from word */
        int index = v0.length -1;
        if(prefixLength >= v0.length && v0[index] > dist){
            return;
        }
        /** stop and return if max weight of current branch is smaller than limit
         *  and there are already k candidates */
        if(result.size() >= k && x.maxWeight <= minWeight){
            return;
        }
        /** add node x if x is an valid end and x is within distance dist */
        if(x.weight != -1){
            int[] v1 = generateNextVector(v0, word, x);
            if(v1[v1.length -1] <= dist){
                result.add(x);
            }
            while(result.size() > k){
                result.pollLast();
            }
            if(result.size() == k){
                minWeight = result.last().weight;
            }
        }

        spellCheckHelper(result, word, dist, k, x.left, v0, prefixLength, minWeight);
        spellCheckHelper(result, word, dist, k, x.right, v0, prefixLength, minWeight);
        int[] v1 = generateNextVector(v0, word, x);
        spellCheckHelper(result, word, dist, k, x.mid, v1, prefixLength + 1, minWeight);
    }

    private int[] generateNextVector(int[] v0, String word, TernaryTrie.Node x){
        int[] v1 = new int[v0.length];
        v1[0] = v0[0] + 1;
        for (int i = 1; i < v0.length; i++){
            int cost = (x.value == word.charAt(i-1))? 0 : 1;
            v1[i] = Math.min(v1[i-1] + 1, Math.min(v0[i] + 1, v0[i-1] + cost));
        }
        return v1;
    }
    /**
     * customized comparator for priority queue
     * compare by max weight
     */
    private class compareByMaxWeight implements Comparator<TernaryTrie.Node>{
        public int compare(TernaryTrie.Node x1, TernaryTrie.Node x2){
            if(x1.maxWeight < x2.maxWeight){
                return 1;
            }else if(x1.maxWeight == x2.maxWeight){
                return 0;
            }else{
                return -1;
            }
        }
    }

    private class compareByWeight implements Comparator<TernaryTrie.Node>{
        public int compare(TernaryTrie.Node x1, TernaryTrie.Node x2){
            if(x1.weight < x2.weight){
                return 1;
            }else if(x1.weight == x2.weight){
                return 0;
            }else{
                return -1;
            }
        }
    }

    public void printset(Iterable<TernaryTrie.Node> set){
        Iterator<TernaryTrie.Node> it = set.iterator();
        while(it.hasNext()){
            TernaryTrie.Node x = it.next();
            String word = x.prefix + x.value;
            System.out.println(word + " " + x.weight);
        }
    }
    /**
     * Test client. Reads the data from the file,
     * then repeatedly reads autocomplete queries from standard input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */

    public static void main(String[] args) {

        String filename = "/Users/Arsen/IdeaProjects/berkley/src/Project3/tiny.txt";

        In in = new In(filename);
        {
            int N = Integer.parseInt(in.readLine());
            String[] terms = new String[N];
            double[] weights = new double[N];
            String line = null;
            int i = 0;
            while (in.hasNextLine()){

                weights[i] = in.readDouble();
                terms[i] = in.readString();
                if(weights[i] < 0){
                    String msg = "weight cannot be negative";
                    throw new IllegalArgumentException(msg);
                }
                i++;
            }
            Autocomplete autocomplete = new Autocomplete(terms, weights);
            Iterable<TernaryTrie.Node> matches = autocomplete.topMatchesHelper("s", 3);
            autocomplete.printset(matches);
            //autocomplete.spellCheck("", 1, 5);

        }
    }
}