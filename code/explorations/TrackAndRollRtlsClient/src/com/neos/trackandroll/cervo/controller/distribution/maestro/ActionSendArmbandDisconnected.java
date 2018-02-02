package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;

public class ActionSendArmbandDisconnected implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action to send when the armband is disconnected
     * @param brain : the brain of the system
     */
    public ActionSendArmbandDisconnected(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method called to execute the action for brain
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        // TODO SEND ARMBAND DISCONNECTED TO MAESTRO
    }
}
