package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RayTest {

    @Test
    public void testFindClosestPoint () {
        Ray ray=new Ray(Point.ZERO, new Vector(1,0,0));
        Point p1=new Point(0,1,0);
        Point p2=new Point(0,0,5);
        Point p3=new Point(10,0,0);
        assertEquals(p1,ray.findClosestPoint(List.of(p2, p1, p3)),"ERROR: wrong point");
        assertNull(ray.findClosestPoint(List.of()),"ERROR: wrong point");
        assertEquals(p1,ray.findClosestPoint(List.of(p1,p2,p3)),"ERROR: wrong point");
        assertEquals(p1,ray.findClosestPoint(List.of(p2,p3,p1)),"ERROR: wrong point");




    }

}