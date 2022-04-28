package geometries;

import primitives.*;

public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    public Material getMaterial() {
        return material;
    }


    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Color getEmission() {
        return emission;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * return the vector between points
     * @param point point
     * @return vector
     */
    public abstract Vector getNormal(Point point);
}
