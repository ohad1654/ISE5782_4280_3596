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
    public AmbientLight(Color color, Double3 point)
    {
        intensity = getIntensity();
    }
    private Color getIntensity()
    {
        return null;
    }

}
