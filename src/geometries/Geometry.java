package geometries;

import primitives.*;

public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    public Material getMaterial() {
        return material;
    }

    private Material material = new Material();

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

    public abstract Vector getNormal(Point point);
}
