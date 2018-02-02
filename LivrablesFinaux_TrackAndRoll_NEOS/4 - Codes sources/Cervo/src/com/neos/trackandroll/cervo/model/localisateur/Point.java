package com.neos.trackandroll.cervo.model.localisateur;

public class Point {

    /**
     * The x coordinate of the point
     */
    public float x;

    /**
     * The y coordinate of the point
     */
    public float y;

    /**
     * The family number the point is part of
     */
    public int family;

    /**
     * Main constructor of the point
     *
     * @param x : the x coordinate of the point
     * @param y : the y coordinate of the point
     */
    public Point(float x, float y) {
        this(x, y, -1);
    }

    /**
     * Main constructor of the point
     *
     * @param x : the x coordinate of the point
     * @param y : the y coordinate of the point
     * @param family : the family number the point is part of
     */
    public Point(float x, float y, int family) {
        this.x = x;
        this.y = y;
        this.family = family;
    }

    /**
     * Process called to convert the model to string
     *
     * @return : the string of the data
     */
    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")[F" + this.family + "]";
    }
}
