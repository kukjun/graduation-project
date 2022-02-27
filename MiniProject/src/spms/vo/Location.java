package spms.vo;

public class Location {
    private int locationCode;
    private int x;
    private int y;
    private double PTY;
    private double REH;
    private double RN1;
    private double T1H;
    private double UUU;
    private double VVV;
    private double VEC;
    private double WSD;

    public int getLocationCode() {
        return locationCode;
    }

    public Location setLocationCode(int locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    public int getX() {
        return x;
    }

    public Location setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Location setY(int y) {
        this.y = y;
        return this;
    }

    public double getPTY() {
        return PTY;
    }

    public Location setPTY(double PTY) {
        this.PTY = PTY;
        return this;
    }

    public double getREH() {
        return REH;
    }

    public Location setREH(double REH) {
        this.REH = REH;
        return this;
    }

    public double getRN1() {
        return RN1;
    }

    public Location setRN1(double RN1) {
        this.RN1 = RN1;
        return this;
    }

    // T1H 를 인식하지 못함.
    public double getT1H() {
        return T1H;
    }

    public Location setT1H(double T1H) {
        this.T1H = T1H;
        return this;
    }

    public double getUUU() {
        return UUU;
    }

    public Location setUUU(double UUU) {
        this.UUU = UUU;
        return this;
    }

    public double getVVV() {
        return VVV;
    }

    public Location setVVV(double VVV) {
        this.VVV = VVV;
        return this;
    }

    public double getVEC() {
        return VEC;
    }

    public Location setVEC(double VEC) {
        this.VEC = VEC;
        return this;
    }

    public double getWSD() {
        return WSD;
    }

    public Location setWSD(double WSD) {
        this.WSD = WSD;
        return this;
    }

}
