package com.neos.trackandroll.cervo.communication.anchor.udp;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.udp.distribution.in.ICommandUdpAnchorIn;
import com.neos.trackandroll.cervo.communication.anchor.udp.distribution.in.ProcessTwrJsonMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;

public final class UdpDistributor {

    /**
     * The process that could be called on the raspberry
     */
    private ICommandUdpAnchorIn command;
    /**
     * Singleton management
     */
    private static UdpDistributor instance;

    /**
     * Main constructor -> create the different commands
     */
    private UdpDistributor() {
        // the different command
        this.command = new ProcessTwrJsonMessage();
    }

    /**
     * Getter of the instance Object Manager
     * @return the instance Object Manager
     */
    public static UdpDistributor getInstance () {
        if (instance == null) {
            instance = new UdpDistributor();
        }
        return instance;
    }


    public ArrayList<ActionForBrain> dispatch (AnchorMessage anchorMessage) {
        return command.convertAndGetActions(anchorMessage);
    }
}
