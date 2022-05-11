package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 *
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (int i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
	}

	/**
	 * calculates normal vector to a point on the Vector
	 * @param point is the point to find normal vector to
	 * @return Vector is normal vector to Point on the Vector
	 */
	@Override  public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	/**
	 * finds intersections between the ray and the Polygon
	 * @param ray is ray to intersect with the Polygon
	 * @return list of intersections
	 */
	@Override protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<Point> planeInters = plane.findIntersections(ray);
		List<GeoPoint> res = new ArrayList<GeoPoint>();
		if(planeInters == null){
			return null;
		}

		List<Vector> vectors = new ArrayList<Vector>();
		Vector vector;
		for(int i=0; i<vertices.size(); i++) {
			vector = vertices.get(i).subtract(ray.getQ0());
			vectors.add(vector);
		}

		List<Vector> normals = new ArrayList<Vector>();
		for(int j=0; j< vectors.size()-1; j++){
			vector = vectors.get(j).crossProduct(vectors.get(j + 1)).normalize();
			normals.add(vector);
		}
		normals.add(vectors.get(vectors.size()-1).crossProduct(vectors.get(0)).normalize());

		boolean temp;
		for (int index=0; index<planeInters.size(); ++index){
			temp = true;
			Vector v = ray.getDir();
			boolean sign = normals.get(0).dotProduct(v) > 0; //+ is true, - is false

			for(int k=0; temp && k<normals.size(); k++) {
				if(isZero(v.dotProduct(normals.get(k)))){
					temp = false;
				}

				else if((v.dotProduct(normals.get(k)) > 0) != sign){
					temp = false;
				}
			}
			if(temp){
				res.add(new GeoPoint(this,planeInters.get(index)));
			}
		}
		if(res.size() == 0){
			return null;
		}
		return res;
	}
}
