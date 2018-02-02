package com.neos.tcp.server.controller;

import com.neos.tcp.server.communication.Communication;
import com.neos.tcp.server.utils.FileUtils;

public final class Brain {
    
    /**
     * The communication of the application
     */
    private Communication communication;

    /**
     * Singleton management
     */
    private static Brain instance;


    
    /**
     * Main Constructor for the Brain
     */
    private Brain() {
        //FileUtils.loadGameConfiguration();
    }
    
    /**
     * Process called to wake up the brain and launch server
     */
    public void wakeUpBrain() {
        this.communication = Communication.getInstance(this);
    }
    
    /**
     * Getter of the instance Brain
     * @return the instance Brain
     */
    public static Brain getInstance () {
        if (instance == null) {
            instance = new Brain();
        }
        return instance;
    }

    /**
     * Creation of the Communication object
     * @return : the communication
     */
    public Communication getCommunication() {
        return communication;
    }
    
    /**
     * Setter of the communication instance
     * @param communication : the communication
     */
    public void setCommunication(Communication communication) {
        this.communication = communication;
    }
    
    /////////////////////
    // NETWORK REQUEST //
    /////////////////////
    
    /**
     * Process called to send pirate subscribed
     * @param port : the port of the pirate
     */
    public void sendPirateSubscribed(int port) {
        /*((IdNewPirateSubscribe) getCommunication().getProxyClient(
        ).getEncoders().get(ProcessOut.PROXY_CLIENT_ID_NEW_PIRATE_SUBSCRIBED.process)
        ).sendMessage(
            port,
            pirate
        );*/
    }
    
    ///////////////////////////
    // INTERFACE INTERACTION //
    ///////////////////////////
    
    /**
     * Process called when a new connection occur
     */
    public void notifyNewConnection() {

    }
}
