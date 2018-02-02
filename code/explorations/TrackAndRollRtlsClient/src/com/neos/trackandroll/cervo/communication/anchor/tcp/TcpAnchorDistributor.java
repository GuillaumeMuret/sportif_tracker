package com.neos.trackandroll.cervo.communication.anchor.tcp;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ICommandTcpAnchorIn;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ProcessResponseGetDetails;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ProcessResponseListAnchors;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ProcessResponseListTags;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProcessIn;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ProcessResponseScanInfra;
import com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in.ProcessResponseSetPos;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;
import java.util.HashMap;

public final class TcpAnchorDistributor {

    /**
     * The process that could be called on the raspberry
     */
    private HashMap<String, ICommandTcpAnchorIn> commands;
    /**
     * Singleton management
     */
    private static TcpAnchorDistributor instance;

    /**
     * Main constructor -> create the different commands
     */
    private TcpAnchorDistributor() {
        // Create the object commands which will have all the process
        this.commands = new HashMap<>();

        // the different command
        this.commands.put(AnchorProcessIn.PROCESS_RESPONSE_LIST_TAGS, new ProcessResponseListTags());
        this.commands.put(AnchorProcessIn.PROCESS_RESPONSE_LIST_ANCHOR, new ProcessResponseListAnchors());
        this.commands.put(AnchorProcessIn.PROCESS_RESPONSE_SET_POS, new ProcessResponseSetPos());
        this.commands.put(AnchorProcessIn.PROCESS_RESPONSE_SCAN_INFRA, new ProcessResponseScanInfra());
        this.commands.put(AnchorProcessIn.PROCESS_RESPONSE_GET_DETAILS, new ProcessResponseGetDetails());
    }

    /**
     * Getter of the instance Object Manager
     * @return the instance Object Manager
     */
    public static TcpAnchorDistributor getInstance () {
        if (instance == null) {
            instance = new TcpAnchorDistributor();
        }
        return instance;
    }


    public ArrayList<ActionForBrain> dispatch (AnchorMessage anchorMessage) {
        return commands.get(anchorMessage.getResponse()).convertAndGetAction(anchorMessage);
    }
}
