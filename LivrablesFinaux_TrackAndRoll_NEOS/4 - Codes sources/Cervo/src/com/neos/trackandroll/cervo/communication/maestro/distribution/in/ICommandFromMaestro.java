package com.neos.trackandroll.cervo.communication.maestro.distribution.in;

import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;

import java.util.ArrayList;

/**
 * Use to convert message string into model data and call action concerned
 */
public interface ICommandFromMaestro {

    /**
     * Process called to convert the message received from the android (maestro)
     * @param maestroParams : the maestro params
     * @return : the maestro params
     */
    ArrayList<ActionForBrain> convertAndGetAction(Params maestroParams);
}
