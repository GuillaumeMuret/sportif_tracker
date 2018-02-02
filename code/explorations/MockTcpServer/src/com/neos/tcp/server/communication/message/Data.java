package com.neos.tcp.server.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.tcp.server.communication.protocole.ProtocoleVocabulary;

public class Data {

    @SerializedName(ProtocoleVocabulary.JSON_POSITION_DATAS)
    private PositionData[] positionData;

    @SerializedName(ProtocoleVocabulary.JSON_VITESSE_DATAS)
    private VitesseData[] vitesseData;

    @SerializedName(ProtocoleVocabulary.JSON_ACCELERATION_EFFORT_DATAS)
    private AccelerationEffortData[] accelerationEffortData;

    @SerializedName(ProtocoleVocabulary.JSON_CARDIO_DATAS)
    private CardioData[] cardioData;






    public PositionData[] getPositionData() {
        return positionData;
    }

    public void setPositionData(PositionData[] positionData) {
        this.positionData = positionData;
    }

    public VitesseData[] getVitesseData() {
        return vitesseData;
    }

    public void setVitesseData(VitesseData[] vitesseData) {
        this.vitesseData = vitesseData;
    }

    public AccelerationEffortData[] getAccelerationEffortData() {
        return accelerationEffortData;
    }

    public void setAccelerationEffortData(AccelerationEffortData[] accelerationEffortData) {
        this.accelerationEffortData = accelerationEffortData;
    }

    public CardioData[] getCardioData() {
        return cardioData;
    }

    public void setCardioData(CardioData[] cardioData) {
        this.cardioData = cardioData;
    }
}
