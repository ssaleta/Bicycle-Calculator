package com.example.sebastian.bicycle_calculator.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sebastian on 2016-05-21.
 */
public class Bicycle implements Serializable {

    private int itemId ;
    private String name;
    private double chainring;
    private double cog;
    private double skidPatch;
    private double ratio;
    private static int counter = 0;





    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChainring() {
        return chainring;
    }

    public void setChainring(double chainring) {
        this.chainring = chainring;
    }
    public Bicycle(String name, Double chainring, double cog, double ratio, double skidPatch){
        this.name = name;
        this.chainring = chainring;
        this.cog = cog;
        this.ratio = ratio;
        this.skidPatch = skidPatch;

    }
    public Bicycle (){}
    public Bicycle(int itemId, String name, Double chainring, double cog, double skidPatch, double ratio){
        this.itemId = itemId;
        this.name = name;
        this.chainring = chainring;
        this.cog = cog;
        this.skidPatch = skidPatch;
        this.ratio = ratio;
    }




}
