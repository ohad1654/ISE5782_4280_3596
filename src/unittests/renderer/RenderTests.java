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

	public Geometries baseTree(Point centerWood,double sizeWood,Color brick,Color leaf)
	{

		Geometries geo = new Geometries();
		geo.add(
		new Polygon(centerWood,centerWood.add(new Vector(sizeWood,0,0)),centerWood.add(new Vector(sizeWood,0,3*sizeWood)),centerWood.add(new Vector(0,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
			,new Polygon(centerWood,centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.25)).setKs(new Double3(0.25)).setShininess(20))
			,new Polygon(centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
			,new Polygon(centerWood.add(new Vector(sizeWood,0,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(sizeWood,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

			,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood/2,4*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(1)).setShininess(100))
			,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood,4*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
			,new Sphere(centerWood.add(new Vector(-sizeWood/3,-sizeWood/2,3.5*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
			,new Sphere(centerWood.add(new Vector(-sizeWood/3,-1.5*sizeWood,3.5*sizeWood)),2.7).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
		);
		return  geo;
	}
	@Test
	public void HomeTest() {
		Scene scene = new Scene("Home").setBackground(new Color(153,204,255))
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

		Color brick = new Color(163,53,18);
		Point centerPool = new Point(20,-15,0.001);
		Point centerWood = new Point (22,-6,0.001);
		int sizeWood = 2;
		Color colorPool = new Color(0,102,255);
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

				,new Circle((centerPool.add(new Vector(0,0,0.00001))),5.7,new Plane(new Point(0, 0, 0.000001),new Vector(0, 0, 1))).setEmission(new Color(9,34,6)).setMaterial(new Material().setKd(new Double3(0.2)).setShininess(100))
				,new Circle((centerPool.add(new Vector(0,0,0.0001))),5,new Plane(new Point(0, 0, 0.00001),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(15,15,0))).setMaterial(new Material().setKd(new Double3(0.07)).setKs(new Double3(0.8)).setShininess(100).setKr(new Double3(0.2)))
				,new Circle((centerPool.add(new Vector(0,0,0.0002))),4,new Plane(new Point(0, 0, 0.00002),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(10,10,0))).setMaterial(new Material().setKd(new Double3(0.04)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.25)))
				,new Circle((centerPool.add(new Vector(0,0,0.0003))),3,new Plane(new Point(0, 0, 0.00003),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(05,05,0))).setMaterial(new Material().setKd(new Double3(0.06)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))
				,new Circle((centerPool.add(new Vector(0,0,0.0004))),2,new Plane(new Point(0, 0, 0.00004),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(0,0,0))).setMaterial(new Material().setKd(new Double3(0.03)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))

				//,new Circle((centerPool.add(new Vector(0,0,0.0004))),2,new Plane(new Point(0, 0, 0.00004),new Vector(0, 0, 1))).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0)).setKs(new Double3(0)).setShininess(000).setKr(new Double3(0.5)))

				,new Polygon(new Point(8,0,0.1),new Point(10,0,0.1),new Point(10,-20,0.1),new Point(8,-20,0.1)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(8,0,0.1),new Point(8,-20,0.1),new Point(8,-20,2),new Point(8,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(10,0,0.1),new Point(10,-20,0.1),new Point(10,-20,2),new Point(10,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(8,0,2),new Point(10,0,2),new Point(10,-20,2),new Point(8,-20,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				,new Polygon(new Point(2,0,0.1),new Point(0,0,0.1),new Point(0,-20,0.1),new Point(2,-20,0.1)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(2,0,0.1),new Point(2,-20,0.1),new Point(2,-20,2),new Point(2,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(0,0,0.1),new Point(0,-20,0.1),new Point(0,-20,2),new Point(0,0,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(new Point(2,0,2),new Point(0,0,2),new Point(0,-20,2),new Point(2,-20,2)).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,baseTree(centerWood.add(new Vector(4,-8,0)),sizeWood+0.5,new Color(10,40,60),new Color(10,40,60))
				,baseTree(centerWood.add(new Vector(4,-8,0)),sizeWood+0.5,new Color(10,40,60),new Color(10,140,220))
				,baseTree(centerWood.add(new Vector(-10,-20,0)),sizeWood+0.7,new Color(00,40,60),new Color(00,140,60))
				,baseTree(centerWood.add(new Vector(10,-30,0)),sizeWood+0.2,new Color(10,90,00),new Color(100,90,0))
				,baseTree(centerWood.add(new Vector(15,-8,0)),sizeWood-0.5,new Color(100,00,60),new Color(0,110,60))
				,baseTree(centerWood.add(new Vector(4,-17,0)),sizeWood+0.5,new Color(190,40,250),new Color(67,223,180))
				,baseTree(centerWood.add(new Vector(20,30,0)),sizeWood-0.5,new Color(100,240,160),new Color(100,240,160))
				//,baseTree(centerWood.add(new Vector(10,10,0)),sizeWood-4)


				/*,new Polygon(centerWood,centerWood.add(new Vector(sizeWood,0,0)),centerWood.add(new Vector(sizeWood,0,3*sizeWood)),centerWood.add(new Vector(0,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(centerWood,centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.25)).setKs(new Double3(0.25)).setShininess(20))
				,new Polygon(centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(centerWood.add(new Vector(sizeWood,0,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(sizeWood,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood/2,4*sizeWood)),3).setEmission(new Color(30,130,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(1)).setShininess(100))
				,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood,4*sizeWood)),3).setEmission(new Color(30,130,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Sphere(centerWood.add(new Vector(-sizeWood/3,-sizeWood/2,3.5*sizeWood)),3).setEmission(new Color(30,130,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Sphere(centerWood.add(new Vector(-sizeWood/3,-1.5*sizeWood,3.5*sizeWood)),2.7).setEmission(new Color(30,130,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				*/


				//,new Sphere(centerWood.add(new Vector(0,-2*sizeWood,3.5*sizeWood)),sizeWood/2).setEmission(new Color(30,100,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//,new Sphere(centerWood.add(new Vector(-sizeWood/2,-sizeWood,3.5*sizeWood)),sizeWood/1.5).setEmission(new Color(30,160,0)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
		);
		scene.lights.add(new DirectionalLight(spCL, new Vector(10,20,-15)));
		//scene.lights.add(new SpotLight(new Color(WHITE),new Point(5,0,5),new Vector(0,0,-1)));

		Camera camera = new Camera(centerWood.add(new Vector(-30,-30,20)), new Vector(30, 30, -20), new Vector(30,30 ,90)
		//Camera camera = new Camera(new Point(5,5,30), new Vector(0, 0, -1), new Vector(0,1 ,0)
		)
				.setVPDistance(10) //
				.setVPSize(40, 40) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		//camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}



}
