package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends Geometry
{
    private final Ray axisRay;
    private final double radius;
    /**
     * the normal between point and the tube
     * @param point the point
     * @return  the normal vector
     */

    @Override
    public Vector getNormal(Point point) {
        double t=axisRay.getDir().dotProduct(point.subtract(axisRay.getQ0()));
        if(t==0)
            return point.subtract(getAxisRay().getQ0()).normalize();
        Point o=axisRay.getQ0().add(axisRay.getDir().scale(t));
        return point.subtract(o).normalize();
    }

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
 }

    /*@Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }*/

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
