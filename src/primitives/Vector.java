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

    /**
     * add vector to this vector
     * @param v2    the vector
     * @return      a new vector
     */
    public Vector add(Vector v2)
    {
        return new Vector(this.xyz.add(v2.xyz));
    }

    /**
     * the length Squared
     * @return  the length Squared
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }
    /**
     * the length
     * @return  the length
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * vector double calculation
     * @param vector a vector
     * @return vector double calculation
     */
    public double dotProduct(Vector vector) {
        Double3 p = xyz.product(vector.xyz);
        return p.d1 + p.d2 + p.d3;
    }
    /**
     * vector  double calculation
     * @param v2 a vector
     * @return vector double calculation
     */
    public Vector crossProduct(Vector v2) {
        return new Vector(this.xyz.d2*v2.xyz.d3-this.xyz.d3*v2.xyz.d2,this.xyz.d3*v2.xyz.d1-this.xyz.d1*v2.xyz.d3,this.xyz.d1*v2.xyz.d2-this.xyz.d2*v2.xyz.d1);
    }

    /**
     * normalize the vector
     * @return the vector normalzied
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    /**
     * multiply a scalar on vector
     * @param sclr a scalar
     * @return the new vector
     */
    public Vector scale(double sclr){ return new Vector(xyz.scale(sclr)); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o);
    }
}
