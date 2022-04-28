package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight
{
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

   @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p);
        double dirl = direction.dotProduct(l);
        if(dirl >0)
        {
            return getIntensity().scale(dirl).reduce(distance(p));
        }
        return getIntensity().scale(0);
    }

    public  Vector getL(Point p)
    {
       return super.getL(p);
    }
}
