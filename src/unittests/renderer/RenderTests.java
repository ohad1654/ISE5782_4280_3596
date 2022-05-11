package unittests.renderer;

import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {
	private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
	private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																												// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																												// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																												// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}

	// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid
	 */
	@Test
	public void basicRenderMultiColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -100), 50),
				// up left
				new Polygon(new Point(-100, 0, -100), new Point(0, 0, -100),new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(GREEN)),
				// down left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(RED)),
				// down right
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
						.setEmission(new Color(BLUE)));

		Camera camera = new Camera(Point.ZERO, new Vector(10, 0, 0), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("color render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}

	@Test
	public void HomeTest() {
		Scene scene = new Scene("Home")//
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //


		scene.geometries.add( //
				new Plane(new Point(0, 0, 0),new Vector(0, 0, 1)).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//new Sphere(new Point(8,0,4),80).setEmission(new Color(BLUE))
				// up left
				 ,new Polygon(new Point(0,0,0),new Point(0,10,0),new Point(0,10,10),new Point(0,0,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(10,0,0),new Point(10,10,0),new Point(10,10,10),new Point(10,0,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(0,10,0),new Point(10,10,0),new Point(10,10,10),new Point(0,10,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(4,-5,0.1),new Point(6,-5,0.1),new Point(6,-10,0.1),new Point(4,-10,0.1)).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

		);
		scene.lights.add(new DirectionalLight(spCL, new Vector(0.3,-1,-0.5)));
		Camera camera = new Camera(new Point(-10,-20,30), new Vector(24, 20, -24), new Vector(72,60 ,122)
		)
				.setVPDistance(20) //
				.setVPSize(30, 30) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		//camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}



}
