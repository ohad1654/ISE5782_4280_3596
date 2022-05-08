package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

public class Ray {
    private static final double DELTA = 0.1;

    private final Point q0;
    private final Vector dir;

    public Ray(Point q0, Vector dir) {
        this.q0 = q0;
        this.dir = dir.normalize();
    }

    public Ray(Point q0, Vector dir, Vector normal){
        if (dir.dotProduct(normal)>0)
            this.q0=q0.add(normal.scale(DELTA));
        else
            this.q0=q0.add(normal.scale(-DELTA));
        this.dir=dir;
    }


    public Point getQ0() {
        return q0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        return q0.add(dir.scale(t));
    }

    /**
     * find coloset point to ray
     * @param points    list
     * @return  the coloset point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return q0.equals(ray.q0) && dir.equals(ray.dir);
    }

    /**
     * find coloset Geoppoint to ray
     * @param points    list
     * @return  the closet Geopoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points ==null)
            return null;
        if(points.size() == 0)
            return null;
        GeoPoint minPoint = points.get(0);
            double minDst = Double.POSITIVE_INFINITY;
        double tmpDst;
        for (GeoPoint point:points)
        {
            tmpDst = this.q0.distance(point.point);
            if(tmpDst < minDst) {
                minPoint = point;
                minDst = tmpDst;
            }
        }
        return minPoint;
    }
    @Override
    public String toString() {
        return  "q0=" + q0 +
                ", dir=" + dir;
    }
}
