package primitives;

import java.util.List;
import java.util.Objects;

public class Ray {
    private final Point q0;
    private final Vector dir;

    public Ray(Point q0, Vector dir) {
        this.q0 = q0;
        this.dir = dir.normalize();
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t)
    {
        return q0.add(dir.scale(t));
    }

    public Point findClosestPoint(List<Point> points){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return q0.equals(ray.q0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return  "q0=" + q0 +
                ", dir=" + dir;
    }
}
