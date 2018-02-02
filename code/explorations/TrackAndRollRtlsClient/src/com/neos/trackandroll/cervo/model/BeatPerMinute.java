package com.neos.trackandroll.cervo.model;

import java.util.Date;

public class BeatPerMinute {

    /**
     * The bpm value for 10 seconds
     */
    private int bpmValue;

    /**
     * The sensor id
     */
    private String sensorId;

    /**
     * The date of the reception of the data
     */
    private Date date;

    /**
     * Main constructor of the beat per minute data
     * @param bpmValue : the bpm value for 10 seconds
     * @param sensorId : the sensor id
     * @param date : the date when the message has been received
     */
    public BeatPerMinute(int bpmValue, String sensorId, Date date) {
        this.bpmValue = bpmValue;
        this.sensorId = sensorId;
        this.date = date;
    }

    /**
     * Getter of the data date
     * @return : the date of the reception of the data
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter of the bpm value
     * @return : the bpm value
     */
    public int getBpmValue() {
        return bpmValue;
    }

    /**
     * Setter of the bpm value
     * @param bpmValue : the bpm value
     */
    public void setBpmValue(int bpmValue) {
        this.bpmValue = bpmValue;
    }

    /**
     * Getter of the sensor ID
     * @return : the sensor ID
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor ID
     * @param sensorId : the new sensor ID
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
}
