package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.LinkedList;

public class CardioData {

    /**
     * the sensor ID
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    /**
     * The results of the sensor data
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private LinkedList<Result> results;

    /**
     * The bpm max received
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_BPM_MAX)
    private int bpmMax;

    /**
     * Main constructor of the cardio data
     *
     * @param sensorId : the sensor id
     * @param results  : the results of the cardio data
     * @param bpmMax   : the bpm max of the data
     */
    public CardioData(String sensorId, LinkedList<Result> results, int bpmMax) {
        this.sensorId = sensorId;
        this.results = results;
        this.bpmMax = bpmMax;
    }

    /**
     * Getter of the sensor id
     *
     * @return : the sensor id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor ID
     *
     * @param sensorId : the new sensor ID
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the results
     *
     * @return : the results
     */
    public LinkedList<Result> getResults() {
        return results;
    }

    /**
     * Setter of the results
     *
     * @param results : the new results
     */
    public void setResults(LinkedList<Result> results) {
        this.results = results;
    }

    /**
     * Getter of the Bpm max
     *
     * @return : the BPM max
     */
    public int getBpmMax() {
        return bpmMax;
    }

    /**
     * Setter of the BPM Max
     *
     * @param bpmMax : the bpm max
     */
    public void setBpmMax(int bpmMax) {
        this.bpmMax = bpmMax;
    }
}
