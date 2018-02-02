package com.neos.trackandroll.cervo.communication.maestro.distribution.in;

import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessStopSaving implements ICommandFromMaestro {

    /**
     * Process called by the distributor to convert the process stop saving and next save the data
     * @param maestroParams : the maestro params
     * @return : the action list for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(Params maestroParams) {
        LogUtils.d("ProcessStopSaving");
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_STOP_SAVING_AND_SEND_DATA));
        return actions;
    }
}
