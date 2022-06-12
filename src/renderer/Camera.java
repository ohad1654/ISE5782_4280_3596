package renderer;

import primitives.*;

import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.*;


public class Camera {

    final int UP_LEFT=0,UP_RIGHT=1, DOWN_LEFT=2, DOWN_RIGHT=3;
    private Point position;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    private boolean depth=false;
    private double focalLength=0;
    private double apertureSize=0;

    private boolean adaptiveSampling=false;
    private int adaptiveBeamDepth =2;




    /**
     * Create camera at the position, facing to VTo
     *
     * @param position the position of the camera
     * @param vTo      the direction the camera facing at
     * @param vUp      the direction of the up
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("Vup and Vto must by orthogonal");
        this.position = position;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    public Camera(Point position, Point target) {
        if (position.equals(target))
            throw new IllegalArgumentException("position must by different from target");

        this.position = position;
        vTo = target.subtract(position).normalize();
        vUp = new Vector(0, 0, 1);
        if (vTo.getZ() != 0) {
            double vUpZ = Math.abs((vTo.getX() * vTo.getX() + vTo.getY() * vTo.getY()) / vTo.getZ());
            vUp = new Vector(vTo.getX(), vTo.getY(), vUpZ).normalize();
        }
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * set the view plane size
     *
     * @param width  the width of the VP
     * @param height thr height of the VP
     * @return the updated camera with the VP (for builder)
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set the view plain distance from the camera
     *
     * @param distance the distance from the camera
     * @return the updated camera with the VP (for builder)
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public Camera setGridSize(int gridSize) {
        this.adaptiveBeamDepth = (int)(Math.log(gridSize-1)/Math.log(2)+1);//Depth=log2(gridSize-1)+1
        this.adaptiveSampling=true;
        return this;
    }

    public Camera setFocalLength(double focalLength) {
        this.focalLength = focalLength;
        if (apertureSize!=0)
            depth=true;
        return this;
    }

    public Camera setApt(double apt) {
        this.apertureSize = apt;
        if (focalLength!=0)
            depth=true;
        return this;
    }


    /**
     * create ray from the camera to the specific pixel at the view plane
     *
     * @param nX the number of pixels on X-axis
     * @param nY the number of pixels on Y-axis
     * @param j  the pixel row number
     * @param i  the pixel column number
     * @return the ray created
     */
    public Ray constructRay(int nX, int nY, double j, double i) {
        Point VPCenter = position.add(vTo.scale(distance));
        double ratioY = height / nY;
        double ratioX = width / nX;
        double yI = alignZero(-(i - (double) (nY - 1) / 2)) * ratioY;
        double xJ = alignZero((j - (double) (nX - 1) / 2)) * ratioX;
        Point pixelIJ = VPCenter;
        if (xJ != 0)
            pixelIJ = pixelIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pixelIJ = pixelIJ.add(vUp.scale(yI));
        return new Ray(position, pixelIJ.subtract(position));
    }

    public Color constructRaysBeam(int nX, int nY, double j, double i) {
        Point pixelIJ = null;
        Point VPCenter = position.add(vTo.scale(distance));
        double ratioY = height / nY;
        double ratioX = width / nX;
        double yI = alignZero(-(i - (double) (nY - 1) / 2)) * ratioY;
        double xJ = alignZero((j - (double) (nX - 1) / 2)) * ratioX;
        pixelIJ = VPCenter;
        if (xJ != 0)
            pixelIJ = pixelIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pixelIJ = pixelIJ.add(vUp.scale(yI));

        if (adaptiveSampling) {
            List<Point> corners=createSquare(pixelIJ,ratioX,ratioY);
            Color ruColor = rayTracer.traceRay(new Ray(position, corners.get(UP_RIGHT).subtract(position)));
            Color luColor = rayTracer.traceRay(new Ray(position, corners.get(UP_LEFT).subtract(position)));
            Color rdColor = rayTracer.traceRay(new Ray(position, corners.get(DOWN_RIGHT).subtract(position)));
            Color ldColor = rayTracer.traceRay(new Ray(position, corners.get(DOWN_LEFT).subtract(position)));

            return adaptiveSampling(pixelIJ, ratioX, ratioY, luColor, ruColor, ldColor, rdColor, adaptiveBeamDepth -1);
        } else
            return Depth(new Ray(position,pixelIJ.subtract(position)),pixelIJ);

    }


