package com.neos.trackandroll.cervo.model.localisateur;

public class Circle {

    /**
     * The center of the circle data
     */
    public Point centre;

    /**
     * The rayon of the circle
     */
    public float rayon;

    /**
     * Main constructor of the circle data
     *
     * @param centre : the center
     * @param rayon  : the rayon
     */
    public Circle(Point centre, float rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    /**
     * Method to transform the data to a string
     *
     * @return : the string of the data
     */
    @Override
    public String toString() {
        return "centre = " + centre.toString() + " rayon = " + String.valueOf(rayon);
    }
}
