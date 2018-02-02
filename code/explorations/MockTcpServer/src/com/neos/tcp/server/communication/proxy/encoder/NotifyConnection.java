package com.neos.tcp.server.communication.proxy.encoder;

import com.google.gson.Gson;
import com.neos.tcp.server.communication.message.Message;
import com.neos.tcp.server.communication.message.Params;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.proxy.ProxyClient;

public class NotifyConnection extends AbstractEncoder {

    /**
     * Main constructor of the proxy client
     * @param proxyClient : the proxy client
     */
    public NotifyConnection(ProxyClient proxyClient) {
        super(proxyClient);
    }

    /**
     * Process called to send the move accepted
     */
    public void sendMessage(int id, String type) {
        Params params = new Params();
        params.setCapteurId(id);
        params.setCapteurType(type);
        Message message = new Message(ProcessOut.PROXY_CLIENT_NOTIFY_CONNECTION.process, params);
        Gson gson = new Gson();
        this.proxyClient.sendBroadcast(gson.toJson(message));
    }
}
