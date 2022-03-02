package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("You cant create vector 0");
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("You cant create vector 0");
    }

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double dotProduct(Vector vector) {
        Double3 p = xyz.product(vector.xyz);
        return p.d1 + p.d2 + p.d3;
    }

    public Vector crossProduct(Vector v2) {
        return new Vector(xyz);
    }

    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o);
    }
}
