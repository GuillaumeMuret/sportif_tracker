package com.neos.trackandroll.cervo.controller.distribution.anchor;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.message.PreLoadedMessage;
import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;

public class ActionInitAnchorsAndPosition implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action to init the anchor and set the anchor position
     * @param brain : the brain of the system
     */
    public ActionInitAnchorsAndPosition(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method call to init the anchors and their position
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {

        /*
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(
                        AnchorProcessOut.PROCESS_SET_NODE_CONFIG,
                        "0",
                        "tdoa",
                        "0x TODO",
                        "TODO",
                        "TODO",
                        "TODO",
                        "TODO",
                        "TODO",
                        "TODO"
                )
        );
        */

        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_SCAN_INFRA));
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(PreLoadedMessage.SET_POS_0X00B8);
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(PreLoadedMessage.SET_POS_0X00B9);
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(PreLoadedMessage.SET_POS_0X00C4);
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(PreLoadedMessage.SET_POS_0X00CA);
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_LIST_TAGS));
        brain.getTcpAnchorCommunication().getTcpAnchorProxy().sendMessage(
                new AnchorMessage(AnchorProcessOut.PROCESS_LIST_ANCHORS));
    }
}
