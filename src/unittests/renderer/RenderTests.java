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

	public Geometries baseBench(Point point)
	{
		double size = 3;
		Color brown = new Color(240,157,12).scale(0.8);
		Geometries geo = new Geometries();
		geo.add(
				baseRectangle(
						point
						,point.add(new Vector(0,2,0))
						,point.add(new Vector(1,2,0))
						,point.add(new Vector(1,0,0))
						,point.add(new Vector(0,1,1))
						,point.add(new Vector(0,3,1))
						,point.add(new Vector(1,3,1))
						,point.add(new Vector(1,1,1)),brown)
				,baseRectangle(
						point.add(new Vector(5,0,0))
						,point.add(new Vector(5,2,0))
						,point.add(new Vector(6,2,0))
						,point.add(new Vector(6,0,0))
						,point.add(new Vector(5,1,1))
						,point.add(new Vector(5,3,1))
						,point.add(new Vector(6,3,1))
						,point.add(new Vector(6,1,1)),brown)


				,baseRectangle(
						point.add(new Vector(0,1,1)),
						point.add(new Vector(0,1,1.2)),
						point.add(new Vector(0,1.5,1.2)),
						point.add(new Vector(0,1.5,1)),
						point.add(new Vector(6,1,1)),
						point.add(new Vector(6,1,1.2)),
						point.add(new Vector(6,1.5,1.2)),
						point.add(new Vector(6,1.5,1)),brown)

				,baseRectangle(
						point.add(new Vector(0,1.8,1)),
						point.add(new Vector(0,1.8,1.2)),
						point.add(new Vector(0,2.1,1.2)),
						point.add(new Vector(0,2.1,1)),
						point.add(new Vector(6,1.8,1)),
						point.add(new Vector(6,1.8,1.2)),
						point.add(new Vector(6,2.1,1.2)),
						point.add(new Vector(6,2.1,1)),brown)

				,baseRectangle(
						point.add(new Vector(0,2.3,1)),
						point.add(new Vector(0,2.3,1.2)),
						point.add(new Vector(0,2.8,1.2)),
						point.add(new Vector(0,2.8,1)),
						point.add(new Vector(6,2.3,1)),
						point.add(new Vector(6,2.3,1.2)),
						point.add(new Vector(6,2.8,1.2)),
						point.add(new Vector(6,2.8,1)),brown)

				,baseRectangle(
						point.add(new Vector(0,2.8,1.3)),
						point.add(new Vector(0,2.8,2)),
						point.add(new Vector(0,3.1,2)),
						point.add(new Vector(0,3.1,1.3)),
						point.add(new Vector(6,2.8,1.3)),
						point.add(new Vector(6,2.8,2)),
						point.add(new Vector(6,3.1,2)),
						point.add(new Vector(6,3.1,1.3)),brown)

						,baseRectangle(
						point.add(new Vector(0,2.8,2.3)),
						point.add(new Vector(0,2.8,3)),
						point.add(new Vector(0,3.1,3)),
						point.add(new Vector(0,3.1,2.3)),
						point.add(new Vector(6,2.8,2.3)),
						point.add(new Vector(6,2.8,3)),
						point.add(new Vector(6,3.1,3)),
						point.add(new Vector(6,3.1,2.3)),brown)

						,baseRectangle(
						point.add(new Vector(2,3.2,0.8)),
						point.add(new Vector(2,3.4,0.8)),
						point.add(new Vector(2.5,3.4,0.8)),
						point.add(new Vector(2.5,3.2,0.8)),
						point.add(new Vector(2,3.2,2.5)),
						point.add(new Vector(2,3.4,2.5)),
						point.add(new Vector(2.5,3.4,2.5)),
						point.add(new Vector(2.5,3.2,2.5))
						,brown)

						,baseRectangle(
						point.add(new Vector(4,3.2,0.8)),
						point.add(new Vector(4,3.4,0.8)),
						point.add(new Vector(4.5,3.4,0.8)),
						point.add(new Vector(4.5,3.2,0.8)),
						point.add(new Vector(4,3.2,2.5)),
						point.add(new Vector(4,3.4,2.5)),
						point.add(new Vector(4.5,3.4,2.5)),
						point.add(new Vector(4.5,3.2,2.5))
						,brown)

		);
		return geo;
	}



	public Geometries baseRectangle(Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7,Point p8, Color mainColor)
	{
		Geometries geo = new Geometries();
		geo.add(
				new Polygon(p1,p4,p8,p5).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(p1,p5,p6,p2).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(p4,p8,p7,p3).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(p2,p3,p7,p6).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(p1,p2,p3,p4).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(p5,p6,p7,p8).setEmission(mainColor).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
		);


		return geo;
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
				new Plane(new Point(0, 0, 0),new Vector(0, 0, 1)).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100)),
				createHome(new Point(0,0,0))
				,new Circle((centerPool.add(new Vector(0,0,0.00001))),5.7,new Plane(new Point(0, 0, 0.000001),new Vector(0, 0, 1))).setEmission(new Color(9,34,6)).setMaterial(new Material().setKd(new Double3(0.2)).setShininess(100))
				,new Circle((centerPool.add(new Vector(0,0,0.0001))),5,new Plane(new Point(0, 0, 0.00001),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(15,15,0))).setMaterial(new Material().setKd(new Double3(0.07)).setKs(new Double3(0.8)).setShininess(100).setKr(new Double3(0.2)))
				,new Circle((centerPool.add(new Vector(0,0,0.0002))),4,new Plane(new Point(0, 0, 0.00002),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(10,10,0))).setMaterial(new Material().setKd(new Double3(0.04)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.25)))
				,new Circle((centerPool.add(new Vector(0,0,0.0003))),3,new Plane(new Point(0, 0, 0.00003),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(05,05,0))).setMaterial(new Material().setKd(new Double3(0.06)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))
				,new Circle((centerPool.add(new Vector(0,0,0.0004))),2,new Plane(new Point(0, 0, 0.00004),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(0,0,0))).setMaterial(new Material().setKd(new Double3(0.03)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))
				,baseBench(new Point(15,-12,0.0001))

				,baseTree(centerWood.add(new Vector(4,-8,0)),sizeWood+0.5,new Color(10,40,60),new Color(10,40,60))
				,baseTree(centerWood.add(new Vector(4,-8,0)),sizeWood+0.5,new Color(10,40,60),new Color(10,140,220))

		);
		scene.lights.add(new DirectionalLight(spCL, new Vector(10,20,-15)));
		Camera camera = new Camera(new Point(-15,-15,20), new Vector(24, 20, -15), new Vector(24*15,20*15 ,976)
		//Camera camera = new Camera(new Point(5,10,40), new Vector(0, 0, -1), new Vector(0,1 ,0)
		)
				.setVPDistance(40) //
				.setVPSize(40, 40) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		//camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}
	private Geometries createHome(Point position){
		double roof_height=15;

		return new Geometries(
				new Polygon(position,position.add(new Vector(0,10,0)),position.add(new Vector(0,10,10)),position.add(new Vector(0,0,10))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))//.setKs(new Double3(0.2)).setShininess(100))
				,new Polygon(position.add(new Vector(10,0,0)),position.add(new Vector(10,10,0)),position.add(new Vector(10,10,10)),position.add(new Vector(10,0,10))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(0,10,0)),position.add(new Vector(10,10,0)),position.add(new Vector(10,10,10)),new Point(0,10,10)).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//pol1a
				,new Polygon(position,position.add(new Vector(0,0,5)),position.add(new Vector(3.5,0,5)), position.add(new Vector(3.5,0,0))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//pol1b0
				,new Polygon(position.add(new Vector(6.5,0,0)),position.add(new Vector(6.5,0,5)),position.add(new Vector(10,0,5)),position.add( new Vector(10,0,0))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//pol1c
				,new Polygon(position.add(new Vector(0,0,5)),position.add(new Vector(0,0,6)),position.add(new Vector(10,0,6)), position.add(new Vector(10,0,5))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//pol2
				,new Polygon(position.add(new Vector(0,0,8)),position.add(new Vector(0,0,10)), position.add(new Vector(10,0,10)),position.add(new Vector(10,0,8))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//pol3
				,new Polygon(position.add(new Vector(0,0,6)),position.add(new Vector(0,0,8)),position.add(new Vector(2,0,8)),position.add(new Vector(2,0,6))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//pol4
				new Polygon(position.add(new Vector(4,0,6)),position.add(new Vector(4,0,8)),position.add(new Vector(6,0,8)),position.add(new Vector(6,0,6))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//pol5
				new Polygon(position.add(new Vector(8,0,6)),position.add(new Vector(8,0,8)),position.add(new Vector(10,0,8)),position.add(new Vector(10,0,6))).setEmission(new Color(BLUE).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//window1
				new Polygon(position.add(new Vector(2,0,6)),position.add(new Vector(2,0,8)),position.add(new Vector(4,0,8)),position.add(new Vector(4,0,6))).setMaterial(new Material().setKt(new Double3(1)).setKr(new Double3(0.2)).setShininess(1000)),
				//window2
				new Polygon(position.add(new Vector(6,0,6)),position.add(new Vector(6,0,8)),position.add(new Vector(8,0,8)),position.add(new Vector(8,0,6))).setMaterial(new Material().setKt(new Double3(1)).setKr(new Double3(0.2)).setShininess(1000))
				//door
				,new Polygon(position.add(new Vector(6.5,0,0)),position.add(new Vector(6.5,0,5)),position.add(new Vector(3.5,-3,5)),position.add(new Vector(3.5,-3,0))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(6.4,0.1,0)),position.add(new Vector(6.4,0.1,5)),position.add(new Vector(3.4,-2.9,5)),position.add(new Vector(3.4,-2.9,0))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(6.5,0,5)),position.add(new Vector(6.4,0.1,5)),position.add(new Vector(3.4,-2.9,5)),position.add(new Vector(3.5,-3,5))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(3.5,-3,5)),position.add(new Vector(3.5,-3,0)),position.add(new Vector(3.4,-2.9,0)),position.add(new Vector(3.4,-2.9,5))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Sphere(position.add(new Vector(4,-2.5,2.2)),0.12).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				//roof
				,new Triangle(position.add(new Vector(0,0,10)),position.add(new Vector(10,0,10)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Triangle(position.add(new Vector(0,10,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(0,0,10)),position.add(new Vector(0,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(10,0,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))

				//entry road
				,new Polygon(position.add(new Vector(2,0,0.1)),position.add(new Vector(8,0,0.1)),position.add(new Vector(8,-20,0.1)),position.add(new Vector(2,-20,0.1))).setEmission(new Color(GRAY)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

		);
	}
}
