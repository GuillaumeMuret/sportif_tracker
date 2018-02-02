package com.neos.trackandroll.cervo.model;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;

public class Coordinates {

    /**
     * The X coordinate
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATE_X)
    private Float coorX;

    /**
     * The Y coordinate
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATE_Y)
    private Float coorY;

    /**
     * The Z coordinate
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COORDINATE_Z)
    private Float coorZ;

    /**
     * The heading
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_HEADING)
    private int heading;

    /**
     * The Position Quality Factor (PQF)
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_PQF)
    private int pqf;

    /**
     * Main constructor of a coordinate
     * @param coorX : the x coordinate
     * @param coorY : the y coordinate
     * @param coorZ : the z coordinate
     */
    public Coordinates(Float coorX, Float coorY, Float coorZ) {
        this.coorX = coorX;
        this.coorY = coorY;
        this.coorZ = coorZ;
    }

    /**
     * Getter of the X coordinate
     * @return : the x coordinate
     */
    public Float getCoorX() {
        return coorX;
    }

    /**
     * Getter of the Y coordinate
     * @return : the Y coordinate
     */
    public Float getCoorY() {
        return coorY;
    }

    /**
     * Getter of the Z coordinate
     * @return : the Z coordinate
     */
    public Float getCoorZ() {
        return coorZ;
    }

    /**
     * Getter of the Heading
     * @return : the heading
     */
    public int getHeading() {
        return heading;
    }

    /**
     * Getter of the Position Quality Factor (PQF)
     * @return : return the Position Quality Factor (PQF)
     */
    public int getPqf() {
        return pqf;
    }
}
