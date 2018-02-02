package com.neos.tcp.server.communication.distribution;

import com.neos.tcp.server.controller.Brain;

public class SubscribePirate implements ICommandFromClient {

    /**
     * Process called to convert params and call manager
     * @param params : the params to decode
     * @param brain : the brain
     * @param port  the port of player socket
     */
    @Override
    public void convertMessageAndCallManager(String params, Brain brain, int port) {
        // TODO action brain.getCharacterManager().subscribeNewPirate(port);
    }
}
