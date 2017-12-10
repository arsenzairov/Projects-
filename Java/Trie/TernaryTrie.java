package Project3;

/**
 * Created by Arsen on 12/9/17.
 */

public class TernaryTrie{

    /** Inner class Node */
    class Node{
        double maxWeight;
        double weight;
        char value;
        Node left, mid, right;
        String prefix;

        public Node(char c){
            maxWeight = -1;
            weight = -1;
            value = c;
            prefix = "";
        }
    }

    private Node root;

    public Node getRoot(){
        return root;
    }

    public void insert(String s, double w){
        if(s.length() == 0){
            String msg = "cannot insert empty string";
            throw new IllegalArgumentException(msg);
        }
        root = insert(root, 0, s, w, "");
    }

    private Node insert(Node x, int d, String s, double w, String pre){
        char c = s.charAt(d);
        if(x == null){
            x = new Node(c);
            x.prefix = pre;
        }
        if(w > x.maxWeight){
            x.maxWeight = w;
        }
        if(x.value == c && d == s.length() - 1){
            x.weight = w;
            return x;
        }
        if(x.value == c){
            x.mid = insert(x.mid, d+1, s, w, pre + c);
        }else if(c < x.value){
            x.left = insert(x.left, d, s, w, pre);
        }else{
            x.right = insert(x.right, d, s, w, pre);
        }
        return x;
    }

    /**
     * search for the prefix, return the node of the last char in the prefix
     * if prefix not exist, return null
     */
    public Node searchPrefix(String prefix){
        if(prefix.length() == 0){
            return root;
        }
        return searchPrefix(root, 0, prefix);
    }

    private Node searchPrefix(Node x, int d, String prefix){
        if(x == null){
            return null;
        }
        char c = prefix.charAt(d);
        if(x.value == c && d == prefix.length() - 1){
            return x;
        }

        if(c < x.value){
            return searchPrefix(x.left, d, prefix);
        }else if(c > x.value){
            return searchPrefix(x.right, d, prefix);
        }else{
            return searchPrefix(x.mid, d+1, prefix);
        }
    }

    /** returns true if trie contains the word s */
    public boolean exists(String s){
        Node temp = searchPrefix(s);
        if(temp == null || temp.weight == -1){
            return false;
        }
        return true;
    }
    /**
     * search the string, return the weight
     * if the string does not exist, return 0
     */
    public double search(String s){
        Node temp = searchPrefix(s);
        if(temp == null || temp.weight == -1){
            return 0.0;
        }
        return temp.weight;
    }

    private double search(Node x, int d, String s){
        if(x == null){
            return 0.0;
        }
        char c = s.charAt(d);
        if(x.value == c && d == s.length() - 1){
            return x.weight;
        }

        if(c < x.value){
            return search(x.left, d, s);
        }else if(c > x.value){
            return search(x.right, d, s);
        }else{
            return search(x.mid, d+1, s);
        }
    }

    public static void main(String[] args)
    {
        TernaryTrie tr = new TernaryTrie();
        tr.insert("smog",5);
        tr.insert("buck", 10);
        System.out.println(tr.exists("smog"));
        System.out.println(tr.exists("smo"));
        tr.insert("cart",4.2);
        tr.insert("plane",1);
        System.out.println(tr.search("cart"));
        System.out.println(tr.search("plan"));
        System.out.println(tr.searchPrefix("ca"));
    }
}
