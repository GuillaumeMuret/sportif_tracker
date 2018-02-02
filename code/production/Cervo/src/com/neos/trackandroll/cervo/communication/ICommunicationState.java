package com.neos.trackandroll.cervo.communication;

public interface ICommunicationState {

    /**
     * Process called when the tcp anchor is connected
     */
    void onTcpAnchorConnected();

    /**
     * Process called when the tcp anchor is disconnected
     */
    void onTcpAnchorDisconnection();

    /**
     * Process called when the tcp anchor connection failed
     */
    void onTcpAnchorFailed();

    /**
     * Process called when the tcp maestro is connected
     */
    void onTcpMaestroConnected();

    /**
     * Process called when the tcp maestro is disconnected
     */
    void onTcpMaestroDisconnection();
}
