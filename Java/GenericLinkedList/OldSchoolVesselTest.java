package SampleWork;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arsen on 10/14/17.
 */
public class OldSchoolVesselTest {
    @Test
    public void testVessel(){
        OldSchoolVessel osv = new OldSchoolVessel();
        osv.put(5); // autoboxes into an Integer, Integer is-an object
        int y = (Integer) osv.peek(); // no autoboxing, retuns the Object
    }

}