package com.neos.tcp.server.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocoleVocabulary;

import java.util.Date;

public class Result {

    @SerializedName(ProtocoleVocabulary.JSON_DATE)
    private Date date;

    @SerializedName(ProtocoleVocabulary.JSON_X)
    private float x;

    @SerializedName(ProtocoleVocabulary.JSON_Y)
    private float y;

    @SerializedName(ProtocoleVocabulary.JSON_V)
    private float v;

    @SerializedName(ProtocoleVocabulary.JSON_VALEUR)
    private int valeur;







    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
