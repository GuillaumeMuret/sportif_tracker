package com.neos.trackandroll.communication.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;


public class Capteur {

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_ID)
    private String capteurId;

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;

    @SerializedName(ProtocolVocabulary.JSON_SENSOR_STATE)
    private String sensorState;

    public String getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }

    public String getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(String capteurType) {
        this.capteurType = capteurType;
    }

    public String getSensorState() {
        return sensorState;
    }

    public void setSensorState(String sensorState) {
        this.sensorState = sensorState;
    }
}
