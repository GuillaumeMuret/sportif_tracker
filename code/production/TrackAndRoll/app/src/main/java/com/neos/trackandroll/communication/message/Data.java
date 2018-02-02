package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

import java.util.HashMap;
import java.util.LinkedList;

public class Data {

    @SerializedName(ProtocolVocabulary.JSON_POSITION_DATAS)
    private HashMap<String, PositionData> positionDataMap;

    @SerializedName(ProtocolVocabulary.JSON_VITESSE_DATAS)
    private LinkedList<VitesseData> vitesseData;

    @SerializedName(ProtocolVocabulary.JSON_ACCELERATION_EFFORT_DATAS)
    private LinkedList<AccelerationEffortData> accelerationEffortData;

    @SerializedName(ProtocolVocabulary.JSON_CARDIO_DATAS)
    private HashMap<String, CardioData> cardioDataMap;

    @SerializedName(ProtocolVocabulary.JSON_TOTAL_SESSION_TIME_IN_SEC)
    private int totalSessionTimeInSec;

    public HashMap<String, PositionData> getPositionDataMap() {
        return positionDataMap;
    }

    public void setPositionDataMap(HashMap<String, PositionData> positionDataMap) {
        this.positionDataMap = positionDataMap;
    }

    public LinkedList<VitesseData> getVitesseData() {
        return vitesseData;
    }

    public void setVitesseData(LinkedList<VitesseData> vitesseData) {
        this.vitesseData = vitesseData;
    }

    public LinkedList<AccelerationEffortData> getAccelerationEffortData() {
        return accelerationEffortData;
    }

    public void setAccelerationEffortData(LinkedList<AccelerationEffortData> accelerationEffortData) {
        this.accelerationEffortData = accelerationEffortData;
    }

    public HashMap<String, CardioData> getCardioDataMap() {
        return cardioDataMap;
    }

    public void setCardioDataMap(HashMap<String, CardioData> cardioDataMap) {
        this.cardioDataMap = cardioDataMap;
    }

    public int getTotalSessionTimeInSec() {
        return totalSessionTimeInSec;
    }

    public void setTotalSessionTimeInSec(int totalSessionTimeInSec) {
        this.totalSessionTimeInSec = totalSessionTimeInSec;
    }
}
