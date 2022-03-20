package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    public void testGetNormal() {
        Plane plane=new Plane(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(plane.getNormal(new Point(0,0,0)),new Vector(0,0,1) , "ERROR: Plane Normal vector  worng value");

    }
    @Test
    public void testFindIntersections()
    {
        Plane plane=new Plane(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));

        // ============ Equivalence Partitions Tests ==============
        assertEquals(plane.findIntersections(new Ray(new Point(0,0,-1),new Vector(0,2,1))),List.of(new Point(0,2,0)) , "ERROR: Plane find intersectionsectens Ray cut not parallel not orthogonal wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 2, 4))), "ERROR: Plane find intersectionsectens Ray not cut not parallel not orthogonal wrong value");
        // =============== Boundary Values Tests ==================
        assertNull(plane.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "ERROR: Plane find intersectionsectens Ray parallel not unite wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))), "ERROR: Plane find intersectionsectens Ray parallel and unite wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 2, 3))), "ERROR: Plane find intersectionsectens Ray cut not parallel not orthogonal begin on the plane wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 2, 3))), "ERROR: Plane find intersectionsectens Ray cut not parallel not orthogonal begin on the plane point wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 3), new Vector(0, 0, 1))), "ERROR: Plane find intersectionsectens Ray cut orthogonal on plane wrong value");
        assertEquals(plane.findIntersections(new Ray(new Point(0,0,-1),new Vector(0,0,1))),List.of(new Point(0,0,0)) , "ERROR: Plane find intersectionsectens Ray cut orthogonal before plane wrong value");
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))), "ERROR: Plane find intersectionsectens Ray cut orthogonal after plane wrong value");

    }


}