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


    @Override
    /*public Color traceRay(Ray ray)
    {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if(points == null)
            return scene.background;
        GeoPoint cosets = ray.findClosestGeoPoint(points);
        return calcColor(cosets);
    }*/
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
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();

        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(lightIntensity.scale(calcDiffusive(material,nl)) ,
                      lightIntensity.scale( calcSpecular(material, n, l,nl ,v)));
            }
        }

        return color;
    }

    private double calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        //first, we calc r, the specular vector
        double ln = l.dotProduct(n);
        Vector r = l.add(n.scale(-2*ln)).normalize();
        double vr = v.dotProduct(r);

        //now we calc specular effect
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
}


