package com.neos.trackandroll.cervo.model.data;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.message.NodeDetails;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class Tag {

    public enum TagState {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    /**
     * The tag state
     */
    private TagState state;

    /**
     * Main constructor of the tag
     * @param id : the id of the tag
     */
    public Tag(String id) {
        this.id = id;
        this.state = TagState.UNKNOWN;
    }

    /**
     * The tag id
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ID)
    private String id;

    /**
     * The node details
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_DETAILS)
    private NodeDetails nodeDetails;

    /**
     * The tag coordinates
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATES)
    private Coordinates coordinates;

    /**
     * Getter of the tag state
     * @return : the tag state
     */
    public TagState getState() {
        return state;
    }

    /**
     * Getter of the string state of the tag
     * @return : the string state of the tag sensor
     */
    public String getStringState() {
        return state == TagState.CONNECTED
                ? "CONNECTED"
                : "DISCONNECTED";
    }

    /**
     * Setter of the tag state
     * @param state : the tag state
     */
    public void setState(TagState state) {
        this.state = state;
    }

    /**
     * Getter of the tag id
     * @return : the tag id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of the tag id
     * @param id : the tag id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of the node details
     * @return : the node details
     */
    public NodeDetails getNodeDetails() {
        return nodeDetails;
    }

    /**
     * Setter of the node details
     * @param nodeDetails : the node details
     */
    public void setNodeDetails(NodeDetails nodeDetails) {
        this.nodeDetails = nodeDetails;
    }

    /**
     * Getter of the tag coordinates
     * @return : the tag coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Setter of the tag coordinates
     * @param coordinates : the new tag coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
