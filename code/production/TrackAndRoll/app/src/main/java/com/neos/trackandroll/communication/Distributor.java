package com.neos.trackandroll.communication;

import com.neos.trackandroll.communication.distribution.in.ICommandIn;
import com.neos.trackandroll.communication.distribution.in.ProcessCheckConnection;
import com.neos.trackandroll.communication.distribution.in.ProcessInformSensors;
import com.neos.trackandroll.communication.distribution.in.ProcessNotifyConnection;
import com.neos.trackandroll.communication.distribution.in.ProcessNotifyDisconnection;
import com.neos.trackandroll.communication.distribution.in.ProcessNotifyEndSession;
import com.neos.trackandroll.communication.distribution.in.ProcessNotifyStartSession;
import com.neos.trackandroll.communication.distribution.in.ProcessSaveSession;
import com.neos.trackandroll.communication.message.Message;
import com.neos.trackandroll.communication.protocole.ProcessIn;

import java.util.HashMap;

public final class Distributor {

    /**
     * The instance of the Distributor
     */
    private static Distributor instance;


    /**
     * Getter of the instance Distributor
     * @return the Distributor instance
     */
    public static Distributor getInstance() {
        if (instance == null){
            instance = new Distributor();
        }
        return instance;
    }


    /**
     * The process that could be called on the raspberry
     */
    private HashMap<String, ICommandIn> commands;

    /**
     * Main constructor -> create the different commands
     */
    private Distributor() {
        // Create the object commands which will have all the process
        this.commands = new HashMap<>();

        // the different command
        this.commands.put(ProcessIn.PROCESS_NOTIFY_START_SESSION, new ProcessNotifyStartSession());
        this.commands.put(ProcessIn.PROCESS_NOTIFY_END_SESSION, new ProcessNotifyEndSession());
        this.commands.put(ProcessIn.PROCESS_NOTIFY_CONNECTION, new ProcessNotifyConnection());
        this.commands.put(ProcessIn.PROCESS_NOTIFY_DISCONNECTION, new ProcessNotifyDisconnection());
        this.commands.put(ProcessIn.PROCESS_INFORM_SENSORS, new ProcessInformSensors());
        this.commands.put(ProcessIn.PROCESS_SAVE_SESSION, new ProcessSaveSession());
        this.commands.put(ProcessIn.PROCESS_CHECK_CONNECTION, new ProcessCheckConnection());

    }

    public String dispatch (Message message) {
        return commands.get(message.getProcess()).convertAndGetAction(message.getParams());
    }
}