    /***
     * calculate the points of sqred
     * @param center the center of the square
     * @param width the width of the square
     * @param height the height of the square
     * @return list of [upLeft,upRight, downLeft, downRight]
     */
    public List<Point> createSquare(Point center,double width,double height){
        return List.of(
                center.subtract(vRight.scale(width / 2)).add(vUp.scale(height / 2)),
                center.add(vRight.scale(width / 2)).add(vUp.scale(height / 2)),
                center.subtract(vRight.scale(width / 2)).subtract(vUp.scale(height / 2)),
                center.add(vRight.scale(width / 2)).subtract(vUp.scale(height / 2)));
    }
    public Point getPosition() {
        return position;
    }

    public Vector getVup() {
        return vUp;
    }

    public Vector getVto() {
        return vTo;
    }

    public Vector getVright() {
        return vRight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }


    public Camera renderImage() {
        if (position == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "position");
        if (vUp == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "vUp");
        if (vTo == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "vTo");
        if (height == 0)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "height");
        if (width == 0)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "width");
        if (distance == 0)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "distance");
        if (imageWriter == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "imageWriter");
        if (rayTracer == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "rayTracer");

        int ny = imageWriter.getNy(), nx = imageWriter.getNx();

        IntStream.range(0, ny).parallel().forEach(j -> {
            IntStream.range(0, nx).parallel().forEach(i->{
                Color color=constructRaysBeam(nx,ny,j,i);
                imageWriter.writePixel(j, i,color);
            });
        });

        return this;
    }

    private Color adaptiveSampling(Point center, double rX, double rY,
                                   Color leftUpColor, Color rightUpColor,
                                   Color leftDownColor, Color rightDownColor,
                                   int depth) {
        if (depth==0)
            return leftUpColor.add(rightUpColor,leftDownColor,rightDownColor).reduce(4);//average

        if(leftUpColor.equals(rightUpColor)&&rightUpColor.equals(leftDownColor)&&leftDownColor.equals(rightDownColor)){
            return leftUpColor;
        }else {
            Point right=center.add(vRight.scale(rX/2));
            Color rightColor=rayTracer.traceRay(new Ray(position,right.subtract(position)));

            Point left=center.subtract(vRight.scale(rX/2));
            Color leftColor=rayTracer.traceRay(new Ray(position,left.subtract(position)));

            Point up=center.add(vUp.scale(rY/2));
            Color upColor=rayTracer.traceRay(new Ray(position,up.subtract(position)));

            Point down=center.subtract(vUp.scale(rY/2));
            Color downColor=rayTracer.traceRay(new Ray(position,down.subtract(position)));

            List<Point> squaresCenters=createSquare(center,rX/2,rY/2);

            Color centerColor=rayTracer.traceRay(new Ray(position,center.subtract(position)));
            Color lu=adaptiveSampling(squaresCenters.get(UP_LEFT),rX,rY,leftUpColor,upColor,leftColor,centerColor,depth-1);
            Color ru=adaptiveSampling(squaresCenters.get(UP_RIGHT),rX,rY,upColor,rightUpColor,centerColor,rightColor,depth-1);
            Color ld=adaptiveSampling(squaresCenters.get(DOWN_LEFT),rX,rY,leftColor,centerColor,leftDownColor,downColor,depth-1);
            Color rd=adaptiveSampling(squaresCenters.get(DOWN_RIGHT),rX,rY,centerColor,rightColor,downColor,rightDownColor,depth-1);

            return lu.add(ru,ld,rd).reduce(4);
        }



    }


    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "imageWriter");
        for (int x = 0; x < imageWriter.getNx(); x++) {
            for (int y = 0; y < imageWriter.getNy(); y++) {
                if (x % interval == 0 || y % interval == 0)
                    imageWriter.writePixel(x, y, color);
            }
        }
        imageWriter.writeToImage();
    }

    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Error missing resource in camera", "Camera", "imageWriter");
        imageWriter.writeToImage();
    }


    public Color Depth(Ray ray, Point VPPoint) {
        if (depth){
            List<Point> corners=createSquare(VPPoint,apertureSize,apertureSize);
            Point focalPoint=ray.getPoint(focalLength);
            Color color=Color.BLACK;
            for (Point point :
                    corners) {
                color=color.add(rayTracer.traceRay(new Ray(point,focalPoint.subtract(point))));
            }
            return color.reduce(4);
        }
        else
            return rayTracer.traceRay(ray);
    }
}
