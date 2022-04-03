package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight
{
    private Color intensity;
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    public AmbientLight(Color color, Double3 rgb) {
        intensity = color.scale(rgb);
    }

    public Color getIntensity() {
        return intensity;
    }

}
