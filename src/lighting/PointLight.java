package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{
    protected final Point position;
    private double Kc = 1, Kl = 0, Kq = 0;


    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setKc(double kC) {
        this.Kc = kC;
        return this;
    }

    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

    /**
     * the intensity for point in point light
     * @param p point
     * @return  the color of intensity
     */
    @Override
    public Color getIntensity(Point p) {

        return getIntensity().reduce(distance(p));
    }

    /**
     * the mechane on the calculation
     * @param p point
     * @return the intensity by distance
     */
    protected double distance(Point p)
    {
        double d = p.distance(position);
        return Kc + Kl * d+ Kq * d *d;
    }
    /**
     * return the vector between points
     * @param p point
     * @return vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
