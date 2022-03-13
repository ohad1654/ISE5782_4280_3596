package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    public void testGetNormal() {
        Triangle triangle = new Triangle(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(triangle.getNormal(new Point(0.25,0.25,0)),new Vector(0,0,1) , "ERROR: triangle Normal vector  worng value");

    }

}