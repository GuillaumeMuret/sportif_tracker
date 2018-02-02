package com.neos.trackandroll.cervo;

import com.neos.trackandroll.cervo.controller.Brain;

public class Main {

    public static final boolean IS_DEBUGGING = true;
    public static final boolean IS_MOCKING = false;

    
    /**
     * First process called to launch the server
     * @param args : the args that user can add
     */
    public static void main(String[] args) {
        Brain.getInstance();
    }
}