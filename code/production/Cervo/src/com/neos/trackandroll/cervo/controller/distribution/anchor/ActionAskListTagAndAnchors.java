package com.neos.trackandroll.cervo.controller.distribution.anchor;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;

public class ActionAskListTagAndAnchors extends AbstractActionForBrain {

    /**
     * Main constructor of the action to ask the tag list
     *
     * @param brain : the brain of the system
     */
    public ActionAskListTagAndAnchors(Brain brain) {
        super(brain);
    }

    /**
     * Method call for the specific action
     * @param params : the different params of the process
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
