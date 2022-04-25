package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane extends Geometry{
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

   /* @Override
    public List<Point> findIntersections(Ray ray)
    {
        if(this.q0.equals(ray.getQ0()))
            return null;
        if(this.normal.dotProduct(ray.getDir()) == 0)
            return null;
        double sclr = this.normal.dotProduct(this.q0.subtract(ray.getQ0()))/this.normal.dotProduct(ray.getDir());
        if(sclr <=  0 )
            return null;
        return List.of(ray.getQ0().add(ray.getDir().scale(sclr)));
    }*/

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if(this.q0.equals(ray.getQ0()))
            return null;
        if(this.normal.dotProduct(ray.getDir()) == 0)
            return null;
        double sclr = this.normal.dotProduct(this.q0.subtract(ray.getQ0()))/this.normal.dotProduct(ray.getDir());
        if(sclr <=  0 )
            return null;
        return List.of(new GeoPoint(this, ray.getQ0().add(ray.getDir().scale(sclr))));
    }
}
