package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {


    public List<LightSource> lights = new LinkedList<>();
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
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
