package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

import java.util.Date;

public class Result {

    @SerializedName(ProtocolVocabulary.JSON_DATE)
    private String date;

    @SerializedName(ProtocolVocabulary.JSON_X)
    private float x;

    @SerializedName(ProtocolVocabulary.JSON_Y)
    private float y;

    @SerializedName(ProtocolVocabulary.JSON_V)
    private float v;

    @SerializedName(ProtocolVocabulary.JSON_VALEUR)
    private int valeur;


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

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
