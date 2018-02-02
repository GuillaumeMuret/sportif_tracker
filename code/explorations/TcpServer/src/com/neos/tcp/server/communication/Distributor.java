package com.neos.tcp.server.communication;

import com.neos.tcp.server.communication.distribution.ICommandFromClient;
import com.neos.tcp.server.communication.distribution.SubscribePirate;
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
        this.commands.put(ProcessIn.SUBSCRIBE_PIRATE, new SubscribePirate());
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
     * @param port : the port of the socket where the message arrived
     */
    public void dispatch(String idCommand, String params, int port) {
        LogUtils.debug("dispatch => " + idCommand + " on port " + port);
        if (commands.containsKey(idCommand)) {
            commands.get(idCommand).convertMessageAndCallManager(params, brain, port);
        } else {
            LogUtils.debug("NO_COMMAND_FOUND");
        }
    }
}
