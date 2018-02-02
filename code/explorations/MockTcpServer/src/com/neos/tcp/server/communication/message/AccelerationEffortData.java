package com.neos.tcp.server.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.tcp.server.communication.protocole.ProtocoleVocabulary;

public class AccelerationEffortData {

    @SerializedName(ProtocoleVocabulary.JSON_CAPTEUR_ID)
    private int capteurId;

    @SerializedName(ProtocoleVocabulary.JSON_RESULTS)
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
