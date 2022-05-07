package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource
{
    private Vector direction;

    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }
    /**
     * intensity for spot light
     * @param p point we want to calculate intensity
     * @return the intensity
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }
    /**
     * return the vector between points
     * @param p point
     * @return vector
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }


}
