package renderer;

import primitives.*;

import static primitives.Util.*;


public class Camera {
    private Point position;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    public Camera(Point position, Vector vTo, Vector vUp) {
        if(!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("Vup and Vto must by orthogonal");
        this.position = position;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight=vTo.crossProduct(vUp);
    }

    public Camera setVPSize(double width, double height){
        this.width=width;
        this.height=height;
        return this;
    }
    public Camera setVPDistance(double distance){
        this.distance=distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
        Point VPCenter=position.add(vTo.scale(distance));
        double ratioY=height/nY;
        double ratioX=width/nX;
        double yI = alignZero(-(i-(double)(nY-1)/2))*ratioY;
        double xJ = alignZero((j-(double)(nX-1)/2))*ratioX;
        Point pixelIJ=VPCenter;
        if (xJ!=0)
            pixelIJ=pixelIJ.add(vRight.scale(xJ));
        if (yI!=0)
            pixelIJ=pixelIJ.add(vUp.scale(yI));
        return new Ray(position,pixelIJ.subtract(position));
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

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public void renderImage() {
    }

    public void printGrid(int i, Color color) {
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }
}
