package com.neos.trackandroll.cervo.communication.armband.distribution.in;

import com.neos.trackandroll.cervo.communication.armband.message.ArmbandMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;

public interface ICommandArmbandIn {

    /**
     * The main process : decode the armbandMessage and return the process for the brain
     * @param armbandMessage : the armbandMessage of the process
     * @return : the corresponding action
     */
    ArrayList<ActionForBrain> convertAndGetAction(ArmbandMessage armbandMessage);
}
