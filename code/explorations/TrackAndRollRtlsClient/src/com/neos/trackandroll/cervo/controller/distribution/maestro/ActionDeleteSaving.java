package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionDeleteSaving implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action delete saving
     * @param brain : the brain of the system
     */
    public ActionDeleteSaving(Brain brain) {
        this.brain = brain;
    }

    /**
     * The method when the brain have to stop saving data and delete the session in progress
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionDeleteSaving");
        this.brain.setSavingData(false);
        // TODO need to remove all data
    }
}
