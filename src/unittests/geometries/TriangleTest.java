package unittests.geometries;

import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest
{

        @Test
        public void testGetNormal()
        {
           Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
           // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
           assertEquals(triangle.getNormal(new Point(0.25, 0.25, 0)), new Vector(0, 0, 1), "ERROR: triangle Normal vector  worng value");
        }
        @Test
        public void testFindIntersections()
        {
            Triangle triangle =new Triangle(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
            // ============ Equivalence Partitions Tests ==============
            assertEquals(triangle.findIntersections(new Ray(new Point(0.2,0.2,-0.5),new Vector(0,0,1))), List.of(new Point(0.2,0.2,0)) , "ERROR: Triangle find intersectionsectens Ray cut wrong value");
            assertNull(triangle.findIntersections(new Ray(new Point(1, 2, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray not cut wrong value");
            assertNull(triangle.findIntersections(new Ray(new Point(-0.1, 1.3, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray not cut wrong value");
            // =============== Boundary Values Tests ==================
            assertNull(triangle.findIntersections(new Ray(new Point(0, 0, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on vertex wrong value");
            assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on rib wrong value");
            assertNull(triangle.findIntersections(new Ray(new Point(2, 0, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on corner wrong value");
        }
}

