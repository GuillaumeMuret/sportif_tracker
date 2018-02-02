package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.data.Anchor;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseListAnchors implements ICommandTcpAnchorIn {

    /**
     * Process called by the distributor to convert the process list anchor
     * @param anchorResponseMessage : the anchor response message
     * @return : the action for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response List Anchors");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        for (int i = 0; i < anchorResponseMessage.getAnchors().length; i++) {
            String anchorId = anchorResponseMessage.getAnchors()[i].getId();
            if (!DataStore.getInstance().getAnchorMap().keySet().contains(anchorId)) {
                LogUtils.d("NEW ANCHOR => " + anchorId);
                anchorResponseMessage.getAnchors()[i].setState(Anchor.AnchorState.UNKNOWN);
                DataStore.getInstance().getAnchorMap().put(anchorId, anchorResponseMessage.getAnchors()[i]);
            }
        }
        return actions;
    }
}
