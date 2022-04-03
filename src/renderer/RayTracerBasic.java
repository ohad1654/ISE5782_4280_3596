package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray)
    {
        List<Point> points = scene.geometries.findIntersections(ray);
        if(points == null)
            return scene.background;
        Point cosets = ray.findClosestPoint(points);
        return calcColor(cosets);
    }
    private Color calcColor(Point point){
        return scene.ambientLight.getIntensity();
    }
}
