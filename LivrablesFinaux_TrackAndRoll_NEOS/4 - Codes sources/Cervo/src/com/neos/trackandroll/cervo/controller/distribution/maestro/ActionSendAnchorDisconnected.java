package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionSendAnchorDisconnected extends AbstractActionForBrain {

    /**
     * Main constructor of the action for sending when an anchor is disconnected
     *
     * @param brain the brain of the system
     */
    public ActionSendAnchorDisconnected(Brain brain) {
        super(brain);
    }

    /**
     * Method called to execute the action created
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ANCHOR DISCONNECTED => " + params[0]);

        // TODO SEND CONNECTION WITH ANCHOR LOST TO MAESTRO
    }
}
