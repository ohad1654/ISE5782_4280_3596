package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight
{
    Color intensity;
    public AmbientLight()
    {
        intensity = Color.BLACK;
    }
    public AmbientLight(Color color, Double3 rgb)
    {
        intensity = getIntensity(color,rgb);
    }
    private Color getIntensity(Color color,Double3 point)
    {
        return color.reduce(point);
    }

}
