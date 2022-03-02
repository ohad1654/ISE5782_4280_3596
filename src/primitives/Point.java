package primitives;

import java.util.Objects;

public class Point {
    protected final Double3 xyz;
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz) {
        this.xyz=xyz;
    }

    public Double3 getXyz() {
        return xyz;
    }

    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
}
