package com.neos.trackandroll.cervo.communication.maestro;


import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.communication.maestro.socket.SocketMaestroCommunication;
import com.neos.trackandroll.cervo.communication.maestro.socket.SocketMaestroConnexion;

import java.io.IOException;

public final class MaestroPostman {

    /**
     * Socket state observator
     */
    private ISocketState iSocketState;

    /**
     * Map with KEY = port and VALUE = SocketMaestroCommunication
     */
    private SocketMaestroCommunication socketMaestroCommunication;

    /**
     * Singleton management
     */
    private static MaestroPostman instance;

    /**
     * Main constructor of the Postman
     */
    private MaestroPostman() {
        new SocketMaestroConnexion(this);
    }

    /**
     * Getter of the instance Postman
     *
     * @return the instance Postman
     */
    public static MaestroPostman getInstance() {
        if (instance == null) {
            instance = new MaestroPostman();
        }
        return instance;
    }

    /**
     * Process called to write message
     *
     * @param message : the message to send
     */
    public void writeMessage(String message) {
        if (socketMaestroCommunication != null
                && socketMaestroCommunication.getStateSocket() == AbstractSocket.StateSocket.CONNECTED) {
            socketMaestroCommunication.writeMessage(message);
        }
    }

    /**
     * Read a message on the socket and return the message
     *
     * @return : the socket message
     * @throws IOException          : the input output exception
     * @throws NullPointerException : the null pointer exception
     */
    public String readMessage() throws IOException, NullPointerException {
        return socketMaestroCommunication.readMessage();
    }

    /**
     * Process called to get the socket state
     *
     * @return the state of the socket
     */
    public SocketMaestroConnexion.StateSocket getStateSocket() {
        if (socketMaestroCommunication != null) {
            return socketMaestroCommunication.getStateSocket();
        } else {
            return null;
        }
    }

    /**
     * Process called to add a socket observator
     *
     * @param iSocketState : the interface of the socket observator
     */
    public void addStateSocketObservator(ISocketState iSocketState) {
        this.iSocketState = iSocketState;
    }

    /**
     * Process called to notify all the observers of the socket state
     *
     * @param socketMaestroCommunication : the socket
     */
    public void notifyNewSocketConnected(SocketMaestroCommunication socketMaestroCommunication) {
        // Add socket to map
        this.socketMaestroCommunication = socketMaestroCommunication;

        iSocketState.onSocketConnected();
    }

    /**
     * Process called to remove the socket communication. Occur on socket error
     */
    public void removeSocketCommunication() {
        socketMaestroCommunication = null;
    }

    /**
     * Manage if the socket communication is needed
     *
     * @return : if the socket communication is needed
     */
    public boolean isSocketCommunicationNeeded() {
        return !(
                socketMaestroCommunication != null
                        && socketMaestroCommunication.getStateSocket().equals(AbstractSocket.StateSocket.CONNECTED)
        );
    }
}
