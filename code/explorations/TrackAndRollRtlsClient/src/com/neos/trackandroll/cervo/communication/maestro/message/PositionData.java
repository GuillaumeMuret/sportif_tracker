package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.LinkedList;

public class PositionData {

    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String capteurId;

    @SerializedName(MaestroProtocolVocabulary.JSON_TOTAL_DISTANCE)
    private float totalDistance;

    @SerializedName(MaestroProtocolVocabulary.JSON_RESULTS)
    private LinkedList<Result> results;

    public PositionData(String capteurId, float totalDistance, LinkedList<Result> results) {
        this.capteurId = capteurId;
        this.totalDistance = totalDistance;
        this.results = results;
    }

    public String getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }

    public LinkedList<Result> getResults() {
        return results;
    }

    public void setResults(LinkedList<Result> results) {
        this.results = results;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
}
