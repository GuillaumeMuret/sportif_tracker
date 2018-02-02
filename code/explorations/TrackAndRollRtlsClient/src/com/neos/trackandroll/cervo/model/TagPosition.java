package com.neos.trackandroll.cervo.model;

import java.util.Date;

public class TagPosition {

    private Date date;
    private float x;
    private float y;

    public TagPosition(Date date, float x, float y) {
        this.date = date;
        this.x = x;
        this.y = y;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
