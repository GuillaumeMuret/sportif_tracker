package com.neos.tcp.server.communication.socket;

import com.neos.tcp.server.communication.PostmanServer;
import com.neos.tcp.server.utils.LogUtils;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketConnexion extends AbstractSocket {

    /**
     * Time waiting for a socket connection
     */
    public static final int TW_CONNECTION_SOCKET = 1000;

    /**
     * Queue of the communication
     */
    public static final int QUEUE_WAIT = 100;

    /**
     * Port use for the first communication
     */
    public static final int SERVER_PORT = 50000;

    /**
     * The postman used to manage the connexion
     */
    private PostmanServer postmanServer;

    /**
     * Main constructor of the server sockets
     *
     * @param postmanServer : the postman which manage the sockets
     */
    public ServerSocketConnexion(PostmanServer postmanServer) {
        this.postmanServer = postmanServer;
        stateSocket = AbstractSocket.StateSocket.CONNEXION;
        ServerSocketConnexion.SetUpConnexion setUpConnexion = new ServerSocketConnexion.SetUpConnexion();
        setUpConnexion.start();
    }

    /**
     * process to connect the application to the client by the socket
     */
    private class SetUpConnexion extends Thread {
    
        @Override
        public void run() {
            super.run();
            LogUtils.debug("Create socket");

            stateSocket = StateSocket.ERROR; // default error : connection not available / has failed

            // Set up the socket
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                while (true) {
                    Socket mySocket = serverSocket.accept();
                    ServerSocketCommunication socketCommunication = new ServerSocketCommunication(mySocket);
                    stateSocket = StateSocket.CONNECTED;
                    LogUtils.debug("Socket connected : " + mySocket.getInetAddress() + " on Port => " + mySocket.getPort());
                    postmanServer.notifyNewSocketConnected(mySocket.getPort(), socketCommunication);
                }
            } catch (SocketTimeoutException ste) {

                // appears when the socket reach timeout limit
                LogUtils.error("SocketTimeoutException : not found");

            } catch (ConnectException connectException) {

                // appears when the network is not available (wifi may be disabled)
                LogUtils.error("ConnectException : not found / Wifi interface disabled");

            } catch (IOException e) {

                // appears when the socket is already connected
                LogUtils.error("SocketError debug");
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter of the socket state
     *
     * @return the state's socket
     */
    public StateSocket getStateSocket() {
        return stateSocket;
    }
}
