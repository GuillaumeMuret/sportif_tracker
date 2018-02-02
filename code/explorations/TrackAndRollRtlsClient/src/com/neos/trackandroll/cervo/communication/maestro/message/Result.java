package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.Date;

public class Result {

    @SerializedName(MaestroProtocolVocabulary.JSON_DATE)
    private String date;

    @SerializedName(MaestroProtocolVocabulary.JSON_X)
    private float x;

    @SerializedName(MaestroProtocolVocabulary.JSON_Y)
    private float y;

    @SerializedName(MaestroProtocolVocabulary.JSON_V)
    private float v;

    @SerializedName(MaestroProtocolVocabulary.JSON_VALEUR)
    private String valeur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
