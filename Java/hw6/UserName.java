package hw7;

import java.util.ArrayList;

/**
 * Created by Arsen on 10/26/17.
 */
public class UserName {
    private int m;
    private Node first;

    private class Node{
        private String name;
        private String email;
        private Node next;

        public Node(String name, String email, Node pointer){
            this.name = name;
            this.email = email;
            this.next = pointer;
        }



    }


    public UserName() {
        // YOUR CODE HERE
    }

    public String get(String name) {
        for (Node x = first; x != null; x = x.next) {
            if (name.equals(x.name))
                return x.email;
            else if (name.equals(x.email))
                return x.name;
        }
        return null;
    }


    public boolean contains(String name) {
        return get(name) != null;
    }


    public void put(String name, String email) {
        for (Node x = first; x != null; x = x.next) {
            if (name.equals(x.name)) {
                x.email = email;
                return;
            }
        }
        first = new Node(name, email, first);
    }
}

