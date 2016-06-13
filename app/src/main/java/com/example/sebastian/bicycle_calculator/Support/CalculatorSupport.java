package com.example.sebastian.bicycle_calculator.Support;

/**
 * Created by Sebastian on 2016-06-12.
 */
public class CalculatorSupport {
    private double cadence = 0;
    private double chainRing;
    private double skidPatch;
    private double cog;
    private double ratio;
    private double ambidextrous;
    private double speed;
    private double speed50;
    private double speed60;
    private double speed70;
    private double speed80;
    private double speed90;
    private double speed100;
    private double speed110;
    private double speed120;
    private double speed130;
    private double speed140;
    private double speed150;
    private double speed160;
    private double speed170;


    public CalculatorSupport(Double chainRing, Double cog) {
        this.chainRing = chainRing;
        this.cog = cog;
        skidPatches(chainRing, cog);
        calculateRatio(chainRing, cog);
        calculateSpeedTable();
    }

    public CalculatorSupport(Double chainRing, Double cog, Double cadence) {
        this.chainRing = chainRing;
        this.cog = cog;
        this.cadence = cadence;
        skidPatches(chainRing, cog);
        calculateRatio(chainRing, cog);
        calculateSpeed();
        calculateSpeedTable();
    }

    public CalculatorSupport(){
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

    public void calculateSpeed() {
        speed = (cadence * ratio * 2.13 * 60) / 1000;

    }

    public void calculateSpeedTable(){
        double speed50 = (50*ratio* 2.13* 60) / 1000;
        double speed60 = (60*ratio* 2.13* 60) / 1000;
        double speed70 = (70*ratio* 2.13* 60) / 1000;
        double speed80 = (80*ratio* 2.13* 60) / 1000;
        double speed90 = (90*ratio* 2.13* 60) / 1000;
        double speed100 = (100*ratio* 2.13* 60) / 1000;
        double speed110 = (110*ratio* 2.13* 60) / 1000;
        double speed120 = (120*ratio* 2.13* 60) / 1000;
        double speed130 = (130*ratio* 2.13* 60) / 1000;
        double speed140 = (140*ratio* 2.13* 60) / 1000;
        double speed150 = (150*ratio* 2.13* 60) / 1000;
        double speed160 = (160*ratio* 2.13* 60) / 1000;
        double speed170 = (170*ratio* 2.13* 60) / 1000;
        setSpeed50(speed50);
        setSpeed60(speed60);
        setSpeed70(speed70);
        setSpeed80(speed80);
        setSpeed90(speed90);
        setSpeed100(speed100);
        setSpeed110(speed110);
        setSpeed120(speed120);
        setSpeed130(speed130);
        setSpeed140(speed140);
        setSpeed150(speed150);
        setSpeed160(speed160);
        setSpeed170(speed170);

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

    public double getSpeed50() {
        return speed50;
    }

    public void setSpeed50(double speed50) {
        this.speed50 = speed50;
    }

    public double getSpeed60() {
        return speed60;
    }

    public void setSpeed60(double speed60) {
        this.speed60 = speed60;
    }

    public double getSpeed70() {
        return speed70;
    }

    public void setSpeed70(double speed70) {
        this.speed70 = speed70;
    }

    public double getSpeed80() {
        return speed80;
    }

    public void setSpeed80(double speed80) {
        this.speed80 = speed80;
    }

    public double getSpeed90() {
        return speed90;
    }

    public void setSpeed90(double speed90) {
        this.speed90 = speed90;
    }

    public double getSpeed100() {
        return speed100;
    }

    public void setSpeed100(double speed100) {
        this.speed100 = speed100;
    }

    public double getSpeed110() {
        return speed110;
    }

    public void setSpeed110(double speed110) {
        this.speed110 = speed110;
    }

    public double getSpeed120() {
        return speed120;
    }

    public void setSpeed120(double speed120) {
        this.speed120 = speed120;
    }

    public double getSpeed130() {
        return speed130;
    }

    public void setSpeed130(double speed130) {
        this.speed130 = speed130;
    }
    public double getSpeed140() {
        return speed140;
    }

    public void setSpeed140(double speed140) {
        this.speed140 = speed140;
    }

    public double getSpeed150() {
        return speed150;
    }

    public void setSpeed150(double speed150) {
        this.speed150 = speed150;
    }

    public double getSpeed160() {
        return speed160;
    }

    public void setSpeed160(double speed160) {
        this.speed160 = speed160;
    }

    public double getSpeed170() {
        return speed170;
    }

    public void setSpeed170(double speed170) {
        this.speed170 = speed170;
    }


    public double getCadence() {
        return cadence;
    }

    public void setCadence(double cadence) {
        this.cadence = cadence;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

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
}