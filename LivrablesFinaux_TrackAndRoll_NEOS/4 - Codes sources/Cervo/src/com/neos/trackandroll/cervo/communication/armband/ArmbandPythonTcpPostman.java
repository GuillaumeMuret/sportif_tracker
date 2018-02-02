package com.neos.trackandroll.cervo.communication.armband;


import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.communication.armband.socket.SocketArmbandCommunication;
import com.neos.trackandroll.cervo.communication.armband.socket.SocketArmbandConnexion;

import java.io.IOException;

public final class ArmbandPythonTcpPostman {

    /**
     * Map with KEY = port and VALUE = SocketMaestroCommunication
     */
    private SocketArmbandCommunication serverSocketCommunication;

    /**
     * The armband communication instance
     */
    private ArmbandCommunication armbandCommunication;

    /**
     * Singleton management
     */
    private static ArmbandPythonTcpPostman instance;

    /**
     * Main constructor of the Postman
     *
     * @param armbandCommunication : the armband communication instance
     */
    private ArmbandPythonTcpPostman(ArmbandCommunication armbandCommunication) {
        new SocketArmbandConnexion(this);
        this.armbandCommunication = armbandCommunication;
    }

    /**
     * Getter of the instance Postman
     *
     * @param armbandCommunication : the armband communication instance
     * @return the instance Postman
     */
    public static ArmbandPythonTcpPostman getInstance(ArmbandCommunication armbandCommunication) {
        if (instance == null) {
            instance = new ArmbandPythonTcpPostman(armbandCommunication);
        }
        return instance;
    }

    /**
     * Read a message on the socket and return the message
     *
     * @return : the socket message
     * @throws IOException          : the input output exception
     * @throws NullPointerException : the null pointer exception
     */
    public String readMessage() throws IOException, NullPointerException {
        return serverSocketCommunication.readMessage();
    }

    /**
     * Process called to get the socket state
     *
     * @return the state of the socket
     */
    public AbstractSocket.StateSocket getStateSocket() {
        if (serverSocketCommunication != null) {
            return serverSocketCommunication.getStateSocket();
        } else {
            return null;
        }
    }

    /**
     * Process called to notify all the observers of the socket state
     *
     * @param serverSocketCommunication : the socket
     */
    public void notifyNewSocketConnected(SocketArmbandCommunication serverSocketCommunication) {
        // Add socket to map
        this.serverSocketCommunication = serverSocketCommunication;
        this.armbandCommunication.launchReadThread();
    }

    /**
     * Process called to remove the socket communication. Occur on socket error
     */
    public void removeSocketCommunication() {
        serverSocketCommunication = null;
    }

    /**
     * Manage if the socket communication is needed
     *
     * @return : if the socket communication is needed
     */
    public boolean isSocketCommunicationNeeded() {
        return !(
                serverSocketCommunication != null
                        && serverSocketCommunication.getStateSocket().equals(AbstractSocket.StateSocket.CONNECTED)
        );
    }
}
