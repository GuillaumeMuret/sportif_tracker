package com.neos.trackandroll.cervo.communication.anchor.tcp;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;

public final class TcpAnchorProxy {

    /**
     * Postman use to send and receive the message
     */
    private TcpAnchorPostmanClient postman;

    /**
     * Singleton management
     */
    private static TcpAnchorProxy instance;

    /**
     * Constructor of all the proxys
     * @param postman : the bluetoothPostman
     */
    private TcpAnchorProxy(TcpAnchorPostmanClient postman) {
        this.postman = postman;
    }

    /**
     * Getter of the instance proxy client
     * @param postmanServer : the bluetoothPostman of the communication
     * @return the instance proxy client
     */
    public static TcpAnchorProxy getInstance (TcpAnchorPostmanClient postmanServer) {
        if (instance == null) {
            instance = new TcpAnchorProxy(postmanServer);
        }
        return instance;
    }

    /**
     * Process called that convert and send anchorMessage
     * @param anchorMessage : the anchor message
     */
    public void sendMessage(AnchorMessage anchorMessage) {
        // Encode anchorMessage
        Gson gson = new Gson();
        String mess = gson.toJson(anchorMessage);

        // Tell handler to Send the ArmbandMessage
        this.postman.writeMessage(mess);
    }
}