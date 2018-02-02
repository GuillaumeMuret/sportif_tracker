package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.LinkedList;

public class PositionData {

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    /**
     * The total distance
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_TOTAL_DISTANCE)
    private float totalDistance;

    /**
     * The json results
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_RESULTS)
    private LinkedList<Result> results;

    /**
     * Main constructor of the position data
     * @param sensorId : the sensor id
     * @param totalDistance : the total distance
     * @param results : the results
     */
    public PositionData(String sensorId, float totalDistance, LinkedList<Result> results) {
        this.sensorId = sensorId;
        this.totalDistance = totalDistance;
        this.results = results;
    }

    /**
     * Getter of the sensor id
     * @return : the sensor id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor id
     * @param sensorId : the new sensor id
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the result list
     * @return : the result list
     */
    public LinkedList<Result> getResults() {
        return results;
    }

    /**
     * Setter of the result list
     * @param results : the new result list
     */
    public void setResults(LinkedList<Result> results) {
        this.results = results;
    }

    /**
     * Getter of the total distance
     * @return : the total distance
     */
    public float getTotalDistance() {
        return totalDistance;
    }

    /**
     * Setter of the total distance
     * @param totalDistance : the total distance
     */
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
}
