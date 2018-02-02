package com.neos.trackandroll.cervo.communication.anchor.protocol;

public class AnchorProcessOut {

    /**
     * The different process called to the client
     * Cervo to Anchor
     */
    public static final String PROCESS_SCAN_INFRA = "scanInfra";
    public static final String PROCESS_LIST_TAGS = "listTags";
    public static final String PROCESS_LIST_ANCHORS = "listAnchors";
    public static final String PROCESS_SET_POS = "setPos";
    public static final String PROCESS_GET_DETAILS = "getDetails";
    public static final String PROCESS_SET_NODE_CONFIG = "setNodeConfig";
}