package com.neos.trackandroll.cervo.model;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.message.NodeDetails;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class Tag {

    public enum TagState{
        UNKNOWN,
        CONNECTED,
        DEFAULTING,
        DISCONNECTED
    }

    private TagState state;

    public Tag(String id) {
        this.id = id;
        this.state = TagState.UNKNOWN;
    }

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ID)
    private String id;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_DETAILS)
    private NodeDetails nodeDetails;

    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATES)
    private Coordinates coordinates;

    public TagState getState() {
        return state;
    }

    public void setState(TagState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NodeDetails getNodeDetails() {
        return nodeDetails;
    }

    public void setNodeDetails(NodeDetails nodeDetails) {
        this.nodeDetails = nodeDetails;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
