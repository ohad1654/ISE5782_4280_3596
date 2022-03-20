package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    LinkedList<Intersectable> shapes;
    public Geometries(Intersectable... geometries)
    {
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
