package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {
    /**
     * the intensity of lighting soirces
     * @param p point
     * @return color of intensity
     */
    public Color getIntensity(Point p);

    /**
     * the vector between the light and point
     * @param p the point
     * @return  a vector
     */
    public Vector getL(Point p);

    double getDistance(Point point);
}
