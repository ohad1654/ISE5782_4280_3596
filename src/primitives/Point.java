package primitives;


public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    protected final Double3 xyz;
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz) {
        this.xyz=xyz;
    }

    public double getX()
    {
        return xyz.d1;
    }

    public double getY()
    {
        return xyz.d2;
    }

    public double getZ()
    {
        return xyz.d3;
    }

    /**
     * add vector to point
     * @param vector  vector
     * @return  the new point
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }
    /**
     * subtract point from point
     * @param point  the point
     * @return  the new Vector
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * the distance Squared
     * @param point points
     * @return  the distance
     */
    public double distanceSquared(Point point)
    {
        return (point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1) + (point.xyz.d2-this.xyz.d2)*(point.xyz.d2-this.xyz.d2) + (point.xyz.d3-this.xyz.d3)*(point.xyz.d3-this.xyz.d3);
    }

    /**
     * distance between points
     * @param point the srcond ponts
     * @return a double distance
     */
    public double distance(Point point)
    {
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(!(o instanceof Point)) return false;

        Point point =(Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
}
