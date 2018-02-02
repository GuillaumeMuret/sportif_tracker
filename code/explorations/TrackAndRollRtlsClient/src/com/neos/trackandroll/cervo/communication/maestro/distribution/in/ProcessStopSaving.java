package com.neos.trackandroll.cervo.communication.maestro.distribution.in;

import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessStopSaving implements ICommandFromMaestro {


    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(Params maestroParams) {
        LogUtils.d("ProcessStopSaving");
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_STOP_SAVING_AND_SEND_DATA));
        return actions;
    }
}
