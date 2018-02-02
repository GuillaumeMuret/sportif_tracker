package com.neos.trackandroll.cervo.communication.armband;

import com.neos.trackandroll.cervo.communication.armband.distribution.in.ICommandArmbandIn;
import com.neos.trackandroll.cervo.communication.armband.distribution.in.ProcessSendBpm;
import com.neos.trackandroll.cervo.communication.armband.message.ArmbandMessage;
import com.neos.trackandroll.cervo.communication.armband.protocol.ArmbandProcessIn;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;
import java.util.HashMap;

public final class ArmbandDistributor {

    /**
     * The process that could be called
     */
    private HashMap<String, ICommandArmbandIn> commands;
    /**
     * Singleton management
     */
    private static ArmbandDistributor instance;

    /**
     * Main constructor -> create the different commands
     */
    private ArmbandDistributor() {
        // Create the object commands which will have all the process
        this.commands = new HashMap<>();

        // the different command
        this.commands.put(ArmbandProcessIn.PROCESS_SEND_BPM, new ProcessSendBpm());
    }

    /**
     * Getter of the instance armband distributor
     * @return the instance armband distributor
     */
    public static ArmbandDistributor getInstance () {
        if (instance == null) {
            instance = new ArmbandDistributor();
        }
        return instance;
    }

    /**
     * Process called to dispatch the message received to the converter
     * @param armbandMessage : the armband message received
     * @return : the list of action to execute for the brain
     */
    public ArrayList<ActionForBrain> dispatch (ArmbandMessage armbandMessage) {
        return commands.get(armbandMessage.getProcess()).convertAndGetAction(armbandMessage);
    }
}
