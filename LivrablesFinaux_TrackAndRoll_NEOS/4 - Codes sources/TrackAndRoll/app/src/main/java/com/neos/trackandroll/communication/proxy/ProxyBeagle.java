package com.neos.trackandroll.communication.proxy;

import com.neos.trackandroll.communication.Postman;
import com.neos.trackandroll.communication.distribution.out.ICommandOut;
import com.neos.trackandroll.communication.distribution.out.ProcessAskSensors;
import com.neos.trackandroll.communication.distribution.out.ProcessDeleteSaving;
import com.neos.trackandroll.communication.distribution.out.ProcessStartSaving;
import com.neos.trackandroll.communication.distribution.out.ProcessStopSaving;
import com.neos.trackandroll.communication.protocole.ProcessOut;
import com.neos.trackandroll.utils.LogUtils;

import java.util.HashMap;

public class ProxyBeagle {

    /**
     * The instance of the ProxyBeagle
     */
    private static ProxyBeagle instance;


    /**
     * Getter of the instance ProxyBeagle
     * @return the ProxyBeagle instance
     */
    public static ProxyBeagle getInstance(Postman postman) {
        if (instance == null){
            instance = new ProxyBeagle(postman);
        }
        return instance;
    }



    /**
     * postman use to send and receive the message
     */
    public Postman postman;

    private HashMap<String, ICommandOut> encoders;

    /**
     * Constructor of all the proxys
     * @param postman : the postman
     */
    private ProxyBeagle(Postman postman) {
        this.postman = postman;
        this.encoders = new HashMap<>();
        this.encoders.put(ProcessOut.PROCESS_ASK_SENSORS, new ProcessAskSensors());
        this.encoders.put(ProcessOut.PROCESS_START_SAVING, new ProcessStartSaving());
        this.encoders.put(ProcessOut.PROCESS_STOP_SAVING, new ProcessStopSaving());
        this.encoders.put(ProcessOut.PROCESS_DELETE_SAVING, new ProcessDeleteSaving());
    }

    public void sendMessage(String message) {
        LogUtils.d(LogUtils.DEBUG_TAG,"sendMessage = " + message);

        // Tell handler to Send the Message
        this.postman.writeMessage(message);
    }

    /**
     * Getter of the postman
     * @return the postman
     */
    public Postman getPostman() {
        return postman;
    }

    public HashMap<String, ICommandOut> getEncoders() {
        return encoders;
    }
}