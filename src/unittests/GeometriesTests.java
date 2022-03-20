package unittests;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeometriesTests {
    @Test
    public void testFindIntersections()
    {


        // =============== Boundary Values Tests ==================
        Geometries objects1 = new Geometries();
        Geometries objects2 = new Geometries(new Plane(new Point(0,0,-100),new Point(0,1,-100),new Point(1,0,-100)),new Sphere(new Point(100,100,100),1),new Triangle(new Point(0,0,10000),new Point(1,0,10000),new Point(0,1,10000)));
        assertNull(objects1.findIntersections(new Ray(new Point(0.2, 0.2, -0.5), new Vector(0, 0, 1))), "ERROR: Geometries findIntersections empty objects wrong value");
        assertNull(objects2.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))), "ERROR: Geometries findIntersections no intersections objects wrong value");
        assertEquals(objects2.findIntersections(new Ray(new Point(0,0,0),new Vector(0,0,-1))), 1, "ERROR: Geometries findIntersections one intersections objects wrong value");
        // ============ Equivalence Partitions Tests ==============


    }
}
