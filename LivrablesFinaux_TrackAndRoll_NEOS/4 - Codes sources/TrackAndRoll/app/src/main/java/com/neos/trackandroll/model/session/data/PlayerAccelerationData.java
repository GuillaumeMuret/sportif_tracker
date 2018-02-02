package com.neos.trackandroll.model.session.data;

import com.google.gson.annotations.SerializedName;

public class PlayerAccelerationData {

    @SerializedName("date")
    private String date;

    @SerializedName("acceleration")
    private float acceleration;

    /**
     * Main constructor of the PlayerAccelerationData
     * @param date
     * @param acceleration
     */
    public PlayerAccelerationData(String date, float acceleration) {
        this.date = date;
        this.acceleration = acceleration;
    }

    /**
     * Method call to get the date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Method call the set the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method call to get the acceleration
     * @return
     */
    public float getAcceleration() {
        return acceleration;
    }

    /**
     * Method call to set the acceleration
     * @param acceleration
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }
}