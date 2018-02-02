package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.data.Anchor;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseSetPos implements ICommandTcpAnchorIn {

    /**
     * Process called by the distributor to convert the process set pos
     *
     * @param anchorResponseMessage : the anchor response message
     * @return : the action for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response setPos");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        String anchorId = anchorResponseMessage.getId();
        if (!DataStore.getInstance().getAnchorMap().keySet().contains(anchorId)) {
            DataStore.getInstance().getAnchorMap().put(anchorId, new Anchor(anchorId));
        }
        return actions;
    }
}
