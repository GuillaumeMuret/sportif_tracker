package com.neos.trackandroll.cervo.utils.file.position;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class FileTagPositionData {

    /**
     * The date of the result
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_DATE)
    private String date;

    /**
     * The x coordinate
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_X)
    private float x;

    /**
     * The y coordinate
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_Y)
    private float y;

    /**
     * Main constructor of the file tag position data
     * @param date : the date of the position
     * @param x : the x coordinate
     * @param y : the y coordinate
     */
    public FileTagPositionData(String date, float x, float y) {
        this.date = date;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of the date
     * @return : the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter of the date
     * @param date : the date
     */
    public void setDate(String date) {
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
     * Getter of the Y coordinate
     * @return : the Y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Setter of the Y coordinate
     * @param y : the y coordinate
     */
    public void setY(float y) {
        this.y = y;
    }
}
