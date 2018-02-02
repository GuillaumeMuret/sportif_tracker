package com.neos.tcp.server.communication.distribution;

import com.neos.tcp.server.communication.message.Params;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.proxy.encoder.NotifyEndSession;
import com.neos.tcp.server.controller.Brain;

public class StopSaving implements ICommandFromClient {

    /**
     * Process called to convert params and call manager
     * @param params : the params to decode
     * @param brain : the brain
     */
    @Override
    public void convertMessageAndCallManager(String params, Brain brain) {
        // TODO action brain.getCharacterManager().subscribeNewPirate(port);
        ((NotifyEndSession) brain.getCommunication().getProxyClient().getEncoders().get(ProcessOut.PROXY_CLIENT_NOTIFY_END_SESSION)).sendMessage();
    }
}
