package com.neos.trackandroll.cervo.communication.armband.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.armband.protocol.ArmbandProtocolVocabulary;

public class Params {

    /**
     * The radix for the conversion hex to int
     */
    private static final int RADIX_HEXADECIMAL = 16;

    /**
     * The bpm for 10 second received
     */
    @SerializedName(ArmbandProtocolVocabulary.JSON_BPM_VALUE_FOR_TEN_SEC)
    private String bpmForTenSec;

    /**
     * The sensor id
     */
    @SerializedName(ArmbandProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

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
     * Getter of the bpm for 10 second
     * @return : the bpm for 10 second
     */
    public int getBpmForTenSec() {
        return (int)Long.parseLong(bpmForTenSec, RADIX_HEXADECIMAL);
    }
}
