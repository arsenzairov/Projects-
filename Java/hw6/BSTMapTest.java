package SampleWork;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arsen on 10/24/17.
 */
public class BSTMapTest {


    @Test
    public void test1(){
        BSTMap<String,Integer> Map = new BSTMap<>();
        Map.put("judy",0);
        Map.put("bill",13);
        Map.put("fred",2);
        Map.put("alice",1);
        Map.put("dave",1);
        Map.put("mary",6);
        Map.put("tom",8);
        Map.put("jane",4);
        Map.put("joe",2);
        Map.put("a",2);
        assertEquals(10,Map.size());
        assertFalse(Map.containsKey("s"));
        assertFalse(Map.containsKey("l"));
        System.out.println("Map In Order: ");
        Map.printInOrder();  // a would be first
        System.out.println("All keys of Map , No Ordering: ");
        Map.preOrder();
        System.out.println("POST ORDER");
        Map.postOrder(); // m would be last
        Map.remove("alice");
        assertFalse(Map.containsKey("alice"));
        

    }

}