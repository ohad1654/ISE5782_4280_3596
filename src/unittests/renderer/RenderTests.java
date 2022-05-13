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

import java.util.Random;

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
				new Plane(new Point(0, 0, 0),new Vector(0, 0, 1)).setEmission(new Color(GREEN).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				createHome(new Point(0,0,0))

		);
		scene.lights.add(new DirectionalLight(new Color(WHITE), new Vector(-10,20,-15)));
		Camera camera = new Camera(new Point(-5,-10,16),new Point(4,9,17)
		)
				.setVPDistance(10) //
				.setVPSize(40, 40) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		//camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}
	private Geometries createHome(Point position){
		final double roof_height=15;
		final double doorAngle=90;
		final double doorAngleRad=Math.toRadians(doorAngle+180);
		final  Vector doorRotateV=new Vector(3*Math.cos(doorAngleRad),3*Math.sin(doorAngleRad),0);
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
				
				,new Polygon(position.add(new Vector(6.5,0,0)),position.add(new Vector(6.5,0,5)),position.add(new Vector(6.5,0,5)).add(doorRotateV),position.add(new Vector(6.5,0,0)).add(doorRotateV)).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(6.4,0.1,0)),position.add(new Vector(6.4,0.1,5)),position.add(new Vector(6.4,0.1,5)).add(doorRotateV),position.add(new Vector(6.4,0.1,0)).add(doorRotateV)).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(6.5,0,5)),position.add(new Vector(6.5,0,5)).add(doorRotateV),position.add(new Vector(6.4,0.1,5)).add(doorRotateV),position.add(new Vector(6.4,0.1,5))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(6.5,0,5)).add(doorRotateV),position.add(new Vector(6.5,0,0)).add(doorRotateV),position.add(new Vector(6.4,0.1,0)).add(doorRotateV),position.add(new Vector(6.4,0.1,5)).add(doorRotateV)).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Sphere(position.add(new Vector(6.5,0,2.2)).add(doorRotateV.scale(0.9)),0.12).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
				//TODO: set handle in place

				//roof
				,new Triangle(position.add(new Vector(0,0,10)),position.add(new Vector(10,0,10)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Triangle(position.add(new Vector(0,10,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(0,0,10)),position.add(new Vector(0,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(10,0,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))

				//entry road
				,new Polygon(position.add(new Vector(2,0,0.1)),position.add(new Vector(8,0,0.1)),position.add(new Vector(8,-20,0.1)),position.add(new Vector(2,-20,0.1))).setEmission(new Color(GRAY)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

				//aruba
				//darom
				,new Polygon(position.add(new Vector(2,9,12)),position.add(new Vector(2,9,17)),position.add(new Vector(4,9,17)),position.add(new Vector(4,9,14))).setEmission(new Color(GRAY).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//maarav
				,new Polygon(position.add(new Vector(4,9,14)),position.add(new Vector(4,9,17)),position.add(new Vector(4,7,17)),position.add(new Vector(4,7,14))).setEmission(new Color(GRAY).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//zafon
				,new Polygon(position.add(new Vector(4,7,14)),position.add(new Vector(4,7,17)),position.add(new Vector(2,7,17)),position.add(new Vector(2,7,12))).setEmission(new Color(GRAY).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				//mizrah
				,new Polygon(position.add(new Vector(2,7,12)),position.add(new Vector(2,7,17)),position.add(new Vector(2,9,17)),position.add(new Vector(2,9,12))).setEmission(new Color(GRAY).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,createSmoke(position.add(new Vector(2,9,17)),position.add(new Vector(2,9,12)).add(new Vector(4,7,23)))
		);
	}
	private Geometries createSmoke(Point start,Point end){
		Geometries smoke=new Geometries();
		for (int i = 0; i <15; i++) {
			double x=Math.random()*(Math.abs(start.getX()-end.getX()))+Math.min(start.getX(),end.getX());
			double y=Math.random()*(Math.abs(start.getY()-end.getY()))+Math.min(start.getY(),end.getY());
			double z=Math.random()*(Math.abs(start.getZ()-end.getZ()))+Math.min(start.getZ(),end.getZ());
			double radius=Math.random()*Math.abs(start.getZ()-end.getZ())*0.1+0.5;
			double color= Math.random()*50+150;
			smoke.add(new Sphere(new Point(x,y,z),radius).setEmission(new Color(color,color,color).scale(0.2)).setMaterial(new Material().setKd(new Double3(0.2)).setKt(new Double3(0.6))));
		}
		return smoke;
	}
}
