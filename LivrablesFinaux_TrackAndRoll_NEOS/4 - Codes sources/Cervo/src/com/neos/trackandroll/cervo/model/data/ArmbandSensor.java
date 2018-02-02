package com.neos.trackandroll.cervo.model.data;

public class ArmbandSensor {

    public enum ArmbandSensorState {
        CONNECTED,
        DISCONNECTED
    }

    /**
     * The sensor id
     */
    private String id;

    /**
     * The armband sensor state
     */
    private ArmbandSensorState sensorState;

    /**
     * Main constructor of the armband sensor
     * @param id : the id of the sensor
     * @param sensorState : the sensor state
     */
    public ArmbandSensor(String id, ArmbandSensorState sensorState) {
        this.id = id;
        this.sensorState = sensorState;
    }

    /**
     * Getter of teh sensor state
     * @return : the sensor state
     */
    public ArmbandSensorState getSensorState() {
        return sensorState;
    }

    /**
     * Setter of the sensor state
     * @param sensorState : the sensor state
     */
    public void setSensorState(ArmbandSensorState sensorState) {
        this.sensorState = sensorState;
    }

    /**
     * Getter of the sensor id
     * @return : the sensor id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of the sensor id
     * @param id : the new sensor id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of the heart beat sensor state
     * @return : the heart beat sensor state
     */
    public ArmbandSensorState getState() {
        return sensorState;
    }

    /**
     * Getter of the sensor string state
     * @return : the string state
     */
    public String getStringState() {
        return sensorState == ArmbandSensorState.CONNECTED
                ? "CONNECTED"
                : "DISCONNECTED";
    }

    /**
     * Setter of the heart beat sensor state
     * @param sensorState : the heart beat sensor state
     */
    public void setState(ArmbandSensorState sensorState) {
        this.sensorState = sensorState;
    }
}
