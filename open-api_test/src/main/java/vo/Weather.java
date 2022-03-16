package main.java.vo;

import java.sql.Date;
import java.sql.Time;

public class Weather {
    private int locationCode;
    private String date;
    private String time;
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

    public Weather setLocationCode(int locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Weather setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Weather setTime(String time) {
        this.time = time;
        return this;
    }

    public double getPTY() {
        return PTY;
    }

    public Weather setPTY(double PTY) {
        this.PTY = PTY;
        return this;
    }

    public double getREH() {
        return REH;
    }

    public Weather setREH(double REH) {
        this.REH = REH;
        return this;
    }

    public double getRN1() {
        return RN1;
    }

    public Weather setRN1(double RN1) {
        this.RN1 = RN1;
        return this;
    }

    public double getT1H() {
        return T1H;
    }

    public Weather setT1H(double t1H) {
        T1H = t1H;
        return this;
    }

    public double getUUU() {
        return UUU;
    }

    public Weather setUUU(double UUU) {
        this.UUU = UUU;
        return this;
    }

    public double getVVV() {
        return VVV;
    }

    public Weather setVVV(double VVV) {
        this.VVV = VVV;
        return this;
    }

    public double getVEC() {
        return VEC;
    }

    public Weather setVEC(double VEC) {
        this.VEC = VEC;
        return this;
    }

    public double getWSD() {
        return WSD;
    }

    public Weather setWSD(double WSD) {
        this.WSD = WSD;
        return this;
    }

    @Override
    public String toString() {
        return "locationCode = " + locationCode +
                "\ndate = " + date +
                "\ntime = " + time +
                "\nPTY = " + PTY +
                "\nREH = " + REH +
                "\nRN1 = " + RN1 +
                "\nT1H = " + T1H +
                "\nUUU = " + UUU +
                "\nVEC = " + VEC +
                "\nVVV = " + VVV +
                "\nWSD = " + WSD;
    }
}


