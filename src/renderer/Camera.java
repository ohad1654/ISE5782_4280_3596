package renderer;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

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
    private double focalLength;


    public Camera setFocalLength(double focalLength) {
        this.focalLength = focalLength;
        return this;
    }

    public Camera setApt(double apt) {
        this.apt = apt;
        return this;
    }

    private double apt;


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

    public Color constructRaysBeam(int nX, int nY, double j, double i, int level) {
        if (level <= 1)
            return rayTracer.traceRay(Depth( constructRay(nX, nY, j, i)));

        Color avgColor = Color.BLACK;
        Color preColor = null;
        Color color;
        for (double beamI = -0.5; beamI < 2; beamI += 2) {
            for (double beamJ = -0.5; beamJ < 2; beamJ += 2) {
                color = rayTracer.traceRay(Depth(constructRay(nX * 2, nY * 2, j * 2 + beamI, i * 2 + beamJ)));
                if (preColor == null)
                    preColor = color;
                if (!color.equals(preColor)) {
                    avgColor = avgColor.add(constructRaysBeam(nX * 2, nY * 2, j * 2 + (int) beamI, i * 2 + (int) beamJ, level - 1));
                    preColor = color;
                } else
                    avgColor = avgColor.add(color);
            }
        }
        return avgColor.reduce(4);
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
            for (int i = 0; i < nx; i++) {
                Color color = constructRaysBeam(nx, ny, j, i, 3);
                imageWriter.writePixel(j, i, color);
            }
        });
        return this;
    }


    public Camera renderImageDepth() {
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

        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
                imageWriter.writePixel(j, i, rayTracer.traceRay(Depth(ray)));
            }
        }
        return this;
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


    public Ray Depth(Ray ray) {
        Point c = ray.getQ0().add(ray.getDir().scale(focalLength));
        Vector randomVector = new Vector(random(-0.5, 0.5), random(-0.5, 0.5), random(-0.5, 0.5)).scale(focalLength);
        Point a0 = ray.getQ0().add(randomVector);
        return new Ray(a0, c.subtract(a0));

    }
}
