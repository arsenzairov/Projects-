package SampleWork;

import java.util.*;

/**
 * Created by Arsen on 10/24/17.
 */
public class BSTMap<Key extends Comparable<Key>,Value> implements Map61B<Key,Value> {

    private int size;              // number of nodes in subtree
    private Node root;             // root of BST
    ArrayList<Key> order = new ArrayList<>();

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees


        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
        size =0;
    }

    @Override
    public boolean containsKey(Key key) {
        if (key == null) throw new IllegalArgumentException("No such key exists");
        return get(key) != null;
    }


    @Override
    public Value get(Key key) {
        return get(root, key);
    }


    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) {return get(x.left, key); }
        else if (cmp > 0) {return get(x.right, key); }
        else  { return x.val; }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        order.add(key);
        root = put(root,key,value);

    }

    public Node put(Node x, Key key, Value value){
        if (x==null){
            size +=1;
            return new Node(key,value);
        }

        int compare = key.compareTo(x.key);
        if (compare > 0) {
            x.right = put(x.right,key,value);
        }
        else if (compare < 0) {
            x.left = put(x.left,key,value);
        }
        else x.val = value;

        return x;
    }


    /** Removes the Node with the specified Key */
    public Node remove(Node T, Key key) {
        if (T == null)
            return null;

        int compare = key.compareTo(T.key);
        if (compare < 0)
            T.left = remove(T.left, key);
        else if (compare > 0)
            T.right = remove(T.right, key);

        // Otherwise, we’ve found L
        else if (T.left == null)
            return T.right;
        else if (T.right == null)
            return T.left;
        else
            T.right = swapSmallest(T.right, T);
        return T;
    }

    /** Promotes the Node to the top when removing a certain key */
    private Node swapSmallest(Node T, Node R) {
        if (T.left == null) {
            R.key = T.key;
            return T.right;
        } else {
            T.left = swapSmallest(T.left, R);
            return T;
        }
    }




    public void printInOrder(){
        printInOrder(root);
    }


    /** Prints all the keys of the TREE in the order */
    public void printInOrder(Node x){
        if (x==null) return;
        printInOrder(x.left);
        System.out.println(x.key);
        printInOrder(x.right);
    }

    /** Prints all the keys of the TREE  */
    public void preOrder(){
        preOrder(root);
    }

    public void preOrder(Node x){
        if (x==null) return;
        System.out.println(x.key);
        preOrder(x.left);
        preOrder(x.right);
    }


    /** Print the root Node Last */
    public void postOrder(){
        postOrder(root);
    }

    public void postOrder(Node x){
        if (x==null) return;
        postOrder(x.left);
        postOrder(x.right);
        System.out.println(x.key);
    }




    @Override
    public Value remove(Key key) {
        root = remove(root,key);
        return root.val;
    }

    @Override
    public Value remove(Key key, Value value) {
        return null;
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

}
