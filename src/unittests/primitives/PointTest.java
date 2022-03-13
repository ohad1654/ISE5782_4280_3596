package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    public void testAdd() {
        Point a = new Point(1,2,3);

        assertEquals(a.add(new Vector(2,4,6)),new Point(3,6,9),"Bad Point.add");

    }

    @Test
    public void testSubtract()
    {
        Point a = new Point(2,4,6);
        assertEquals(a.subtract(new Point(1,2,3)),new Vector(1,2,3),"Bad Point.subtract");
        assertEquals(a.subtract(new Point(5,5,5)),new Vector(-3,-1,1),"Bad Point.subtract");
    }

    @Test
    public void testDistanceSquared()
    {
        Point a = new Point(1,2,3);
        assertEquals(a.distanceSquared(a),0,"Bad Point.DistanceSquared");
        assertEquals(a.distanceSquared(new Point(-1,2,3)),4,"Bad Point.DistanceSquared");

    }

    @Test
    public void testDistance() {
        Point a = new Point(1,2,3);
        assertEquals(a.distance(a),0,"Bad Point.DistanceSquared");
        assertEquals(a.distance(new Point(-1,2,3)),2,"Bad Point.DistanceSquared");

    }


}