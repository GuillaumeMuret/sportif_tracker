package com.neos.trackandroll.cervo.model.data;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class Anchor {

    public static final Coordinates POSITION_0X0108 = new Coordinates(0f, 0f, 0f);
    public static final Coordinates POSITION_0X00B8 = new Coordinates(-17f, 0f, 0f);
    public static final Coordinates POSITION_0X00C7 = new Coordinates(-4f, 2f, 0f);
    public static final Coordinates POSITION_0X00C4 = new Coordinates(11f, 0f, 0f);
    public static final Coordinates POSITION_0X00B9 = new Coordinates(0f, 11f, 0f);
    public static final Coordinates POSITION_0X00CA = new Coordinates(-8f, 11f, 0f);
    public static final Coordinates POSITION_0X00DF = new Coordinates(11f, 11f, 0f);
    public static final Coordinates POSITION_0X00BB = new Coordinates(-17f, 11f, 0f);

    public enum AnchorState {
        UNKNOWN,
        CONNECTED,
        DEFAULTING,
        DISCONNECTED
    }

    /**
     * The state of the anchor
     */
    private AnchorState state;

    /**
     * The ID fo the anchor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ID)
    private String id;

    /**
     * The coordinates of the anchor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATES)
    private Coordinates coordinates;

    /**
     * Main constructor of the Anchor
     * @param id : the id of the anchor
     */
    public Anchor(String id) {
        this.id = id;
        this.state = AnchorState.UNKNOWN;
    }

    /**
     * Getter of the anchor state
     * @return : the anchor state
     */
    public AnchorState getState() {
        return state;
    }

    /**
     * Setter of the anchor state
     * @param state : the anchor state
     */
    public void setState(AnchorState state) {
        this.state = state;
    }

    /**
     * Getter of the anchor id
     * @return : the anchor id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of the anchor id
     * @param id : the anchor id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of the anchor coordinates
     * @return : the anchor coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Setter of the anchor coordinates
     * @param coordinates : the anchor coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
