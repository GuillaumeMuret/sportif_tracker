package com.neos.trackandroll.cervo.communication.anchor.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class NodeDetails {

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_HW_VERSION)
    private String hwVersion;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_LDRFW_VERSION)
    private String ldrfwVersion;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_FW_VERSION)
    private String fwVersion;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_OPTIONS)
    private String nodeOptions;

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getLdrfwVersion() {
        return ldrfwVersion;
    }

    public void setLdrfwVersion(String ldrfwVersion) {
        this.ldrfwVersion = ldrfwVersion;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public String getNodeOptions() {
        return nodeOptions;
    }

    public void setNodeOptions(String nodeOptions) {
        this.nodeOptions = nodeOptions;
    }
}
