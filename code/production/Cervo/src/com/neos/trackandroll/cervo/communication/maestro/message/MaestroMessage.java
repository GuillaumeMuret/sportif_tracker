package com.neos.trackandroll.cervo.communication.maestro.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

public class MaestroMessage {

    /**
     * Process of the message
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_PROCESS)
    private String process;

    /**
     * The different params of the message
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_PARAMS)
    private Params params;

    /**
     * Main constructor of the Maestro message
     * @param process : the process of the maestro message
     * @param params : the params
     */
    public MaestroMessage(String process, Params params) {
        this.process = process;
        this.params = params;
    }

    /**
     * Getter of the maestro process
     * @return : the maestro process
     */
    public String getProcess() {
        return process;
    }

    /**
     * Setter of the maestro process
     * @param process : the new maestro process
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * Getter of the maestro params
     * @return : the maestro params
     */
    public Params getParams() {
        return params;
    }

    /**
     * Setter of the maestro params
     * @param params : the params
     */
    public void setParams(Params params) {
        this.params = params;
    }
}
