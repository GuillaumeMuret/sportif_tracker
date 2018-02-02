package com.neos.trackandroll.cervo.communication.anchor.udp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;

public interface ICommandUdpAnchorIn {

    /**
     * The main process : decode the message and return the list of action for the brain
     * @param message : the message of the process
     * @return : the corresponding action
     */
    ArrayList<ActionForBrain> convertAndGetActions(AnchorMessage message);
}
