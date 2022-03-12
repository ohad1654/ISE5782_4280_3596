package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    public void testGetNormal() {
        Cylinder cylinder = new Cylinder (new Ray(new Point(0,0,0),new Vector(0,0,1)),1,5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point at bottom base
        assertEquals(cylinder.getNormal(new Point(0,0,0)),new Vector(0,0,1) , "ERROR: Cylinder Normal vector bottom base worng value");
        // TC02: Point at top base
        assertEquals(cylinder.getNormal(new Point(0,0,5)),new Vector(0,0,1) , "ERROR: Cylinder Normal vector top base worng value");
        // TC03: Point at side
        assertEquals(cylinder.getNormal(new Point(1,0,1)),new Vector(1,0,0) , "ERROR: Cylinder Normal vector worng value");
        // =============== Boundary Values Tests ==================
        // TC04: Point at center bottom base
        assertEquals(cylinder.getNormal(new Point(0,0,0)),new Vector(0,0,1) , "ERROR: Cylinder Normal vector worng value");
        // TC05: Point at center top base
        assertEquals(cylinder.getNormal(new Point(0,0,5)),new Vector(0,0,1) , "ERROR: Cylinder Normal vector worng value");

    }

}