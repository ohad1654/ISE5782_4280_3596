package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    public void testAdd()
    {

    }

    @Test
    public void testSubtract() {
    }

    @Test
    public void testScaling() {
    }

    @Test
    public void testDotProduct() {
    }

    @Test
    public void testLength() {
    }

    @Test
    public void testLengthSquared() {
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
    }


}