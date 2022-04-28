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


    /**
     * find the point where the ray intersacte with object
     * @param ray the ray
     * @return the color that the pixel shuld be peinted by
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of point from ray
     * @param intersection the point to calculate the color on
     * @param ray the ray that 'looking' at the geometry
     * @return the color of the point
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * calculat the Local Effects
     * @param intersection the point to calculate the color on
     * @param ray the ray that 'looking' at the geometry
     * @return the color of the point
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = intersection.geometry.getMaterial();


        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) // sign(nl) == sing(nv)
            {
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(lightIntensity.scale(calcDiffusive(material,nl)) ,
                      lightIntensity.scale( calcSpecular(material, n, l,nl ,v)));
            }
        }

        return color;
    }

    /**
     * calculate the Specular
     * @return the Specular
     */
    private double calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {

        double ln = l.dotProduct(n);
        Vector r = l.add(n.scale(-2*ln)).normalize();
        double vr = v.dotProduct(r);
        if(vr < 0){
            return material.Ks*Math.pow((-1*vr),material.nShininess);
        }
        else return 0;
    }


    /**
     * calculate the diffusive of the matirial
     * @return the diffusive
     */
    private double calcDiffusive(Material material, double nl) {
        if(nl < 0) //calc |nl|  --> abs(nl)
            nl = nl*-1;
        return material.Kd*nl;
    }
}


