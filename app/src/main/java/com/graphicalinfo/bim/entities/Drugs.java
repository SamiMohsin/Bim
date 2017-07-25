package com.graphicalinfo.bim.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by ayoob on 25/07/17.
 */

public class Drugs {
    private Drawable image;
    private String name;
    private String trade;
    private double mg;
    private double ml;

    public Drugs() {}


    public Drugs(Drawable image, String name, String trade, double mg, double ml) {
        this.image = image;
        this.name = name;
        this.trade = trade;
        this.mg = mg;
        this.ml = ml;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public double getMg() {
        return mg;
    }

    public void setMg(double mg) {
        this.mg = mg;
    }

    public double getMl() {
        return ml;
    }

    public void setMl(double ml) {
        this.ml = ml;
    }
}
