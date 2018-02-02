package com.neos.trackandroll.cervo.communication.maestro.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;


public class Sensor {

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    /**
     * The sensor type
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_TYPE)
    private String sensorType;

    /**
     * The sensor state
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_STATE)
    private String sensorState;

    /**
     * Main constructor of the sensor
     * @param sensorId : the sensor id
     * @param sensorType : the sensor type
     * @param sensorState : the sensor sate
     */
    public Sensor(String sensorId, String sensorType, String sensorState) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorState = sensorState;
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
     * @param sensorId : the sensor id
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the sensor type
     * @return : the sensor type
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Setter of the sensor type
     * @param sensorType : the new sensor type
     */
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Getter of the sensor state
     * @return : the sensor state
     */
    public String getSensorState() {
        return sensorState;
    }

    /**
     * Setter of the sensor state
     * @param sensorState : the sensor state
     */
    public void setSensorState(String sensorState) {
        this.sensorState = sensorState;
    }
}
