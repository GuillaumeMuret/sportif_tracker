package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.Anchor;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseScanInfra implements ICommandTcpAnchorIn {

    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response scanInfra");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        return actions;
    }
}
