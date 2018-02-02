package com.neos.tcp.server;

import com.neos.tcp.server.controller.Brain;

public class Main {
    
    /**
     * First process called to launch the server
     * @param args : the args that user can add
     */
    public static void main(String[] args) {
        // Launch the application
        Brain.getInstance().wakeUpBrain();
    }
}