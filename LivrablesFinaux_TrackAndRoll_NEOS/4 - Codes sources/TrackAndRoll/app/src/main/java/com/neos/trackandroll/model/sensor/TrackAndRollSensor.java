package com.neos.trackandroll.model.sensor;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;
import com.neos.trackandroll.model.constant.Constant;

public class TrackAndRollSensor {

    public static final String SENSOR_TYPE_POSITION = ProtocolVocabulary.CAPTEUR_TYPE_DOSSARD;
    public static final String SENSOR_TYPE_HEART_BEAT = ProtocolVocabulary.CAPTEUR_TYPE_BRASSARD;

    @SerializedName("sensor_id")
    private String sensorId;

    @SerializedName("custom_sensor_id")
    private String customSensorId;

    @SerializedName("sensor_type")
    private String sensorType;

    @SerializedName("sensor_allocated_player_key")
    private String allocatedPlayerKey;

    @SerializedName("sensor_state")
    private String sensorState;

    /**
     * Main constructor of TrackAndRollSensor
     * @param sensorId
     * @param sensorType
     * @param customSensorId
     * @param sensorState
     */
    public TrackAndRollSensor(String sensorId, String sensorType, String customSensorId, String sensorState) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.customSensorId = customSensorId;
        this.sensorState = sensorState;
        this.allocatedPlayerKey = Constant.SENSOR_NON_ASSIGNED_NAME;
    }

    /**
     * Method call to get the sensor id
     * @return the id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Method call to set the sensor id
     * @param sensorId
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Method call to get the custom sensor id
     * @return the custom id
     */
    public String getCustomSensorId() {
        return customSensorId;
    }

    /**
     * Method call to set the custom sensor id
     * @param customSensorId
     */
    public void setCustomSensorId(String customSensorId) {
        this.customSensorId = customSensorId;
    }

    /**
     * Method call to get the allocated player key
     * @return the allocated player key
     */
    public String getAllocatedPlayerKey() {
        return allocatedPlayerKey;
    }

    /**
     * Method call to set the allocated player key
     * @param allocatedPlayerKey
     */
    public void setAllocatedPlayerKey(String allocatedPlayerKey) {
        this.allocatedPlayerKey = allocatedPlayerKey;
    }

    /**
     * Method call to get the sensor date
     * @return the sensor date
     */
    public String getSensorState() {
        return sensorState;
    }

    /**
     * Method call to set the sensor date
     * @param sensorState
     */
    public void setSensorState(String sensorState) {
        this.sensorState = sensorState;
    }

    /**
     * Method call to get the sensor type
     * @return the sensor type
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Method call to return if a sensor is equals to a heart beat sensor
     * @return the sensorType
     */
    public boolean isHeartBeatSensor() {
        return sensorType.equals(SENSOR_TYPE_HEART_BEAT);
    }

    /**
     * Method call to set the sensor type
     * @param sensorType
     */
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }
}
