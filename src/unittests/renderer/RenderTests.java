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

		Color brick = new Color(163,53,18);
		scene.geometries.add( //
				new Plane(new Point(0, 0, 0),new Vector(0, 0, 1)).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//new Sphere(new Point(8,0,4),80).setEmission(new Color(BLUE))
				// up left
				 ,new Polygon(new Point(0,0,0),new Point(0,10,0),new Point(0,10,10),new Point(0,0,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(10,0,0),new Point(10,10,0),new Point(10,10,10),new Point(10,0,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(0,10,0),new Point(10,10,0),new Point(10,10,10),new Point(0,10,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//entry road
				,new Polygon(new Point(4,-5,0.1),new Point(6,-5,0.1),new Point(6,-10,0.1),new Point(4,-10,0.1)).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//pol1
				,new Polygon(Point.ZERO,new Point(0,0,6),new Point(10,0,6), new Point(10,0,0)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//pol2
				,new Polygon(new Point(0,0,8),new Point(0,0,10), new Point(10,0,10),new Point(10,0,8)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//pol3
				,new Polygon(new Point(0,0,6),new Point(0,0,8),new Point(2,0,8),new Point(2,0,6)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100)),
				//pol4
				new Polygon(new Point(4,0,6),new Point(4,0,8),new Point(6,0,8),new Point(6,0,6)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100)),
				//pol5
				new Polygon(new Point(8,0,6),new Point(8,0,8),new Point(10,0,8),new Point(10,0,6)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100)),
				//window1
				new Polygon(new Point(2,0,6),new Point(2,0,8),new Point(4,0,8),new Point(4,0,6)).setMaterial(new Material().setKt(new Double3(1))),
				//window2
				new Polygon(new Point(6,0,6),new Point(6,0,8),new Point(8,0,8),new Point(8,0,6)).setMaterial(new Material().setKt(new Double3(1)))
				,new Polygon(new Point(0,0,0),new Point(10,0,0),new Point(10,0,10),new Point(0,0,10)).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				,new Polygon(new Point(2,0,0.1),new Point(8,0,0.1),new Point(8,-20,0.1),new Point(2,-20,0.1)).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				,new Circle(new Point(17,-10,0.1),5,new Plane(new Point(0, 0, 0.1),new Vector(0, 0, 1))).setEmission(new Color(0,109,236)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(200))
				,new Circle(new Point(17,-10,0.2),4,new Plane(new Point(0, 0, 0.2),new Vector(0, 0, 1))).setEmission(new Color(0,90,236)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(200))
				,new Circle(new Point(17,-10,0.3),3,new Plane(new Point(0, 0, 0.3),new Vector(0, 0, 1))).setEmission(new Color(0,70,236)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(200))
				,new Circle(new Point(17,-10,0.4),2,new Plane(new Point(0, 0, 0.4),new Vector(0, 0, 1))).setEmission(new Color(0,50,236)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(200))

				,new Polygon(new Point(8,0,0.1),new Point(10,0,0.1),new Point(10,-20,0.1),new Point(8,-20,0.1)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(8,0,0.1),new Point(8,-20,0.1),new Point(8,-20,2),new Point(8,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(10,0,0.1),new Point(10,-20,0.1),new Point(10,-20,2),new Point(10,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(8,0,2),new Point(10,0,2),new Point(10,-20,2),new Point(8,-20,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				,new Polygon(new Point(2,0,0.1),new Point(0,0,0.1),new Point(0,-20,0.1),new Point(2,-20,0.1)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(2,0,0.1),new Point(2,-20,0.1),new Point(2,-20,2),new Point(2,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(0,0,0.1),new Point(0,-20,0.1),new Point(0,-20,2),new Point(0,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(2,0,2),new Point(0,0,2),new Point(0,-20,2),new Point(2,-20,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100)));
		scene.lights.add(new DirectionalLight(spCL, new Vector(10,20,-15)));
		//scene.lights.add(new SpotLight(new Color(WHITE),new Point(5,0,5),new Vector(0,0,-1)));

		Camera camera = new Camera(new Point(-10,-20,30), new Vector(24, 20, -24), new Vector(72,60 ,122)
		//Camera camera = new Camera(new Point(5,5,30), new Vector(0, 0, -1), new Vector(0,1 ,0)
		)
				.setVPDistance(40) //
				.setVPSize(40, 40) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		//camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}



}
