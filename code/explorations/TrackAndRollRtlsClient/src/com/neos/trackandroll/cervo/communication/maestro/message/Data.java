package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.HashMap;

public class Data {

    /**
     * The position data map. KEY = captor ID and value is the position data
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_POSITION_DATAS)
    private HashMap<String, PositionData> positionDataMap;

    /**
     * The speed data list
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_VITESSE_DATAS)
    private VitesseData[] vitesseData;

    /**
     * The acceleration effort data
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_ACCELERATION_EFFORT_DATAS)
    private AccelerationEffortData[] accelerationEffortData;

    /**
     * The cardio data map. KEY is the sensor ID and value is the cardio data for the sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_CARDIO_DATAS)
    private HashMap<String, CardioData> cardioDataMap;

    /**
     * The total session time in second
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_TOTAL_SESSION_TIME_IN_SEC)
    private int totalSessionTimeInSec;

    /**
     * Getter of the position data map
     * @return : the position data map
     */
    public HashMap<String, PositionData> getPositionDataMap() {
        return positionDataMap;
    }

    /**
     * Setter of the position data map
     * @param positionDataMap : the position data map
     */
    public void setPositionDataMap(HashMap<String, PositionData> positionDataMap) {
        this.positionDataMap = positionDataMap;
    }

    /**
     * Getter of the speed data
     * @return : the speed data
     */
    public VitesseData[] getVitesseData() {
        return vitesseData;
    }

    /**
     * Setter of the speed data
     * @param vitesseData : the new speed data
     */
    public void setVitesseData(VitesseData[] vitesseData) {
        this.vitesseData = vitesseData;
    }

    /**
     * Getter of the acceleration effort data
     * @return : the acceleration effort data
     */
    public AccelerationEffortData[] getAccelerationEffortData() {
        return accelerationEffortData;
    }

    /**
     * Setter of the acceleration effort data
     * @param accelerationEffortData : the acceleration effort data
     */
    public void setAccelerationEffortData(AccelerationEffortData[] accelerationEffortData) {
        this.accelerationEffortData = accelerationEffortData;
    }

    /**
     * Getter of the cardio data map
     * @return : the cardio data map
     */
    public HashMap<String, CardioData> getCardioDataMap() {
        return cardioDataMap;
    }

    /**
     * Setter of the cardio data map
     * @param cardioDataMap : the cardio data map
     */
    public void setCardioDataMap(HashMap<String, CardioData> cardioDataMap) {
        this.cardioDataMap = cardioDataMap;
    }

    /**
     * Getter of the total session time in sec
     * @return : the total session time in sec
     */
    public int getTotalSessionTimeInSec() {
        return totalSessionTimeInSec;
    }

    /**
     * Setter of the total session time in second
     * @param totalSessionTimeInSec : the new total session time in sec
     */
    public void setTotalSessionTimeInSec(int totalSessionTimeInSec) {
        this.totalSessionTimeInSec = totalSessionTimeInSec;
    }
}
