package com.neos.trackandroll.communication.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;


public class Message {

    @SerializedName(ProtocolVocabulary.JSON_PROCESS)
    private String process;

    @SerializedName(ProtocolVocabulary.JSON_PARAMS)
    private Params params;



    public Message(String process, Params params) {
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
