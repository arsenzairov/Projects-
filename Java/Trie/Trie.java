package Project3;

/**
 * Created by Arsen on 12/9/17.
 */

public class Trie {
    private static final int R = 500;
    private Node root = new Node();

    private class Node{
        boolean exists;
        Node[] links;

        public Node(){
            links = new Node[R];
            exists = false;
        }
    }

    public boolean find(String s, boolean isFullWord) {
        if(s.length() == 0){
            return true;
        }
        Node curr = root;
        for(int i = 0; i < s.length(); i++){
            Node next = curr.links[s.charAt(i)];
            if(next == null){
                return false;
            }else{
                curr = next;
            }
        }
        if(curr.exists){
            return true;
        }else{
            return !isFullWord;
        }
    }

    public void insert(String s) {
        if(s.length() == 0){
            String msg = "empty string cannot be inserted";
            throw new IllegalArgumentException(msg);
        }
        insert(root, s, 0);
    }

    private Node insert(Node x, String s, int d){
        if(x == null){
            x = new Node();
        }

        if(d == s.length()){
            x.exists = true;
            return x;
        }

        char c = s.charAt(d);
        x.links[c] = insert(x.links[c], s, d+1);
        return x;
    }

    public void printSorted(String charOrder){
        String s = "";
        printSorted(root, s, charOrder);
    }
    public void printSorted(Node x, String s, String charOrder){
        if(x.exists){
            System.out.println(s);
        }
        for(int i = 0; i < charOrder.length(); i++){
            char c = charOrder.charAt(i);
            if(x.links[c] != null){
                printSorted(x.links[c], s + c, charOrder);
            }
        }
    }

    public static void main(String[] args)
    {
        Trie t = new Trie();
        t.insert("hello");
        t.insert("hey");
        t.insert("goodbye");
        System.out.println(t.find("hell",false));
        System.out.println(t.find("good",false));
        System.out.println(t.find("hell",true));

    }

}