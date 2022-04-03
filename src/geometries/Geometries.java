package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private List<Intersectable> geometries;
    public Geometries(Intersectable... geometries)
    {
        this.geometries = new LinkedList(List.of(geometries));
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        LinkedList<Point> intersectPoints=null;
        List<Point> shapePoint;
        for (Intersectable shape: geometries) {
            shapePoint=shape.findIntersections(ray);
            if (shapePoint!=null) {
                if (intersectPoints == null)
                    intersectPoints = new LinkedList<>();
                intersectPoints.addAll(shapePoint);
            }
        }
        return intersectPoints;
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries,geometries);
    }
}
