package primitives;

import java.util.Objects;

public class Ray {
    private final Point q0;
    private final Vector dir;

    public Ray(Point q0, Vector dir) {
        this.q0 = q0;
        this.dir = dir.normalize();
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
