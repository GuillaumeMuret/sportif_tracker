package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionStopTimerCheckMaestroConnection extends AbstractActionForBrain {

    /**
     * Main constructor of the action for the brain to stop the timer for check maestro connection
     *
     * @param brain : the brain of the system
     */
    public ActionStopTimerCheckMaestroConnection(Brain brain) {
        super(brain);
    }

    /**
     * Process called to execute the action created above
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionStopTimerCheckMaestroConnection");
        if (brain.getTimerMaestroConnection() != null) {
            brain.getTimerMaestroConnection().cancel();
        }
    }
}
