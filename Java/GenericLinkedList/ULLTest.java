package SampleWork;

import static org.junit.Assert.*;

/**
 * Created by Arsen on 10/14/17.
 */
import org.junit.Test;

import java.util.Iterator;

/** ULLTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        um.put("Gracia", "Dios Basado");
        um.put("Grac", "Dios Basado");
        assertEquals("Dios Basado", um.get("Gracias"));
        assertTrue(um.containsKey("Gracias"));
        assertFalse(um.containsKey("Gra"));
        assertEquals(3, um.size());
        assertEquals("Dios Basado", um.remove("Gracias"));
        assertEquals(2
                , um.size());

        um.put("one", "Josh Hug");
        um.put("two", "Rayn");
        assertEquals(null, um.remove("one", "Rayn"));
        assertEquals("Rayn", um.remove("two", "Rayn"));
    }


    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        int key = umi.next();
        assertEquals(2, key);
        assertEquals("two", um.get(key));
    }
}