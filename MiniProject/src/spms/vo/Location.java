package spms.vo;

public class Location {
    private int locationCode;
    private String localLevel1;
    private String localLevel2;
    private String localLevel3;
    private String x;
    private String y;

    public int getLocationCode() {
        return locationCode;
    }

    public Location setLocationCode(int locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    public String getX() {
        return x;
    }

    public Location setX(String x) {
        this.x = x;
        return this;
    }

    public String getY() {
        return y;
    }

    public Location setY(String y) {
        this.y = y;
        return this;
    }

    public String getLocalLevel1() {
        return localLevel1;
    }

    public Location setLocalLevel1(String localLevel1) {
        this.localLevel1 = localLevel1;
        return this;
    }

    public String getLocalLevel2() {
        return localLevel2;
    }

    public Location setLocalLevel2(String localLevel2) {
        this.localLevel2 = localLevel2;
        return this;
    }

    public String getLocalLevel3() {
        return localLevel3;
    }

    public Location setLocalLevel3(String localLevel3) {
        this.localLevel3 = localLevel3;
        return this;
    }
}