package com.neos.trackandroll.cervo.communication.anchor.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class Measure {

    /**
     * The anchor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ANCHOR)
    private String anchor;

    /**
     * The time quality factor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_TQF)
    private float tqf;

    /**
     * The rssi - receive signal strength indicator in dB
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_RSSI)
    private float rssi;

    /**
     * The distance in meter
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_DISTANCE)
    private float distance;

    /**
     * Getter of the anchor
     * @return : the anchor
     */
    public String getAnchor() {
        return anchor;
    }

    /**
     * Getter of the TQF
     * @return : the TQF
     */
    public float getTqf() {
        return tqf;
    }

    /**
     * Getter of the RSSI
     * @return : the RSSI
     */
    public float getRssi() {
        return rssi;
    }

    /**
     * Getter of the distance
     * @return : the distance
     */
    public float getDistance() {
        return distance;
    }
}
