package com.neos.tcp.server.communication;

import com.neos.tcp.server.communication.distribution.*;
import com.neos.tcp.server.communication.protocole.ProcessIn;
import com.neos.tcp.server.controller.Brain;
import com.neos.tcp.server.utils.LogUtils;

import java.util.HashMap;

public final class Distributor {

    /**
     * The process that could be called on the raspberry
     */
    private HashMap<String, ICommandFromClient> commands;

    /**
     * The brain that manage all managers
     */
    private Brain brain;
    
    /**
     * Singleton management
     */
    private static Distributor instance;
    
    /**
     * Main constructor -> create the different commands
     * @param brain : the brain
     */
    private Distributor(Brain brain) {
        this.brain = brain;

        // Create the object commands which will have all the process
        this.commands = new HashMap<>();

        // the different command
        this.commands.put(ProcessIn.ASK_SENSORS, new AskSensors());
        this.commands.put(ProcessIn.START_SAVING, new StartSaving());
        this.commands.put(ProcessIn.STOP_SAVING, new StopSaving());
        this.commands.put(ProcessIn.DELETE_SAVING, new DeleteSaving());
    }
    
    /**
     * Getter of the instance Object Manager
     * @param brain : the brain
     * @return the instance Object Manager
     */
    public static Distributor getInstance (Brain brain) {
        if (instance == null) {
            instance = new Distributor(brain);
        }
        return instance;
    }

    /**
     * Main function that dispatch the process called
     *
     * @param idCommand : the id of the command called
     * @param params    : the params of the command
     */
    public void dispatch(String idCommand, String params) {
        LogUtils.debug("dispatch => " + idCommand);
        if (commands.containsKey(idCommand)) {
            commands.get(idCommand).convertMessageAndCallManager(params, brain);
        } else {
            LogUtils.debug("NO_COMMAND_FOUND");
        }
    }
}
