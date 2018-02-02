package com.neos.trackandroll.cervo.communication.maestro.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class MaestroMessage {

    @SerializedName(MaestroProtocolVocabulary.JSON_PROCESS)
    private String process;

    @SerializedName(MaestroProtocolVocabulary.JSON_PARAMS)
    private Params params;



    public MaestroMessage(String process, Params params) {
        this.process = process;
        this.params = params;
    }




    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
