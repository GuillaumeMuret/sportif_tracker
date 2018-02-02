package com.neos.trackandroll.cervo.communication.armband.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;


public class ArmbandMessage {

    /**
     * The process called
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_PROCESS)
    private String process;

    /**
     * The different params associated to the process
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_PARAMS)
    private Params params;

    /**
     * Main constructor of the armband message
     * @param process : the process
     * @param params : the params
     */
    public ArmbandMessage(String process, Params params) {
        this.process = process;
        this.params = params;
    }

    /**
     * Getter of the message process
     * @return : the message process
     */
    public String getProcess() {
        return process;
    }

    /**
     * Setter of the message process
     * @param process : the message process
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * Getter of the message params
     * @return : the message params
     */
    public Params getParams() {
        return params;
    }

    /**
     * Setter of the armband message params
     * @param params : the params
     */
    public void setParams(Params params) {
        this.params = params;
    }



}
