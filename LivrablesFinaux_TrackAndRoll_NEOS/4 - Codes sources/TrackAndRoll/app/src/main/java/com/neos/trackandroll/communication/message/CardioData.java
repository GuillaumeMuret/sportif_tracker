package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

import java.util.LinkedList;

public class CardioData {

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_ID)
    private String capteurId;

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private LinkedList<Result> results;

    @SerializedName(ProtocolVocabulary.JSON_BPM_MAX)
    private int bpmMax;


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

    public int getBpmMax() {
        return bpmMax;
    }

    public void setBpmMax(int bpmMax) {
        this.bpmMax = bpmMax;
    }
}
