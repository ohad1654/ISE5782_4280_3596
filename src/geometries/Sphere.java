package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }


}
