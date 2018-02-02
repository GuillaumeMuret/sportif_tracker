package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseGetDetails implements ICommandTcpAnchorIn {

    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response getDetails");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        return actions;
    }
}
