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
    int countIntersactions(Camera camera, Intersectable geometry){
        int counter=0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                List<Point> points=geometry.findIntersections(camera.constructRay(x,y,j,i));
                if (points!=null)
                    counter+=points.size();
            }
        }
        return counter;
    }
    @Test
    void testSphereIntegration(){
        Camera camera=new Camera(new Point(0,0,0),new Vector(0,1,0),new Vector(0,0,-1)).setVPSize(x,y).setVPDistance(1);
        Sphere sphere=new Sphere(new Point(0,0,-3),1);
        assertEquals(2,countIntersactions(camera,sphere));

        camera=new Camera(new Point(0,0,0.5),new Vector(0,1,0),new Vector(0,0,-1)).setVPSize(x,y).setVPDistance(1);
        sphere=new Sphere(new Point(0,0,-2.5),2.5);
        assertEquals(18,countIntersactions(camera,sphere));

        sphere=new Sphere(new Point(0,0,-2),2);
        assertEquals(10,countIntersactions(camera,sphere));

        sphere=new Sphere(new Point(0,0,-0.5),4);
        assertEquals(9,countIntersactions(camera,sphere));

        sphere=new Sphere(new Point(0,0,1),0.5);
        assertEquals(0,countIntersactions(camera,sphere));
    }


    @Test
    void testPlaneIntegration(){
        Camera camera=new Camera(new Point(0,0,0),new Vector(0,1,0),new Vector(0,0,-1)).setVPSize(x,y).setVPDistance(1);
        Plane plane=new Plane(new Point(0,0,-3),new Vector(0,0,1));
        assertEquals(9,countIntersactions(camera,plane));

        plane=new Plane(new Point(0,0,-3),new Vector(0,-0.5,-1));
        assertEquals(9,countIntersactions(camera,plane));

        plane=new Plane(new Point(0,0,-3),new Vector(0,-1.5,-1));
        assertEquals(6,countIntersactions(camera,plane));
    }

    @Test
    void testTriangleIntegration() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)).setVPSize(x, y).setVPDistance(1);
        Triangle triangle=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(1,countIntersactions(camera,triangle));

        triangle=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(2,countIntersactions(camera,triangle));
    }
}
