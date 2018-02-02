package com.neos.trackandroll.cervo.model.localisateur;

public class Point {

    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+this.x+";"+this.y+")";
    }
}
