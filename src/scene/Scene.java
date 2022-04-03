package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.awt.*;

public class Scene {
    public final String sceneName;
    public Color background;
    public AmbientLight ambientLight;



    public Geometries geometries;

    public Scene(String sceneName) {
        this.sceneName = sceneName;
        this.background=new Color(java.awt.Color.BLACK);
        ambientLight=new AmbientLight();
        geometries=new Geometries();

    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
