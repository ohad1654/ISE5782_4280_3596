package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    public void testGetNormal() {
        Sphere sphere=new Sphere(new Point(0,0,0),1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(sphere.getNormal(new Point(1,0,0)),new Vector(1,0,0), "ERROR: Sphere Normal vector  worng value");

    }


}