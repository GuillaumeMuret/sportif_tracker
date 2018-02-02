package com.neos.trackandroll.cervo.communication.maestro.socket;

import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.communication.maestro.MaestroPostman;
import com.neos.trackandroll.cervo.model.constant.Constant;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketMaestroConnexion extends AbstractSocket {

    /**
     * Queue of the communication
     */
    public static final int QUEUE_WAIT = 100;

    /**
     * The postman used to manage the connexion
     */
    private MaestroPostman maestroPostman;

    /**
     * Main constructor of the server sockets
     *
     * @param maestroPostman : the postman which manage the sockets
     */
    public SocketMaestroConnexion(MaestroPostman maestroPostman) {
        this.maestroPostman = maestroPostman;
        stateSocket = AbstractSocket.StateSocket.CONNEXION;
        SocketMaestroConnexion.SetUpConnexion setUpConnexion = new SocketMaestroConnexion.SetUpConnexion();
        setUpConnexion.start();
    }

    /**
     * process to connect the application to the client by the socket
     */
    private class SetUpConnexion extends Thread {

        @Override
        public void run() {
            super.run();
            LogUtils.d("MAESTRO Create socket");

            stateSocket = StateSocket.ERROR; // default error : connection not available / has failed

            // Set up the socket
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(
                        Constant.SERVER_MAESTRO_PORT,
                        QUEUE_WAIT,
                        InetAddress.getByName(Constant.SERVER_MAESTRO_IP_ADDRESS)
                );
                //serverSocket = new ServerSocket(Constant.SERVER_MAESTRO_PORT);
                while (true) {
                    stateSocket = StateSocket.CONNECTED;
                    Socket mySocket = serverSocket.accept();

                    if (maestroPostman.isSocketCommunicationNeeded()) {
                        SocketMaestroCommunication socketCommunication = new SocketMaestroCommunication(mySocket);
                        maestroPostman.notifyNewSocketConnected(socketCommunication);
                    }

                    LogUtils.d("Socket connected : " + mySocket.getInetAddress() + " on Port => " + mySocket.getPort());
                }
            } catch (Exception e) {
                LogUtils.e("Socket server maestro error", e);
            }
            stateSocket = StateSocket.ERROR;
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
