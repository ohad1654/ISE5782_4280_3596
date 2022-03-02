package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends  Tube
{
    private final double hieght;

    public Cylinder(Ray axisRay, double radius, double hieght) {
        super(axisRay, radius);
        this.hieght = hieght;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    public double getHieght() {
        return hieght;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hieght=" + hieght +
                '}';
    }

}
