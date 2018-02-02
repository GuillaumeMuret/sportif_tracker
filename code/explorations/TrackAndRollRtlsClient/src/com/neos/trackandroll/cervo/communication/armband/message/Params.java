package com.neos.trackandroll.cervo.communication.armband.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.armband.protocol.ArmbandProtocolVocabulary;
import com.neos.trackandroll.cervo.utils.LogUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class Params {

    @SerializedName(ArmbandProtocolVocabulary.JSON_BPM_VALUE_FOR_TEN_SEC)
    private String bpmForTenSec;

    @SerializedName(ArmbandProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    public String getSensorId() {
        int sensorIdInt = (int)Long.parseLong(sensorId, 16);
        return String.valueOf(sensorIdInt);
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public int getBpmForTenSec() {
        return (int)Long.parseLong(bpmForTenSec, 16);
    }
}
