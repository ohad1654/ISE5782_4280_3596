package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    public void testAdd()
    {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 0, 0).add(new Vector(2, -1, 0)),new Vector(4, -1, 0) , "ERROR:add vector sharp angle worng value");
        assertEquals(new Vector(2, 0, 0).add(new Vector(-2, -1, 0)),new Vector(0, -1, 0) , "ERROR: add vector Obtuse angle worng value");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(2, 0, 0).add(new Vector(2, 0, 0)),new Vector(4, 0, 0) , "ERROR: add vector same  worng value");
        try {
            new Vector(2, 0, 0).add(new Vector(-2, 0, 0));
            fail("ERROR: add opposite vectors return zero vector");
        }catch (Exception e){}
        assertEquals(new Vector(2, 0, 0).add(new Vector(-4, 0, 0)),new Vector(-2, 0, 0) , "ERROR: add opposite vectors  worng value");
        assertEquals(new Vector(2, 0, 0).add(new Vector(1, 0, 0)),new Vector(3, 0, 0) , "ERROR: add parallel vectors  worng value");
        assertEquals(new Vector(1, 0, 0).add(new Vector(0, 0, 1)),new Vector(1, 0, 1) , "ERROR: add orthogonal vectors  worng value");
    }

    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 0, 0).subtract(new Vector(-2, 1, 0)),new Vector(4, -1, 0) , "ERROR:subtract vector sharp angle worng value");
        assertEquals(new Vector(2, 0, 0).subtract(new Vector(2, 1, 0)),new Vector(0, -1, 0) , "ERROR: subtract vector Obtuse angle worng value");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(2, 0, 0).subtract(new Vector(-2, 0, 0)),new Vector(4, 0, 0) , "ERROR: subtract vector same  worng value");
        try {
            new Vector(2, 0, 0).subtract(new Vector(2, 0, 0));
            fail("ERROR: subtract opposite vectors return zero vector");
        }catch (Exception e){}
        assertEquals(new Vector(2, 0, 0).subtract(new Vector(4, 0, 0)),new Vector(-2, 0, 0) , "ERROR: subtract opposite vectors  worng value");
        assertEquals(new Vector(2, 0, 0).subtract(new Vector(-1, 0, 0)),new Vector(3, 0, 0) , "ERROR: subtract parallel vectors  worng value");
        assertEquals(new Vector(1, 0, 0).subtract(new Vector(0, 0, -1)),new Vector(1, 0, 1) , "ERROR: subtract orthogonal vectors  worng value");
    }

    @Test
    public void testScaling() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 0, 0).scale(2),new Vector(4, 0, 0) , "ERROR: scale by positive worng value");
        assertEquals(new Vector(2, 0, 0).scale(-2),new Vector(-4, 0, 0) , "ERROR: scale by negative worng value");

        // =============== Boundary Values Tests ==================
        try {
            new Vector(2, 0, 0).scale(0);
            fail("ERROR: scale by 0 return zero vector");
        }catch (Exception e){}
    }

    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 0, 0).dotProduct(new Vector(-2, 1, 0)),-4 , "ERROR:dotProduct vector sharp angle worng value");
        assertEquals(new Vector(2, 0, 0).dotProduct(new Vector(2, 1, 0)),4 , "ERROR: dotProduct vector Obtuse angle worng value");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(2, 0, 0).dotProduct(new Vector(-2, 0, 0)),-4, "ERROR: dotProduct vector same  worng value");

        assertEquals(new Vector(2, 0, 0).dotProduct(new Vector(4, 0, 0)),8, "ERROR: dotProduct opposite vectors  worng value");
        assertEquals(new Vector(2, 0, 0).dotProduct(new Vector(-1, 0, 0)),-2, "ERROR: dotProduct parallel vectors  worng value");
        assertEquals(new Vector(1, 0, 0).dotProduct(new Vector(0, 0, -1)),0 , "ERROR: dotProduct orthogonal vectors  worng value");
    }

    @Test
    public void testLength() {
        // =============== Boundary Values Tests ==================
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),"ERROR: length() wrong value");

    }

    @Test
    public void testLengthSquared() {
        assertTrue(isZero(new Vector(1, 2, 3).lengthSquared() - 14),"ERROR: lengthSquared() wrong value");
    }


    @Test
    public void testNormalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n), //
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }


    @Test
    public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 0, 0).crossProduct(new Vector(2, -1, 0)),new Vector(0,0,-2) , "ERROR:crossProduct vector sharp angle worng value");
        assertEquals(new Vector(2, 0, 0).crossProduct(new Vector(-2, -1, 0)),new Vector(0,0,-2) , "ERROR: crossProduct vector Obtuse angle worng value");

        // =============== Boundary Values Tests ==================
        try {
            new Vector(2, 0, 0).crossProduct(new Vector(-2, 0, 0));
            fail("ERROR: crossProduct opposite vectors return zero vector");
        }catch (Exception e){}
        try {
            new Vector(2, 0, 0).crossProduct(new Vector(2, 0, 0));
            fail("ERROR: crossProduct same vectors return zero vector");
        }catch (Exception e){}
        assertEquals(new Vector(1, 0, 0).crossProduct(new Vector(0, 0, 1)),new Vector(0, -1, 0) , "ERROR: crossProduct orthogonal vectors  worng value");
    }


}