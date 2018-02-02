package com.neos.trackandroll.cervo.controller.distribution;

import com.neos.trackandroll.cervo.controller.Brain;

public abstract class AbstractActionForBrain implements IActionForBrain {

    /**
     * The brain of the system
     */
    protected Brain brain;

    /**
     * Main constructor of the action for the brain
     *
     * @param brain : the brain of the system
     */
    public AbstractActionForBrain(Brain brain) {
        this.brain = brain;
    }

}
