package com.neos.trackandroll.cervo.communication.anchor.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class NodeDetails {

    /**
     * The hardware version
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_HW_VERSION)
    private String hwVersion;

    /**
     * The ldrfw version (unknown param)
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_LDRFW_VERSION)
    private String ldrfwVersion;

    /**
     * The firmware version
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_FW_VERSION)
    private String fwVersion;

    /**
     * The node option
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_OPTIONS)
    private String nodeOptions;

    /**
     * Getter of the hardware version
     * @return : the hardware version
     */
    public String getHwVersion() {
        return hwVersion;
    }

    /**
     * Setter of the hardware version
     * @param hwVersion : the hardware version
     */
    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    /**
     * Getter of the ldrfw version (unknown what it is)
     * @return : the ldrfw version
     */
    public String getLdrfwVersion() {
        return ldrfwVersion;
    }

    /**
     * Setter of the ldrfw version
     * @param ldrfwVersion : the ldrfw version
     */
    public void setLdrfwVersion(String ldrfwVersion) {
        this.ldrfwVersion = ldrfwVersion;
    }

    /**
     * Getter of the fw version
     * @return : the fw version
     */
    public String getFwVersion() {
        return fwVersion;
    }

    /**
     * Setter of the fw version
     * @param fwVersion : the fw version
     */
    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    /**
     * Getter of the node options
     * @return : the node options
     */
    public String getNodeOptions() {
        return nodeOptions;
    }

    /**
     * Setter of the node options
     * @param nodeOptions : the node options
     */
    public void setNodeOptions(String nodeOptions) {
        this.nodeOptions = nodeOptions;
    }
}
