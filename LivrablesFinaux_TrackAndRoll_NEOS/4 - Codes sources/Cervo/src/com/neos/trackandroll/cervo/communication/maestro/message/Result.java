package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class Result {

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
     * The speed
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_V)
    private float v;

    /**
     * The value of the result
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_VALEUR)
    private String valeur;

    /**
     * Getter of the date of the result
     * @return : the result date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter of the result date
     * @param date : the new result date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter of the x value
     * @return : the x value
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
     * @param y : the new y coordinate
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Getter of the speed v
     * @return : the speed v
     */
    public float getV() {
        return v;
    }

    /**
     * Setter of the speed v
     * @param v : the speed v
     */
    public void setV(float v) {
        this.v = v;
    }

    /**
     * Getter of the value
     * @return : the value
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Setter of the value
     * @param valeur : the value
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
