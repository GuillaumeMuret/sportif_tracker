package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;


import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;

public interface ICommandTcpAnchorIn {

    /**
     * The main process : decode the anchorMessage and return the list of action for the brain
     * @param anchorMessage : the anchorMessage of the process
     * @return : the corresponding action
     */
    ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorMessage);
}
