package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

public class AccelerationEffortData {

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_ID)
    private int capteurId;

    @SerializedName(ProtocolVocabulary.JSON_RESULTS)
    private Result[] results;





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
}
