package com.neos.trackandroll.cervo.communication.armband.socket;

import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.communication.armband.ArmbandPythonTcpPostman;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketArmbandConnexion extends AbstractSocket {

    /**
     * Time waiting for a socket connection
     */
    public static final int TW_CONNECTION_SOCKET = 1000;

    /**
     * Queue of the communication
     */
    public static final int QUEUE_WAIT = 100;

    /**
     * Address use for the communication
     */
    public static final String IP_ADDRESS = "127.0.0.1";

    /**
     * Port of the connection
     */
    public static final int PORT = 55000;

    /**
     * The bluetoothPostman used to manage the connexion
     */
    private ArmbandPythonTcpPostman bluetoothPostman;

    /**
     * Main constructor of the server sockets
     *
     * @param bluetoothPostman : the bluetoothPostman which manage the sockets
     */
    public SocketArmbandConnexion(ArmbandPythonTcpPostman bluetoothPostman) {
        this.bluetoothPostman = bluetoothPostman;
        stateSocket = AbstractSocket.StateSocket.CONNEXION;
        SocketArmbandConnexion.SetUpConnexion setUpConnexion = new SocketArmbandConnexion.SetUpConnexion();
        setUpConnexion.start();
    }

    /**
     * process to connect the application to the client by the socket
     */
    private class SetUpConnexion extends Thread {

        @Override
        public void run() {
            super.run();
            LogUtils.d("Bluetooth TCP Create socket");

            stateSocket = AbstractSocket.StateSocket.ERROR; // default error : connection not available / has failed

            // Set up the socket
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT, QUEUE_WAIT, InetAddress.getByName(IP_ADDRESS));
                //serverSocket = new ServerSocket(Constant.SERVER_MAESTRO_PORT);
                while (true) {
                    stateSocket = AbstractSocket.StateSocket.CONNECTED;
                    Socket mySocket = serverSocket.accept();

                    if (bluetoothPostman.isSocketCommunicationNeeded()) {
                        SocketArmbandCommunication socketCommunication = new SocketArmbandCommunication(mySocket);
                        bluetoothPostman.notifyNewSocketConnected(socketCommunication);
                    }

                    LogUtils.d("Socket connected : " + mySocket.getInetAddress() + " on Port => " + mySocket.getPort());
                }
            } catch (Exception e) {
                LogUtils.e("Socket server maestro error",e);
            }
            stateSocket = AbstractSocket.StateSocket.ERROR;
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
