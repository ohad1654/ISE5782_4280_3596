package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Point;

import java.awt.*;

public class Scene {
    public final String sceneName;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    public Scene(String sceneName) {
        this.sceneName = sceneName;

    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
}
