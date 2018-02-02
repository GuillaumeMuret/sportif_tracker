package com.neos.trackandroll.model.session.data;

import com.google.gson.annotations.SerializedName;

public class PlayerSpeedData {

    @SerializedName("date")
    private String date;

    @SerializedName("speed")
    private float speed;

    /**
     * Main constructor of PlayerSpeedData
     * @param date
     * @param speed
     */
    public PlayerSpeedData(String date, float speed) {
        this.date = date;
        this.speed = speed;
    }

    /**
     * Method call to get the date
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Method call to set the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method call to get the player speed
     * @return
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Method to set the player speed
     * @param speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}