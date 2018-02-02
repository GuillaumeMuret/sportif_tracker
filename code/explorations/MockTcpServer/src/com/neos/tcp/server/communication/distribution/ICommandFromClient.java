package com.neos.tcp.server.communication.distribution;

import com.neos.tcp.server.controller.Brain;

/**
 * Use to convert message string into model data and call manager
 */
public interface ICommandFromClient {
    
    /**
     * Process called to convert the params received and call the manager
     * @param params : the params
     * @param brain : the brain (to call the managers)
     */
    void convertMessageAndCallManager(String params, Brain brain);
}
