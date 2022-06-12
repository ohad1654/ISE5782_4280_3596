package unittests.renderer;

import lighting.DirectionalLight;

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

	public Geometries baseBench(Point point) {
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



	public Geometries baseRectangle(Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7,Point p8, Color mainColor) {
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

	public Geometries baseTree(Point centerWood,double sizeWood,Color brick,Color leaf)	{

		Geometries geo = new Geometries();
		geo.add(
		new Polygon(
				centerWood,
				centerWood.add(new Vector(sizeWood,0,0)),
				centerWood.add(new Vector(sizeWood,0,3*sizeWood)),
				centerWood.add(new Vector(0,0,3*sizeWood))
		).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)))
			,new Polygon(centerWood,centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.25)).setKs(new Double3(0.25)))
			,new Polygon(centerWood.add(new Vector(0,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(0,-sizeWood,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)))
			,new Polygon(centerWood.add(new Vector(sizeWood,0,0)),centerWood.add(new Vector(sizeWood,-sizeWood,0)),centerWood.add(new Vector(sizeWood,-sizeWood,3*sizeWood)),centerWood.add(new Vector(sizeWood,0,3*sizeWood))).setEmission(brick).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)))

			,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood/2,4*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)))
			,new Sphere(centerWood.add(new Vector(sizeWood/2,-sizeWood,4*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)))
			,new Sphere(centerWood.add(new Vector(-sizeWood/3,-sizeWood/2,3.5*sizeWood)),3).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)))
			,new Sphere(centerWood.add(new Vector(-sizeWood/3,-1.5*sizeWood,3.5*sizeWood)),2.7).setEmission(leaf).setMaterial(new Material().setKd(new Double3(0.2)))
		);
		return  geo;
	}
	@Test
	public void HomeTest() {
		Scene scene = new Scene("Home").setBackground(new Color(153,204,255))
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

		Color brick = new Color(163,53,18);
		Point centerPool = new Point(-4,-5,0.001);
		Color colorPool = new Color(0,102,255);
		scene.geometries.add( //
				new Plane(new Point(0, 0, 0),new Vector(0, 0, 1)).setEmission(new Color(155,244,145).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				createHome(new Point(0,0,0)),
				//baseBench(new Point(-8,0,0)),
				baseTree(new Point(40,20,0),2,new Color(130,70,0).scale(0.5),new Color(110,213,66).scale(0.5))
				/*,new Circle((centerPool.add(new Vector(0,0,0.00001))),4.7,new Plane(new Point(0, 0, 0.000001),new Vector(0, 0, 1))).setEmission(new Color(9,34,6)).setMaterial(new Material().setKd(new Double3(0.2)).setShininess(100))
				,new Circle((centerPool.add(new Vector(0,0,0.0001))),4,new Plane(new Point(0, 0, 0.00001),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(15,15,0))).setMaterial(new Material().setKd(new Double3(0.07)).setKs(new Double3(0.8)).setShininess(100).setKr(new Double3(0.2)))
				,new Circle((centerPool.add(new Vector(0,0,0.0002))),3,new Plane(new Point(0, 0, 0.00002),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(10,10,0))).setMaterial(new Material().setKd(new Double3(0.04)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.25)))
				,new Circle((centerPool.add(new Vector(0,0,0.0003))),2,new Plane(new Point(0, 0, 0.00003),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(5,5,0))).setMaterial(new Material().setKd(new Double3(0.06)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))
				,new Circle((centerPool.add(new Vector(0,0,0.0004))),1,new Plane(new Point(0, 0, 0.00004),new Vector(0, 0, 1))).setEmission(colorPool.add(new Color(0,0,0))).setMaterial(new Material().setKd(new Double3(0.03)).setKs(new Double3(0.85)).setShininess(100).setKr(new Double3(0.3)))
*/
		);
		scene.lights.add(new DirectionalLight(new Color(255, 255, 128), new Vector(-10,20,-15)));
		Camera camera = new Camera(new Point(5,-5,20),new Point(5,5,7.5)
		)
				//.setGridSize(9)
				.setApt(1)
				.setFocalLength(15)
				.setVPDistance(5) //
				.setVPSize(20, 20) //
				.setImageWriter(new ImageWriter("Home picture", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.writeToImage();
	}

	@Test
	public void AntialiasingTest() {
		Scene s=new Scene("test").setGeometries(new Geometries(new Circle(new Point(10,5,5),1,new Plane(new Point(10,0,0),new Vector(-1,0,0))).setEmission(new Color(BLUE))));
		Camera camera=new Camera(Point.ZERO,new Point(1,0,0))
				.setVPSize(10,10).setVPDistance(10)
				.setImageWriter(new ImageWriter("Test",1,1))
				.setRayTracer(new RayTracerBasic(s));
		camera.constructRaysBeam(1,1,0,0);
	}

	private Geometries createHome(Point position) {
		final double roof_height=15;
		final double doorAngle=30;
		final double doorAngleRad=Math.toRadians(doorAngle+180);
		final  Vector doorRotateV=new Vector(Math.cos(doorAngleRad),Math.sin(doorAngleRad),0).scale(3);
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
				new Polygon(position.add(new Vector(6,0,6)),position.add(new Vector(6,0,8)),position.add(new Vector(8,0,8)),position.add(new Vector(8,0,6))).setMaterial(new Material().setKt(new Double3(1)).setKr(new Double3(0.2)).setShininess(1000)),

				createDoor(position.add(new Vector(6.5,0,0)),doorRotateV)

				//roof
				,new Triangle(position.add(new Vector(0,0,10)),position.add(new Vector(10,0,10)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Triangle(position.add(new Vector(0,10,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(0,0,10)),position.add(new Vector(0,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))
				,new Polygon(position.add(new Vector(10,0,10)),position.add(new Vector(10,10,10)),position.add(new Vector(5,10,roof_height)),position.add(new Vector(5,0,roof_height))).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2)))

				//entry road
				,new Polygon(position.add(new Vector(2,0,0.00001)),position.add(new Vector(8,0,0.00001)),position.add(new Vector(8,-20,0.00001)),position.add(new Vector(2,-20,0.00001))).setEmission(new Color(GRAY)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))

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

	private Geometries createDoor(Point position,Vector dir) {
		Vector widthVec=dir.crossProduct(new Vector(0,0,1)).normalize().scale(0.2);
		return new Geometries(
				//front side
				new Polygon(
						position,
						position.add(new Vector(0,0,5)),
						position.add(new Vector(0,0,5)).add(dir),
						position.add(dir)
				).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//back side
				new Polygon(
						position.add(widthVec),
						position.add(new Vector(0,0,5)).add(widthVec),
						position.add(new Vector(0,0,5)).add(dir).add(widthVec),
						position.add(dir).add(widthVec)
				).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//top side
				new Polygon(
						position.add(new Vector(0,0,5)),
						position.add(new Vector(0,0,5)).add(widthVec),
						position.add(new Vector(0,0,5)).add(widthVec).add(dir),
						position.add(new Vector(0,0,5)).add(dir)
				).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//face side
				new Polygon(
						position.add(new Vector(0,0,5)).add(dir),
						position.add(dir),
						position.add(dir).add(widthVec),
						position.add(new Vector(0,0,5)).add(dir).add(widthVec)
				).setEmission(new Color(RED).scale(0.5)).setMaterial(new Material().setKd(new Double3(0.2))),
				//handle
				new Sphere(position.add(new Vector(0,0,2.5)).add(dir.scale(0.9)).add(widthVec.scale(-1.1)),0.12).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setShininess(100))
		);
	}

	private Geometries createSmoke(Point start,Point end) {
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
