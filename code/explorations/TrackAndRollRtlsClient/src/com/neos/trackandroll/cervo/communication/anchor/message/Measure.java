package com.neos.trackandroll.cervo.communication.anchor.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;
import com.neos.trackandroll.cervo.model.Coordinates;

public class Measure {

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ANCHOR)
    private String anchor;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_TQF)
    private float tqf;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_RSSI)
    private float rssi;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_DISTANCE)
    private float distance;

    public String getAnchor() {
        return anchor;
    }

    public float getTqf() {
        return tqf;
    }

    public float getRssi() {
        return rssi;
    }

    public float getDistance() {
        return distance;
    }
}
