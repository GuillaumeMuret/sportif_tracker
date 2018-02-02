package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class VitesseData {

    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private int capteurId;

    @SerializedName(MaestroProtocolVocabulary.JSON_AVERAGE_SPEED)
    private float averageSpeed;

    @SerializedName(MaestroProtocolVocabulary.JSON_RESULTS)
    private Result[] results;

    public VitesseData(int capteurId, float averageSpeed, Result[] results) {
        this.capteurId = capteurId;
        this.averageSpeed = averageSpeed;
        this.results = results;
    }

    public int getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(int capteurId) {
        this.capteurId = capteurId;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
