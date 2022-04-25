package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

public class Sphere extends Geometry {
    private final Point center;
    private final double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

   /* @Override
    public List<Point> findIntersections(Ray ray) {
        if(ray.getQ0().equals(center))
            return List.of(ray.getPoint(radius));

        Vector u = center.subtract(ray.getQ0());
        double tm = ray.getDir().dotProduct(u);
        double d = alignZero(Math.sqrt(u.lengthSquared()-tm*tm));
        if (d >= radius || u.dotProduct(ray.getDir())<0)
            return null;

        List<Point> points;
        double th=Math.sqrt(radius*radius-d*d);
        double t1 = alignZero(tm+th);
        double t2= alignZero(tm-th);
        if(t1 * t2 > 1)
            points= new ArrayList<>(2);
        else
            points = new ArrayList<>(1);

        if(t1 > 0)
            points.add(ray.getPoint(t1));
        if(t2 > 0)
            points.add(ray.getPoint(t2));
        return points;
    }*/

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if(ray.getQ0().equals(center))
            return List.of(new GeoPoint(this,ray.getPoint(radius)));

        Vector u = center.subtract(ray.getQ0());
        double tm = ray.getDir().dotProduct(u);
        double d = alignZero(Math.sqrt(u.lengthSquared()-tm*tm));
        if (d >= radius || u.dotProduct(ray.getDir())<0)
            return null;

        List<GeoPoint> points;
        double th=Math.sqrt(radius*radius-d*d);
        double t1 = alignZero(tm+th);
        double t2= alignZero(tm-th);
        if(t1 * t2 > 1)
            points= new ArrayList<>(2);
        else
            points = new ArrayList<>(1);

        if(t1 > 0)
            points.add(new GeoPoint(this,ray.getPoint(t1)));
        if(t2 > 0)
            points.add(new GeoPoint(this,ray.getPoint(t2)));
        return points;    }
}