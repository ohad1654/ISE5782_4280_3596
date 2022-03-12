package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends  Tube
{
    private final double hieght;

    public Cylinder(Ray axisRay, double radius, double hieght) {
        super(axisRay, radius);
        this.hieght = hieght;
    }

    @Override
    public Vector getNormal(Point point) {
        if(point.equals(getAxisRay().getQ0())|| point.subtract(getAxisRay().getQ0()).length()<getRadius()) //point at the top base
            return getAxisRay().getDir();
        Point btm_point=getAxisRay().getQ0().add(getAxisRay().getDir().scale(getHieght()));
        if(point.equals(btm_point)|| point.subtract(btm_point).length()<getRadius()) //point at the bottom base
            return getAxisRay().getDir();

        return super.getNormal(point);
    }

    public double getHieght() {
        return hieght;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hieght=" + hieght +
                '}';
    }

}
