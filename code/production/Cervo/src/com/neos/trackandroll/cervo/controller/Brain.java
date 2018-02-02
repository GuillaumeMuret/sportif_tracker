package com.neos.trackandroll.cervo.controller;

import com.neos.trackandroll.cervo.communication.ICommunicationState;
import com.neos.trackandroll.cervo.communication.anchor.tcp.TcpAnchorCommunication;
import com.neos.trackandroll.cervo.communication.anchor.udp.UdpAnchorCommunication;
import com.neos.trackandroll.cervo.communication.armband.ArmbandCommunication;
import com.neos.trackandroll.cervo.communication.maestro.MaestroCommunication;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.controller.distribution.anchor.ActionAskListTagAndAnchors;
import com.neos.trackandroll.cervo.controller.distribution.armband.ActionManagePythonDisconnected;
import com.neos.trackandroll.cervo.controller.distribution.armband.ActionRelaunchWatchdogForArmband;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionDeleteSaving;
import com.neos.trackandroll.cervo.controller.distribution.anchor.ActionFindTagPosition;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionLaunchTimerCheckMaestroConnection;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendAllSensorsForMaestro;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendAnchorDisconnected;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendArmbandConnected;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendArmbandDisconnected;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendTagConnected;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionSendTagDisconnected;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionStartSaving;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionStopSavingAndSendData;
import com.neos.trackandroll.cervo.controller.distribution.anchor.ActionInitAnchorsAndPosition;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.controller.distribution.anchor.ActionRelaunchWatchdogForTag;
import com.neos.trackandroll.cervo.controller.distribution.maestro.ActionStopTimerCheckMaestroConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public final class Brain implements ICommunicationState {

    /**
     * The tcpAnchorCommunication of the application
     */
    private TcpAnchorCommunication tcpAnchorCommunication;

    /**
     * The udpAnchorCommunication of the application
     */
    private UdpAnchorCommunication udpAnchorCommunication;

    /**
     * The boolean to know if the system has to save data
     */
    private boolean savingData;

    /**
     * The Bluetooth communication of the application
     */
    private ArmbandCommunication armbandCommunication;

    /**
     * The Maestro communication of the application
     */
    private MaestroCommunication maestroCommunication;

    /**
     * The watchdog for tags
     */
    private HashMap<String, Timer> watchdogsTag;

    /**
     * The watchdog for armband
     */
    private HashMap<String, Timer> watchdogArmband;

    /**
     * Timer for the maestro connection
     */
    private Timer timerMaestroConnection;

    /**
     * Actions for brain pattern command
     */
    private HashMap<String, AbstractActionForBrain> actionsForBrain;

    /**
     * Singleton management
     */
    private static Brain instance;

    /**
     * Main Constructor for the Brain
     */
    private Brain() {
        this.watchdogsTag = new HashMap<>();
        this.watchdogArmband = new HashMap<>();

        this.actionsForBrain = new HashMap<>();
        this.actionsForBrain.put(ActionForBrain.ACTION_ASK_TAGS_AND_ANCHORS,
                new ActionAskListTagAndAnchors(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_INIT_ANCHORS_AND_POSITIONS,
                new ActionInitAnchorsAndPosition(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_RELAUNCH_WATCHDOG_FOR_TAG,
                new ActionRelaunchWatchdogForTag(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_TAG_CONNECTED,
                new ActionSendTagConnected(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_TAG_DISCONNECTED,
                new ActionSendTagDisconnected(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_ANCHOR_CONNECTION_LOST,
                new ActionSendAnchorDisconnected(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_ARMBAND_CONNECTED,
                new ActionSendArmbandConnected(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_ARMBAND_DISCONNECTED,
                new ActionSendArmbandDisconnected(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_SEND_ALL_SENSORS_FOR_MAESTRO,
                new ActionSendAllSensorsForMaestro(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_LAUNCH_TIMER_CHECK_MAESTRO_CONNECTION,
                new ActionLaunchTimerCheckMaestroConnection(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_START_SAVING,
                new ActionStartSaving(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_STOP_SAVING_AND_SEND_DATA,
                new ActionStopSavingAndSendData(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_DELETE_SAVING,
                new ActionDeleteSaving(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_STOP_TIMER_CHECK_MAESTRO_CONNECTION,
                new ActionStopTimerCheckMaestroConnection(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_FIND_TAG_POSITION,
                new ActionFindTagPosition(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_RELAUNCH_WATCHDOG_FOR_ARMBAND,
                new ActionRelaunchWatchdogForArmband(this));
        this.actionsForBrain.put(ActionForBrain.ACTION_MANAGE_PYTHON_DISCONNECTED,
                new ActionManagePythonDisconnected(this));

        this.tcpAnchorCommunication = TcpAnchorCommunication.getInstance(this, this);
        this.udpAnchorCommunication = UdpAnchorCommunication.getInstance(this, this);
        this.armbandCommunication = ArmbandCommunication.getInstance(this, this);
        this.maestroCommunication = MaestroCommunication.getInstance(this, this);
    }

    /**
     * Getter of the instance Brain
     *
     * @return the instance Brain
     */
    public static Brain getInstance() {
        if (instance == null) {
            instance = new Brain();
        }
        return instance;
    }

    /**
     * Main process of the communication. Letter box to manage the different message received
     *
     * @param action : an action to do
     */
    public synchronized void manageActions(ArrayList<ActionForBrain> action) {
        for (int i = 0; i < action.size(); i++) {
            this.actionsForBrain.get(action.get(i).getActionId()).execute(action.get(i).getParams());
        }
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    //                                    //
    // PROCESS OF THE COMMUNICATION STATE //
    //                                    //
    ////////////////////////////////////////
    ////////////////////////////////////////

    @Override
    public void onTcpAnchorConnected() {
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_INIT_ANCHORS_AND_POSITIONS));
        manageActions(actions);
    }

    @Override
    public void onTcpAnchorDisconnection() {
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_SEND_ANCHOR_CONNECTION_LOST));
        manageActions(actions);
        this.tcpAnchorCommunication.relaunchCommunication();
    }

    @Override
    public void onTcpAnchorFailed() {
        this.tcpAnchorCommunication.relaunchCommunication();
    }

    @Override
    public void onTcpMaestroConnected() {
        ArrayList<ActionForBrain> actions = new ArrayList<>();
        actions.add(new ActionForBrain(ActionForBrain.ACTION_LAUNCH_TIMER_CHECK_MAESTRO_CONNECTION));
        manageActions(actions);
    }

    @Override
    public void onTcpMaestroDisconnection() {

    }

    /////////////////////////
    /////////////////////////
    //                     //
    // GETTERS AND SETTERS //
    //                     //
    /////////////////////////
    /////////////////////////

    /**
     * Getter of the TCP anchor communication instance
     * @return : the TCP anchor communication
     */
    public TcpAnchorCommunication getTcpAnchorCommunication() {
        return tcpAnchorCommunication;
    }

    /**
     * Getter of the armband communication
     * @return : the armband instance communication
     */
    public ArmbandCommunication getArmbandCommunication() {
        return armbandCommunication;
    }

    /**
     * Getter of the maestro communication
     * @return : the maestro communication instance
     */
    public MaestroCommunication getMaestroCommunication() {
        return maestroCommunication;
    }

    /**
     * Getter of the watchdogs map for the tag
     * @return : the watchdog map for the map
     */
    public HashMap<String, Timer> getWatchdogsTag() {
        return watchdogsTag;
    }

    /**
     * Getter of the watchdog map for the armband
     * @return : the armband watchdog map
     */
    public HashMap<String, Timer> getWatchdogArmband() {
        return watchdogArmband;
    }

    /**
     * Getter of the maestro communication timer
     * @return : the timer of maestro connection
     */
    public Timer getTimerMaestroConnection() {
        return timerMaestroConnection;
    }

    /**
     * Setter of the maestro timer connection
     * @param timerMaestroConnection : the timer maestro connection
     */
    public void setTimerMaestroConnection(Timer timerMaestroConnection) {
        this.timerMaestroConnection = timerMaestroConnection;
    }

    /**
     * Getter of the boolean to know if the system is saving data
     * @return : is the system is saving data
     */
    public boolean isSavingData() {
        return savingData;
    }

    /**
     * Setter of the saving data boolean
     * @param savingData : the saving data boolean
     */
    public void setSavingData(boolean savingData) {
        this.savingData = savingData;
    }

}