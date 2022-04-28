package primitives;

public class Material
{
    public  double Kd =0;
    public  double Ks = 0;
    public int nShininess = 0;

    public Material setKd(double kD) {
        this.Kd = kD;
        return this;
    }

    public Material setKs(double kS) {
        this.Ks = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
