package com.neos.tcp.server.communication.socket;

public abstract class AbstractSocket {
    
    /**
     * State of socket
     */
    public enum StateSocket {
        CONNEXION,
        CONNECTED,
        ERROR,
        KILL;
    }
    
    /**
     * State of the socket
     */
    protected StateSocket stateSocket;
    
    /**
     * type of encodage.
     */
    protected static final String ENCODAGE = "UTF-8";
}
