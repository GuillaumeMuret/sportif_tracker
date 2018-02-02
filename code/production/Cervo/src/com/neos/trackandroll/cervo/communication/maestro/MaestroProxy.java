package com.neos.trackandroll.cervo.communication.maestro;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.utils.LogUtils;

public final class MaestroProxy {

    /**
     * Postman use to send and receive the message
     */
    public MaestroPostman postman;

    /**
     * Singleton management
     */
    private static MaestroProxy instance;

    /**
     * Constructor of all the proxys
     * @param postman : the postman
     */
    private MaestroProxy(MaestroPostman postman) {
        this.postman = postman;
    }

    /**
     * Getter of the instance proxy client
     * @param maestroPostman : the bluetoothPostman of the communication
     * @return the instance proxy client
     */
    public static MaestroProxy getInstance (MaestroPostman maestroPostman) {
        if (instance == null) {
            instance = new MaestroProxy(maestroPostman);
        }
        return instance;
    }

    /**
     * Process called that convert and send maestroMessage
     * @param maestroMessage : the maestro message to send
     */
    public void sendMessage(MaestroMessage maestroMessage) {

        // Encode maestroMessage
        Gson gson = new Gson();
        String mess = gson.toJson(maestroMessage);
        if (!maestroMessage.getProcess().equals(MaestroProcessOut.PROCESS_CHECK_CONNECTION)) {
            LogUtils.d("sendMessage = " + mess);
        }


        // Tell handler to Send the ArmbandMessage
        this.postman.writeMessage(mess);
    }
    
}
