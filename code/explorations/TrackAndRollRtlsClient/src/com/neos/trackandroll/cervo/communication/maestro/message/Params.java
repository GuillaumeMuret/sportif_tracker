package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class Params {


    @SerializedName(MaestroProtocolVocabulary.JSON_CAPTEURS)
    private Sensor[] sensors;

    @SerializedName(MaestroProtocolVocabulary.JSON_DATAS)
    private Data data;

    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String capteurId;

    @SerializedName(MaestroProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;

    public Sensor[] getSensors() {
        return sensors;
    }

    public void setSensors(Sensor[] sensors) {
        this.sensors = sensors;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(String capteurType) {
        this.capteurType = capteurType;
    }

    public String getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }
}
