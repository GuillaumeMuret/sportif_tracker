package com.neos.tcp.server.communication.protocole;

public enum ProcessOut {

    PROXY_CLIENT_NOTIFY_START_SESSION            (Proxy.PROXY_CLIENT,"notifyStartSession"),
    PROXY_CLIENT_NOTIFY_END_SESSION              (Proxy.PROXY_CLIENT,"notifyEndSession"),
    PROXY_CLIENT_NOTIFY_CONNECTION               (Proxy.PROXY_CLIENT,"notifyConnection"),
    PROXY_CLIENT_NOTIFY_DISCONNECTION            (Proxy.PROXY_CLIENT,"notifyDisconnection"),
    PROXY_CLIENT_INFORM_SENSORS                  (Proxy.PROXY_CLIENT,"informsSensors"),
    PROXY_CLIENT_SAVE_SESSION                    (Proxy.PROXY_CLIENT,"saveSession");

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


