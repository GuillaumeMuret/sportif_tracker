package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

import java.util.LinkedList;

public class PositionData {

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_ID)
    private String capteurId;

    @SerializedName(ProtocolVocabulary.JSON_TOTAL_DISTANCE)
    private float totalDistance;

    @SerializedName(ProtocolVocabulary.JSON_RESULTS)
    private LinkedList<Result> results;

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
