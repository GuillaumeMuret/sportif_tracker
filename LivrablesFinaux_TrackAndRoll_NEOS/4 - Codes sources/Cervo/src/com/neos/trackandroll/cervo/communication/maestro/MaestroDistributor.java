package com.neos.trackandroll.cervo.communication.maestro;

import com.neos.trackandroll.cervo.communication.maestro.distribution.in.ICommandFromMaestro;
import com.neos.trackandroll.cervo.communication.maestro.distribution.in.ProcessAskSensors;
import com.neos.trackandroll.cervo.communication.maestro.distribution.in.ProcessDeleteSaving;
import com.neos.trackandroll.cervo.communication.maestro.distribution.in.ProcessStartSaving;
import com.neos.trackandroll.cervo.communication.maestro.distribution.in.ProcessStopSaving;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessIn;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;
import java.util.HashMap;

public final class MaestroDistributor {

    /**
     * The process that could be called
     */
    private HashMap<String, ICommandFromMaestro> commands;

    /**
     * The brain that manage all managers
     */
    private Brain brain;
    
    /**
     * Singleton management
     */
    private static MaestroDistributor instance;
    
    /**
     * Main constructor -> create the different commands
     * @param brain : the brain
     */
    private MaestroDistributor(Brain brain) {
        this.brain = brain;

        // Create the object commands which will have all the process
        this.commands = new HashMap<>();

        // the different command
        this.commands.put(MaestroProcessIn.PROCESS_ASK_SENSORS, new ProcessAskSensors());
        this.commands.put(MaestroProcessIn.PROCESS_START_SAVING, new ProcessStartSaving());
        this.commands.put(MaestroProcessIn.PROCESS_STOP_SAVING, new ProcessStopSaving());
        this.commands.put(MaestroProcessIn.PROCESS_DELETE_SAVING, new ProcessDeleteSaving());
    }
    
    /**
     * Getter of the instance of maestro distributor
     * @param brain : the brain
     * @return the instance
     */
    public static MaestroDistributor getInstance (Brain brain) {
        if (instance == null) {
            instance = new MaestroDistributor(brain);
        }
        return instance;
    }

    /**
     * Main function that dispatch the process called
     * @param maestroMessage : the message to convert
     * @return : the list of the different action for the brain
     */
    public ArrayList<ActionForBrain> dispatch(MaestroMessage maestroMessage) {
        return commands.get(maestroMessage.getProcess()).convertAndGetAction(maestroMessage.getParams());
    }
}
