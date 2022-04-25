package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.awt.Color.WHITE;

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
        Color color = calcColor(cosets);
        if (color != Color.BLACK && !color.equals(new Color(WHITE)))
        {
            int a=1;
        }
        return color;
    }
    private Color calcColor(GeoPoint point){
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
    }
}
