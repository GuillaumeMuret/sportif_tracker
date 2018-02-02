package com.neos.tcp.server.communication.proxy.encoder;

import com.google.gson.Gson;
import com.neos.tcp.server.communication.message.Capteur;
import com.neos.tcp.server.communication.message.Message;
import com.neos.tcp.server.communication.message.Params;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.protocole.ProtocolVocabulary;
import com.neos.tcp.server.communication.proxy.ProxyClient;

public class NotifyEndSession extends AbstractEncoder {

    /**
     * Main constructor of the proxy client
     * @param proxyClient : the proxy client
     */
    public NotifyEndSession(ProxyClient proxyClient) {
        super(proxyClient);
    }

    /**
     * Process called to send the move accepted
     */
    public void sendMessage() {
        Params params = new Params();
        Message message = new Message(ProcessOut.PROXY_CLIENT_NOTIFY_END_SESSION.process, params);
        Gson gson = new Gson();
        this.proxyClient.sendBroadcast(gson.toJson(message));
    }
}
