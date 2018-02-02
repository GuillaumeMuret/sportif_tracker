package com.neos.trackandroll.cervo.controller.distribution.anchor;

import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.constant.Constant;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.Tag;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActionRelaunchWatchdogForTag extends AbstractActionForBrain {

    /**
     * Main constructor of the action for brain for relaunching the watchdog for tag
     * @param brain : the brain of the system
     */
    public ActionRelaunchWatchdogForTag(Brain brain) {
        super(brain);
    }

    /**
     * Process called to execute the system given in parameter
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        scheduleWatchdogForTag(params[0]);
    }

    /**
     * Process called to schedule the watchdog for the tag
     * @param tagId : the tag id
     */
    private void scheduleWatchdogForTag(String tagId) {
        if (!brain.getWatchdogsTag().containsKey(tagId)) {
            brain.getWatchdogsTag().put(tagId, new Timer());
        } else {
            brain.getWatchdogsTag().get(tagId).cancel();
            brain.getWatchdogsTag().put(tagId, new Timer());
        }
        brain.getWatchdogsTag().get(tagId).schedule(new TimerWatchdogTag(tagId), Constant.TIME_MAX_FOR_TAG_POSITION);
    }

    /**
     * Nested class of the timer action when time is over
     */
    private class TimerWatchdogTag extends TimerTask {

        /**
         * The tag id
         */
        private String tagId;

        /**
         * Main constructor of the timer action
         * @param tagId : the tag id
         */
        public TimerWatchdogTag(String tagId) {
            this.tagId = tagId;
        }

        /**
         * Method called when the timer action start
         */
        @Override
        public void run() {

            DataStore.getInstance().getTagMap().get(tagId).setState(Tag.TagState.DISCONNECTED);

            brain.getWatchdogsTag().get(tagId).cancel();
            brain.getWatchdogsTag().remove(tagId);

            ArrayList<ActionForBrain> actionForBrains = new ArrayList<>();
            actionForBrains.add(new ActionForBrain(ActionForBrain.ACTION_SEND_TAG_DISCONNECTED, tagId));
            brain.manageActions(actionForBrains);
        }
    }
}
