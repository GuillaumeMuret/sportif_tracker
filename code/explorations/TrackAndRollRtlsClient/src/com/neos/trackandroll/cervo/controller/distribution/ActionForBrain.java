package com.neos.trackandroll.cervo.controller.distribution;

public class ActionForBrain {

    public static final String ACTION_INIT_ANCHORS_AND_POSITIONS = "ACTION_INIT_ANCHORS_AND_POSITIONS";
    public static final String ACTION_ASK_TAGS_AND_ANCHORS = "ACTION_ASK_TAGS_AND_ANCHORS";
    public static final String ACTION_RELAUNCH_WATCHDOG_FOR_TAG = "ACTION_RELAUNCH_WATCHDOG_FOR_TAG";
    public static final String ACTION_RELAUNCH_WATCHDOG_FOR_ARMBAND = "ACTION_RELAUNCH_WATCHDOG_FOR_ARMBAND";
    public static final String ACTION_SEND_TAG_CONNECTED = "ACTION_SEND_TAG_CONNECTED";
    public static final String ACTION_SEND_TAG_DISCONNECTED = "ACTION_SEND_TAG_DISCONNECTED";
    public static final String ACTION_SEND_ANCHOR_CONNECTION_LOST = "ACTION_SEND_ANCHOR_CONNECTION_LOST";
    public static final String ACTION_SEND_ARMBAND_CONNECTED = "ACTION_SEND_ARMBAND_CONNECTED";
    public static final String ACTION_SEND_ARMBAND_DISCONNECTED = "ACTION_SEND_ARMBAND_DISCONNECTED";
    public static final String ACTION_MANAGE_PYTHON_DISCONNECTED = "ACTION_MANAGE_PYTHON_DISCONNECTED";
    public static final String ACTION_SEND_ALL_TAGS_FOR_MAESTRO = "ACTION_SEND_ALL_TAGS_FOR_MAESTRO";
    public static final String ACTION_LAUNCH_TIMER_CHECK_MAESTRO_CONNECTION = "ACTION_LAUNCH_TIMER_CHECK_MAESTRO_CONNECTION";
    public static final String ACTION_START_SAVING = "ACTION_START_SAVING";
    public static final String ACTION_STOP_SAVING_AND_SEND_DATA = "ACTION_STOP_SAVING_AND_SEND_DATA";
    public static final String ACTION_DELETE_SAVING = "ACTION_DELETE_SAVING";
    public static final String ACTION_STOP_TIMER_CHECK_MAESTRO_CONNECTION = "ACTION_STOP_TIMER_CHECK_MAESTRO_CONNECTION";
    public static final String ACTION_FIND_TAG_POSITION = "ACTION_FIND_TAG_POSITION";

    /**
     * The action ID of the action for the brain
     */
    private String actionId;

    /**
     * The different parameters of the action for the brain
     */
    private String[] params;

    /**
     * Main constructor of the action for the brain with parameters and the action id
     * @param actionId : the action id
     * @param params : the parameters to give for the brain
     */
    public ActionForBrain(String actionId, String... params) {
        this.actionId = actionId;
        this.params = params;
    }

    /**
     * Getter of the action id
     * @return : the action id
     */
    public String getActionId() {
        return actionId;
    }

    /**
     * Getter of the parameters of the action
     * @return : the parameters of the action
     */
    public String[] getParams() {
        return params;
    }
}
