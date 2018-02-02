package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionDeleteSaving extends AbstractActionForBrain {

    /**
     * Main constructor of the action delete saving
     * @param brain : the brain of the system
     */
    public ActionDeleteSaving(Brain brain) {
        super(brain);
    }

    /**
     * The method when the brain have to stop saving data and delete the session in progress
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionDeleteSaving");
        this.brain.setSavingData(false);
        DataStore.getInstance().removeAllData();
    }
}
