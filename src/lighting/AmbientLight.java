package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light
{

    public AmbientLight() {
        super(Color.BLACK);
    }

    public AmbientLight(Color color, Double3 rgb) {
        super(color.scale(rgb));
    }



}
