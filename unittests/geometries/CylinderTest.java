package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    public void testGetNormal() {
        Cylinder cylinder = new Cylinder (new Ray(new Point(0,0,0),new Vector(1,0,0)),1,5);
        assertEquals(cylinder.getNormal(new Point(0,0,0)),new Vector(1,0,0) , "ERROR: Cylinder Normal vector base worng value");
        assertEquals(cylinder.getNormal(new Point(1,0,1)),new Vector(1,0,0) , "ERROR: Cylinder Normal vector base worng value");
    }

}