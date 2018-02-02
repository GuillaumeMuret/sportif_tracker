package com.neos.tcp.server.communication.protocole;

public enum ProcessOut {

    PROXY_CLIENT_MOVE_ACCEPTED              (Proxy.PROXY_CLIENT,"/A "); // + posX-posY-energie

    /**
     * The owner
     */
    public final String owner;

    /**
     * The process called
     */
    public final String process;

    /**
     * Constructor of the enum with owner, num owner and the process
     * @param owner : the owner
     * @param process : the process called
     */
    ProcessOut(final String owner,final String process) {
        this.owner = owner;
        this.process = process;
    }
}


