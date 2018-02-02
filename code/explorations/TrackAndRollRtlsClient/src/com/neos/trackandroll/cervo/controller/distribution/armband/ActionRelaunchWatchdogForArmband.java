package com.neos.trackandroll.cervo.controller.distribution.armband;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.model.Constant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActionRelaunchWatchdogForArmband implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action for relaunch the watchdog for the armband
     * @param brain : the brain of the system
     */
    public ActionRelaunchWatchdogForArmband(Brain brain) {
        this.brain = brain;
    }

    /**
     * Process called for relaunching the watchdog for the armband
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        if (!brain.getWatchdogArmband().containsKey(params[0])) {
            brain.getWatchdogArmband().put(params[0], new Timer());
        } else {
            brain.getWatchdogArmband().get(params[0]).cancel();
            brain.getWatchdogArmband().put(params[0], new Timer());
        }

        brain.getWatchdogArmband().get(params[0]).schedule(
                new TimerWatchdogArmband(params[0]), Constant.TIME_MAX_FOR_ARMBAND_BPM);
    }

    /**
     * Nested class of the timer task when the time is over
     */
    private class TimerWatchdogArmband extends TimerTask {

        /**
         * The sensor id of the armband
         */
        private String armbandId;

        /**
         * The timer watchdog for the armband
         * @param armbandId : the armband id
         */
        public TimerWatchdogArmband(String armbandId) {
            this.armbandId = armbandId;
        }

        /**
         * Process called when the time of the timer is over
         */
        @Override
        public void run() {
            ArrayList<ActionForBrain> actionForBrains = new ArrayList<>();
            actionForBrains.add(new ActionForBrain(ActionForBrain.ACTION_SEND_ARMBAND_DISCONNECTED, armbandId));
            brain.manageActions(actionForBrains);
        }
    }
}
