package com.neos.trackandroll.model.session.data;

import com.google.gson.annotations.SerializedName;

public class PlayerPositionData {

    @SerializedName("date")
    private String date;

    @SerializedName("pos_x")
    private float posX;

    @SerializedName("pos_y")
    private float posY;

    /**
     * Main constructor of PlayerPositionData
     * @param date
     * @param posX
     * @param posY
     */
    public PlayerPositionData(String date, float posX, float posY) {
        this.date = date;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Method call to get the date
     * @return
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
     * Method to get the position x
     * @return the position x
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Method call to set the position x
     * @param posX
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Method call to get the position y
     * @return the position y
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Method call to set the position y
     * @param posY
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }
}