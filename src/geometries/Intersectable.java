package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;


public abstract class Intersectable {
    /**
     * the intersection between the ray and the a gemotry
     * @param ray a ray
     * @return list of Point
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    public static class GeoPoint {
        public  Geometry geometry;
        public  Point point;

        public GeoPoint() {
        }

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof Point)) return false;
            GeoPoint other = (GeoPoint) o;
            return this.point.equals(other.point) && this.geometry.equals(other.geometry);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }



    }
    /**
     * the intersection between the ray and a geometry
     * @param ray a ray
     * @return list of GeoPoint
     */
    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }
    /**
     * the intersection between the ray and a geometry
     * @param ray a ray
     * @return list of GeoPoint
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
