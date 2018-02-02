package com.neos.trackandroll.cervo.communication.maestro.distribution.in;

import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessAskSensors implements ICommandFromMaestro {


    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(Params maestroParams) {
        LogUtils.d("ProcessAskSensors");
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_SEND_ALL_TAGS_FOR_MAESTRO));
        return actions;
    }
}
