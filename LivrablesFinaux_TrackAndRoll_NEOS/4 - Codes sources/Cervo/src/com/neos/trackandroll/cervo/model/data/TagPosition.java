package com.neos.trackandroll.cervo.model.data;

import java.util.Date;

public class TagPosition {

    /**
     * The date of the tag position
     */
    private Date date;

    /**
     * The tag x coordinate
     */
    private float x;

    /**
     * The tag y coordinate
     */
    private float y;

    /**
     * Main constructor of the tag position
     * @param date : the date of the tag position
     * @param x : the x coordinate of the tag position
     * @param y : the y coordinate of the tag position
     */
    public TagPosition(Date date, float x, float y) {
        this.date = date;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of the date
     * @return : the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter of the date
     * @param date : the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter of the x coordinate
     * @return : the x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Setter of the x coordinate
     * @param x : the x coordinate
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Getter of the y coordinate
     * @return : the y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Setter of the y coordinate
     * @param y : the y coordinate
     */
    public void setY(float y) {
        this.y = y;
    }
}
