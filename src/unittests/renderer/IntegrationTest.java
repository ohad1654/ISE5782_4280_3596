package unittests.renderer;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import geometries.*;
import primitives.*;
import renderer.Camera;

import java.util.List;

public class IntegrationTest {
    static final int x=3;
    static final int y=3;
    int countIntersections(Camera camera, Intersectable geometry){
        int counter=0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Ray ray=camera.constructRay(x,y,j,i);
                List<Point> points=geometry.findIntersections(ray);
                if (points!=null)
                    counter+=points.size();
            }
        }
        return counter;
    }
    @Test
    void testSphereIntegration(){
        Camera camera=new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPSize(x,y).setVPDistance(1);
        Sphere sphere=new Sphere(new Point(0,0,-3),1);
        assertEquals(2, countIntersections(camera,sphere));

        camera=new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0)).setVPSize(x,y).setVPDistance(1);
        sphere=new Sphere(new Point(0,0,-2.5),2.5);
        assertEquals(18, countIntersections(camera,sphere));

        sphere=new Sphere(new Point(0,0,-2),2);
        assertEquals(10, countIntersections(camera,sphere));

        sphere=new Sphere(new Point(0,0,-0.5),4);
        assertEquals(9, countIntersections(camera,sphere));

        sphere=new Sphere(new Point(0,0,1),0.5);
        assertEquals(0, countIntersections(camera,sphere));
    }


    @Test
    void testPlaneIntegration(){
        Camera camera=new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPSize(x,y).setVPDistance(1);
        Plane plane=new Plane(new Point(0,0,-3),new Vector(0,0,1));
        assertEquals(9, countIntersections(camera,plane));

        plane=new Plane(new Point(0,0,-3),new Vector(0,-0.5,-1));
        assertEquals(9, countIntersections(camera,plane));

        plane=new Plane(new Point(0,0,-3),new Vector(0,-1.5,-1));
        assertEquals(6, countIntersections(camera,plane));
    }

    @Test
    void testTriangleIntegration() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(x, y).setVPDistance(1);
        Triangle triangle=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(1, countIntersections(camera,triangle));

        triangle=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(2, countIntersections(camera,triangle));
    }
}
