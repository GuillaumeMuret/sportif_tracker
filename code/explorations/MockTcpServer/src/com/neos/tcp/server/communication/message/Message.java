package com.neos.tcp.server.communication.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocoleVocabulary;


public class Message {

    @SerializedName(ProtocoleVocabulary.JSON_PROCESS)
    private String process;

    @SerializedName(ProtocoleVocabulary.JSON_PARAMS)
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
