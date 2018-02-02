package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.Anchor;
import com.neos.trackandroll.cervo.model.Coordinates;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.Tag;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseSetPos implements ICommandTcpAnchorIn {

    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response setPos");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        String anchorId = anchorResponseMessage.getId();
        if(!DataStore.getInstance().getAnchorMap().keySet().contains(anchorId)){
            DataStore.getInstance().getAnchorMap().put(anchorId, new Anchor(anchorId));
        }
        return actions;
    }
}
