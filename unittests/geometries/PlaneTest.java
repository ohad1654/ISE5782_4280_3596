package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    public void testGetNormal() {
        Plane plane=new Plane(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(plane.getNormal(new Point(0,0,0)),new Vector(0,0,1) , "ERROR: Plane Normal vector  worng value");
    }


}