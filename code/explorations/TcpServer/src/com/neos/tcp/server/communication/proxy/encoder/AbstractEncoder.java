package com.neos.tcp.server.communication.proxy.encoder;

import com.neos.tcp.server.communication.proxy.ProxyClient;

public abstract class AbstractEncoder {
    /**
     * the client proxy
     */
    protected ProxyClient proxyClient;
    
    /**
     * Main Constructor of the encoders
     * @param proxyClient : the proxy client where the message are going
     */
    public AbstractEncoder(ProxyClient proxyClient) {
        this.proxyClient = proxyClient;
    }
}
