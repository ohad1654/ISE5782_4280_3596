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

    public Camera(Point position, Vector vUp, Vector vTo) {
        if(!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("Vup and Vto must by orthogonal");
        this.position = position;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight=vUp.crossProduct(vTo).normalize();
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
        return null;
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
}
