package com.neos.trackandroll.cervo.controller.distribution.armband;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;

public class ActionManagePythonDisconnected implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action for manage the python socket disconnection
     * @param brain : the brain of the system
     */
    public ActionManagePythonDisconnected(Brain brain) {
        this.brain = brain;
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
