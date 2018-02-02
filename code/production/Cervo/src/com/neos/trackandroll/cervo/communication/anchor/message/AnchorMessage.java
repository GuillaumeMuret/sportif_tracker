package com.neos.trackandroll.cervo.communication.anchor.message;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProtocolVocabulary;
import com.neos.trackandroll.cervo.model.data.Anchor;
import com.neos.trackandroll.cervo.model.data.Coordinates;
import com.neos.trackandroll.cervo.model.data.Tag;


public class AnchorMessage {

    /**
     * The command to execute by the open RTLS system
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_COMMAND)
    private String command;

    /**
     * The response command of the RTLS system
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_RESPONSE)
    private String response;

    /**
     * The used
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ID)
    private String id;

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
     * The id of the anchor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ANCHOR)
    private String anchor;

    /**
     * The distance received
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_DISTANCE)
    private String dist;

    /**
     * The time quality factor
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_TQF)
    private String tqf;

    /**
     * The rssi - receive signal strength indicator in dB
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_RSSI)
    private String rssi;

    /**
     * The different measures received
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_MEASURE)
    private Measure[] measures;

    /**
     * The different tag received
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_TAGS)
    private Tag[] tags;

    /**
     * The different anchor received
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_ANCHORS)
    private Anchor[] anchors;

    /**
     * cfgId (mandatory) - Id of configuration. If the ID does not exist, it will be created if the ID is in permitted
     * range. If it exists, the new parameters will overwrite the existing settings. In the current version, the cfgID
     * can be in the range of <0; 1>. The master will test nodeMask with the node address using configuration with
     * lowest cfgId number. The first configuration which matches will be used.
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_CFG_ID)
    private String cfgId;

    /**
     * nodeOptions - twr (Two-Way Ranging), tdoa (Time Difference of Arrival)
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_OPTIONS)
    private String nodeOptions;

    /**
     * nodeMask - the configurator will check if the node address fits the nodeMask. If not matched, the configuration
     * will be skipped. If matched, the configuration will be sent to the node and the other configurations will be
     * ignored. I.e. (0xDECAFFFFFFFFFFFF & "node_id" == "node_id") will result in applying the configuration rule,
     * (0xDECAFFFFFFFFFFFF & "node_id" != "node_id") will result in skipping the rule
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_NODE_MASK)
    private String nodeMask;

    /**
     * msecRefreshInterval - refresh interval of the node in mili-seconds
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_MSEC_REFRESH_INTERVAL)
    private String msecRefreshInterval;

    /**
     * msecTsync - required time synchronization interval in mili-seconds
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_MSEC_T_SYNC)
    private String msecTsync;

    /**
     * secExpiration - expiration of the configuration in seconds
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_SEC_EXPIRATION)
    private String secExpiration;

    /**
     * locEngine - none (do not use internal Location Engine), 1 (internal Location Engine)
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_LOC_ENGINE)
    private String locEngine;

    /**
     * posFilter - none (disable all filtering), ma (Moving Average)
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_POS_FILTER)
    private String posFilter;

    /**
     * posFilterArg - argument for posFilter, e.g. for posFilter=ma, posFilterArg=3 meaning moving average coefficient
     * is set to 3.
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_POS_FILTER_ARG)
    private String posFilterArg;

    /**
     * downloadRate - number of refresh intervals after which node/tag, will ask master for user data download.
     */
    @SerializedName(AnchorProtocolVocabulary.JSON_RTLS_DOWNLOAD_RATE)
    private String downloadRate;

    /**
     * Message for the node config command
     *
     * @param command             : the command
     * @param msecRefreshInterval : the refresh interval time
     * @param msecTsync           : the period of synchronization
     * @param secExpiration       : the time of expiration
     * @param locEngine           : the loc engine
     * @param posFilter           : the pos filter
     * @param downloadRate        : the download rate
     */
    public AnchorMessage(
            String command,
            String msecRefreshInterval,
            String msecTsync,
            String secExpiration,
            String locEngine,
            String posFilter,
            String downloadRate) {
        this.command = command;
        this.cfgId = "0";
        this.nodeOptions = "twr";
        this.nodeMask = "0xDECAFFFFFFFFFF";
        this.msecRefreshInterval = msecRefreshInterval;
        this.msecTsync = msecTsync;
        this.secExpiration = secExpiration;
        this.locEngine = locEngine;
        this.posFilter = posFilter;
        this.downloadRate = downloadRate;
    }

    /**
     * Anchor message constructor with just the command
     * @param command : the command to execute
     */
    public AnchorMessage(String command) {
        this.command = command;
    }

    /**
     * The anchor message to set the position of the different anchor
     * @param command : the command to execute
     * @param id : the id of the anchor
     * @param coordinates : the coordinates of the anchor to place
     */
    public AnchorMessage(String command, String id, Coordinates coordinates) {
        this.command = command;
        this.id = id;
        this.coorX = coordinates.getCoorX();
        this.coorY = coordinates.getCoorY();
        this.coorZ = coordinates.getCoorZ();
    }

    /**
     * Getter of the command
     * @return : the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Getter of the response
     * @return : the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Getter of the id
     * @return : the id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter of the x coordinate
     * @return : the x coordinate
     */
    public float getCoorX() {
        return coorX;
    }

    /**
     * Getter of the Y coordinate
     * @return : the Y coordinate
     */
    public float getCoorY() {
        return coorY;
    }

    /**
     * Getter of the Z coordinate
     * @return : the Z coordinate
     */
    public float getCoorZ() {
        return coorZ;
    }

    /**
     * Getter of the config id
     * @return : the config id
     */
    public String getCfgId() {
        return cfgId;
    }

    /**
     * Getter of the download rate
     * @return : the download rate
     */
    public String getDownloadRate() {
        return downloadRate;
    }

    /**
     * Getter of the location engine
     * @return : the location engine
     */
    public String getLocEngine() {
        return locEngine;
    }

    /**
     * Getter of the node config
     * @return : the node mask
     */
    public String getNodeMask() {
        return nodeMask;
    }

    /**
     * Getter of the node options
     * @return : the node options
     */
    public String getNodeOptions() {
        return nodeOptions;
    }

    /**
     * Getter of the position filter
     * @return : the position filter
     */
    public String getPosFilter() {
        return posFilter;
    }

    /**
     * Getter of the expiration seconds
     * @return : the expiration second
     */
    public String getSecExpiration() {
        return secExpiration;
    }

    /**
     * Getter of the anchor
     * @return : the anchor
     */
    public String getAnchor() {
        return anchor;
    }

    /**
     * Getter of the distance
     * @return : the distance
     */
    public String getDist() {
        return dist;
    }

    /**
     * Getter of the TQF
     * @return : the tqf
     */
    public String getTqf() {
        return tqf;
    }

    /**
     * Getter of the RSSI
     * @return : the RSSI
     */
    public String getRssi() {
        return rssi;
    }

    /**
     * Getter of the measures
     * @return : the measures
     */
    public Measure[] getMeasures() {
        return measures;
    }

    /**
     * Getter of the tags
     * @return : the tags
     */
    public Tag[] getTags() {
        return tags;
    }

    /**
     * Getter of the anchors
     * @return : the anchors
     */
    public Anchor[] getAnchors() {
        return anchors;
    }

    /**
     * Setter of the id (for tag or anchor)
     * @param id : the new ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Setter of the Anchor
     * @param anchor : the anchor
     */
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
}