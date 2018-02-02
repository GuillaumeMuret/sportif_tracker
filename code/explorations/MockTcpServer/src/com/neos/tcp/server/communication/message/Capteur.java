package com.neos.tcp.server.communication.message;

import com.google.gson.annotations.SerializedName;
import com.neos.tcp.server.communication.protocole.ProtocoleVocabulary;


public class Capteur {

    @SerializedName(ProtocoleVocabulary.JSON_CAPTEUR_ID)
    private int capteurId;

    @SerializedName(ProtocoleVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;






    public int getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(int capteurId) {
        this.capteurId = capteurId;
    }

    public String getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(String capteurType) {
        this.capteurType = capteurType;
    }
}
