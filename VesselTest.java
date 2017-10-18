package SampleWork;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arsen on 10/14/17.
 */
public class VesselTest {
    @Test
    public void testVessel(){
        Vessel<Integer> osv = new Vessel<>();
        osv.put(5); // autoboxes into an Integer, Integer is-an object
        int y = osv.peek(); // no autoboxing, retuns the Object
        int x = 5;
        assertEquals(5,y);
        assertEquals((Integer) 5, osv.peek());
//        assertEquals( x, osv.peek());
    }

    @Test
    public void testVesselHelper(){
        Vessel<Integer> v = new Vessel<>();
        v.put(5);
        int occupant = VesselHelper.remove(v);
        assertEquals(5,occupant);
        assertEquals(null,v.peek());

        Vessel<String> sv = new Vessel<>();
        sv.put("ketchup");
        String occupantstring = VesselHelper.remove(sv);
        assertEquals("ketchup",occupantstring);
        assertEquals(null,sv.peek());


    }


    @Test
    public void testMax(){
        Vessel<Integer> v1 = new Vessel<>();
        Vessel<Integer> v2 = new Vessel<>();
        v1.put(2);
        v2.put(3);

        System.out.println(VesselHelper.max(v1,v2));
    }
}