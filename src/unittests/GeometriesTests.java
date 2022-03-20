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


        Triangle triangle =new Triangle(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
        Sphere sphere = new Sphere(new Point(100,100,100),1);
        Plane plane = new Plane(new Point(0,0,-100),new Point(0,1,-100),new Point(1,0,-100));
        Geometries objects1 = new Geometries();
        Geometries objects2 = new Geometries();
        assertEquals(objects1.findIntersections(new Ray(new Point(0.2,0.2,-0.5),new Vector(0,0,1))), null, "ERROR: Geometries findIntersections empty objects wrong value");
        assertEquals(objects2.findIntersections(new Ray(new Point(0.2,0.2,-0.5),new Vector(0,0,1))), null, "ERROR: Geometries findIntersections empty objects wrong value");


        // ============ Equivalence Partitions Tests ==============
        assertEquals(triangle.findIntersections(new Ray(new Point(0.2,0.2,-0.5),new Vector(0,0,1))), List.of(new Point(0.2,0.2,0)) , "ERROR: Triangle find intersectionsectens Ray cut wrong value");
        assertNull(triangle.findIntersections(new Ray(new Point(1, 2, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray not cut wrong value");
        assertNull(triangle.findIntersections(new Ray(new Point(-0.1, 1.3, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray not cut wrong value");
        // =============== Boundary Values Tests ==================
        assertNull(triangle.findIntersections(new Ray(new Point(0, 0, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on vertex wrong value");
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on rib wrong value");
        assertNull(triangle.findIntersections(new Ray(new Point(2, 0, -0.5), new Vector(0, 0, 1))), "ERROR: Triangle find intersectionsectens Ray cut on corner wrong value");

        triangle=new Triangle(new Point(-1,-1,0),new Point(0,1,0),new Point(1,0,0));
        plane=new Plane(new Point(0,0,0),new Point(0,1,0),new Point(1,0,0));
        sphere=new Sphere(new Point(0,0,0),2);
        assertEquals(new Geometries(triangle,plane,sphere).findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0))).size(),1,"ERROR: geometries find intersectoins not all shapse inersacte, wrong value");
        assertEquals(new Geometries(triangle,plane,sphere).findIntersections(new Ray(new Point(0,0,1),new Vector(0,0,-1))).size(),3,"ERROR: geometries find intersectoins not all shapse inersacte, wrong value");
    }
}
