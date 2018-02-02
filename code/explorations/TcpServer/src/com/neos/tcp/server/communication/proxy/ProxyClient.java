package com.neos.tcp.server.communication.proxy;

import com.neos.tcp.server.communication.PostmanServer;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.proxy.encoder.AbstractEncoder;
import com.neos.tcp.server.communication.proxy.encoder.MoveAccepted;

import java.util.HashMap;

public final class ProxyClient extends ProxyModel {
    
    /**
     * Map of all the encoders KEY = the process to call, VALUE = the class that encode the data
     */
    private HashMap<String,AbstractEncoder> encoders;
    
    /**
     * Singleton management
     */
    private static ProxyClient instance;
    
    /**
     * Main constructor of the client proxy
     * @param postman : the postman which manage sockets
     */
    private ProxyClient(PostmanServer postman) {
        super(postman);
        this.encoders = new HashMap<>();

        this.encoders.put(ProcessOut.PROXY_CLIENT_MOVE_ACCEPTED.process,new MoveAccepted(this));
    }
    
    /**
     * Getter of the instance proxy client
     * @param postmanServer : the postman of the communication
     * @return the instance proxy client
     */
    public static ProxyClient getInstance (PostmanServer postmanServer) {
        if (instance == null) {
            instance = new ProxyClient(postmanServer);
        }
        return instance;
    }
    
    /**
     * Process called to get the encoders of the proxy
     * @return : the encoders
     */
    public HashMap<String, AbstractEncoder> getEncoders() {
        return encoders;
    }
    
    /**
     * Process called to encode data
     * @param processOut : the process out to call
     * @return : the encoded data
    @Override
    protected String getMessageToSend(ProcessOut processOut) {
        return encoders.get(processOut.process).getEncodedData();
    }
    */
}
