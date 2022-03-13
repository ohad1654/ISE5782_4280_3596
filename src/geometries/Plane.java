package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry{
    private final Point q0;
    private final Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point point1, Point point2, Point point3) {
        if(point1.equals(point2))
            throw new IllegalArgumentException("points must be different");
        Vector v1=point2.subtract(point1);
        Vector v2=point3.subtract(point1);
        if (v1.normalize().equals(v2.normalize()))
            throw new IllegalArgumentException("Points must not be on the same line");
        q0=point1;
        normal=v1.crossProduct(v2).normalize();
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
