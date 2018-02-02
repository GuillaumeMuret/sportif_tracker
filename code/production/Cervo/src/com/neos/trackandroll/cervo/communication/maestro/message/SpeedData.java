package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class SpeedData {

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private int sensorId;

    /**
     * The average speed
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_AVERAGE_SPEED)
    private float averageSpeed;

    /**
     * The result list of the speed data
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_RESULTS)
    private Result[] results;

    /**
     * Main constructor of the speed data
     * @param sensorId : the sensor id
     * @param averageSpeed : the average speed
     * @param results : the result array of the speed data
     */
    public SpeedData(int sensorId, float averageSpeed, Result[] results) {
        this.sensorId = sensorId;
        this.averageSpeed = averageSpeed;
        this.results = results;
    }

    /**
     * Getter of the sensor id
     * @return : the sensor id
     */
    public int getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor id
     * @param sensorId : the new sensor id
     */
    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the result array
     * @return : the result array
     */
    public Result[] getResults() {
        return results;
    }

    /**
     * Setter of the result array
     * @param results : the new result array
     */
    public void setResults(Result[] results) {
        this.results = results;
    }

    /**
     * Getter of the average speed
     * @return : the average speed
     */
    public float getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Setter of the average speed
     * @param averageSpeed : the average speed
     */
    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
