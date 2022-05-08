package primitives;

public class Material
{
    public  Double3 Kd =Double3.ZERO;
    public  Double3 Ks = Double3.ZERO;
    public Double3 Kt=Double3.ZERO;
    public Double3 Kr=Double3.ZERO;
    public int nShininess = 0;

    public Material setKt(Double3 kt) {
        Kt = kt;
        return this;
    }

    public Material setKr(Double3 kr) {
        Kr = kr;
        return this;
    }

    public Material setKd(Double3 kD) {
        this.Kd = kD;
        return this;
    }

    public Material setKs(Double3 kS) {
        this.Ks = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
