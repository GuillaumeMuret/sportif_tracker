package com.neos.trackandroll.cervo.controller.distribution.anchor;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;

public class ActionAskListTagAndAnchors implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action ask list tag and anchors
     * @param brain : the brain of the system
     */
    public ActionAskListTagAndAnchors(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method call for the specific action
     * @param params
     */
    @Override
    public void execute(String[] params) {
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_SCAN_INFRA));
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_LIST_TAGS));
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_LIST_ANCHORS));
    }
}
