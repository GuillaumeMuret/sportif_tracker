package com.neos.trackandroll.cervo.communication.maestro.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class AccelerationEffortData {

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private int capteurId;

    /**
     * The results of the message
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_RESULTS)
    private Result[] results;

    /**
     * Getter of the result of the sensor id
     * @return : the sensor id
     */
    public int getCapteurId() {
        return capteurId;
    }

    /**
     * Setter of the sensor id
     * @param capteurId : the new sensor id
     */
    public void setCapteurId(int capteurId) {
        this.capteurId = capteurId;
    }

    /**
     * Getter of the results
     * @return : the results
     */
    public Result[] getResults() {
        return results;
    }

    /**
     * Setter of the results
     * @param results : the results
     */
    public void setResults(Result[] results) {
        this.results = results;
    }
}
