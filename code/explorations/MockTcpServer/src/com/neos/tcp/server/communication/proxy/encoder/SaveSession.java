package com.neos.tcp.server.communication.proxy.encoder;

import com.google.gson.Gson;
import com.neos.tcp.server.communication.message.Data;
import com.neos.tcp.server.communication.message.Message;
import com.neos.tcp.server.communication.message.Params;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.proxy.ProxyClient;

public class SaveSession extends AbstractEncoder {

    /**
     * Main constructor of the proxy client
     * @param proxyClient : the proxy client
     */
    public SaveSession(ProxyClient proxyClient) {
        super(proxyClient);
    }

    /**
     * Process called to send the move accepted
     */
    public void sendMessage(Data data) {
        Params params = new Params();
        params.setData(data);
        Message message = new Message(ProcessOut.PROXY_CLIENT_SAVE_SESSION.process, params);
        Gson gson = new Gson();
        this.proxyClient.sendBroadcast(gson.toJson(message));
    }
}
