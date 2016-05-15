package com.example.sebastian.bicycle_calculator;

/**
 * Created by Sebastian on 2016-05-11.
 */public class Cadence {
    private double chainRing;


    private double skidPatch;
    private double cog;
    private double ratio;
    private double ambidextrous;

    public double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public double getChainRing() {
        return chainRing;
    }

    public void setChainRing(double chainRing) {
        this.chainRing = chainRing;
    }

    public double getSkidPatch() {
        return skidPatch;
    }

    public void setSkidPatch(double skidPatch) {
        this.skidPatch = skidPatch;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getAmbidextrous() {
        return ambidextrous;
    }

    public void setAmbidextrous(double ambidextrous) {
        this.ambidextrous = ambidextrous;
    }

    public Cadence(Double chainRing, Double cog) {
        this.chainRing = chainRing;
        this.cog = cog;
        skidPatches(chainRing, cog);
        calculateRatio(chainRing, cog);
    }

    public void calculateRatio(double chainRing, double cog) {
        ratio = chainRing / cog;
        setRatio(ratio);
    }

    public void skidPatches(double chainRing, double cog) {
        double nwd = nwd(chainRing, cog);

        if ((chainRing / nwd) % 2 == 0) {
            setSkidPatch(cog / nwd);
            setAmbidextrous(cog / nwd);
        }
        if (chainRing / nwd % 2 != 0) {
            setSkidPatch(cog / nwd);
            setAmbidextrous(cog / nwd * 2);
        }
    }

    //function to find the greatest common divisor
    public double nwd(double a, double b) {

        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }
}
