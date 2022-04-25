package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray)
    {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if(points == null)
            return scene.background;
        GeoPoint cosets = ray.findClosestGeoPoint(points);
        return calcColor(cosets);
    }
    private Color calcColor(GeoPoint point){
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
    }
}
