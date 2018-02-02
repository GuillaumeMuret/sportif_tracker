package com.neos.tcp.server.communication.proxy.encoder;

import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.protocole.ProtocolVocabulary;
import com.neos.tcp.server.communication.proxy.ProxyClient;

public class MoveAccepted extends AbstractEncoder {
    
    /**
     * Main constructor of the proxy client
     * @param proxyClient : the proxy client
     */
    public MoveAccepted(ProxyClient proxyClient) {
        super(proxyClient);
    }
    
    /**
     * Process called to send the move accepted
     * @param port : the port
     * @param positionX : the position X of the pirate
     * @param positionY : the position Y of the pirate
     * @param energy : the pirate energy
     */
    public void sendMessage(int port, int positionX, int positionY, int energy) {
        this.proxyClient.sendMessage(
            port,
        ProcessOut.PROXY_CLIENT_MOVE_ACCEPTED.process
            + ProtocolVocabulary.SEPARATOR_WHITESPACE
            + String.valueOf(positionX)
            + ProtocolVocabulary.SEPARATOR_MINUS
            + String.valueOf(positionY)
            + ProtocolVocabulary.SEPARATOR_MINUS
            + String.valueOf(energy)
        );
    }
}
