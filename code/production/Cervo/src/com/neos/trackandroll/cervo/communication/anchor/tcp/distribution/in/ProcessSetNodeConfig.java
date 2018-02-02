package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessSetNodeConfig implements ICommandTcpAnchorIn {

    /**
     * Process called by the distributor to convert the process set node config
     * @param anchorResponseMessage : the anchor response message
     * @return : the action for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response setNodeConfig");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        return actions;
    }
}
