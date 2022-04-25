package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{
    private final Point position;
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

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return getIntensity().reduce(Kc+ Kl *d+ Kq *d*d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
