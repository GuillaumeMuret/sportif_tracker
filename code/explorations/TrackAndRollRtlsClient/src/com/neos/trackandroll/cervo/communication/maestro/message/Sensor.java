package com.neos.trackandroll.cervo.communication.maestro.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;


public class Sensor {

    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String capteurId;

    @SerializedName(MaestroProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;

    public Sensor(String capteurId, String capteurType) {
        this.capteurId = capteurId;
        this.capteurType = capteurType;
    }

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
}
