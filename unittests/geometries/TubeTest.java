package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    public void testGetNormal() {
        Tube tube = new Tube (new Ray(new Point(0,0,0),new Vector(1,0,0)),1);
        assertEquals(tube.getNormal(new Point(1,0,0.5)),new Vector(1,0,0) , "ERROR: tube Normal vector base worng value");

    }

}