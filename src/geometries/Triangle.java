package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
    @Override
    public List<Point> findIntersections(Ray ray)
    {
        List intersection = plane.findIntersections(ray);
        if(intersection == null)
            return null;
        Vector v1 = vertices.get(0).subtract(ray.getQ0());
        Vector v2 = vertices.get(1).subtract(ray.getQ0());
        Vector v3 = vertices.get(2).subtract(ray.getQ0());
        double n1 = v1.crossProduct(v2).normalize().dotProduct(ray.getDir());
        double n2 = v2.crossProduct(v3).normalize().dotProduct(ray.getDir());
        double n3 = v3.crossProduct(v1).normalize().dotProduct(ray.getDir());
        if(n1 > 0 && n2 > 0 && n3 > 0 || n1 < 0 && n2 < 0 && n3 < 0 )
            return intersection;
        else
            return null;
    }
}
