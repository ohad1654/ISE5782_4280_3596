package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    List<Intersectable> shapes;
    public Geometries(Intersectable... geometries)
    {
        shapes= List.of(geometries);
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        LinkedList<Point> intersectPoints = new LinkedList<>();
        List<Point> shapePoint;
        for (Intersectable shape:shapes) {
            shapePoint=shape.findIntersections(ray);
            if (shapePoint!=null)
                intersectPoints.addAll(shapePoint);
        }
        return intersectPoints;
    }
}
