package com.neos.trackandroll.cervo.controller.distribution.armband;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;

public class ActionManagePythonDisconnected extends AbstractActionForBrain {

    /**
     * Main constructor of the action for manage the python socket disconnection
     * @param brain : the brain of the system
     */
    public ActionManagePythonDisconnected(Brain brain) {
        super(brain);
    }

    /**
     * Process called for managing the python socket disconnection
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        brain.getArmbandCommunication().relaunchCommunication(brain, brain);
    }
}
