package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.awt.Color.WHITE;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private static final double DELTA = 0.1;

    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    private Color calcColor(GeoPoint point){
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
    }

    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
    }
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = intersection.geometry.getMaterial();
        for (LightSource lightSource : scene.lights)
        {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0)
            { // sign(nl) == sing(nv)
                if (unshaded(intersection, l,lightSource))
                {
                    Color iL = lightSource.getIntensity(intersection.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }


    private double calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {

        double ln = l.dotProduct(n);
        Vector r = l.add(n.scale(-2*ln)).normalize();
        double vr = v.dotProduct(r);


        if(-vr > 0){
            double x = material.Ks*Math.pow((-1*vr),material.nShininess);
            return x;
        }
        else return 0;
    }


    private double calcDiffusive(Material material, double nl) {
        if(nl < 0) //calc |nl|  --> abs(nl)
            nl = nl*-1;
        return material.Kd*nl;
    }
    private boolean unshaded(GeoPoint gp, Vector l,LightSource light) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if(intersections == null)
            return true ;
        for (GeoPoint point: intersections)
        {
            if(point.point.distance(gp.point) < light.getDistance(gp.point))
            {
                return false;
            }
            
        }
        return true;
    }
}


