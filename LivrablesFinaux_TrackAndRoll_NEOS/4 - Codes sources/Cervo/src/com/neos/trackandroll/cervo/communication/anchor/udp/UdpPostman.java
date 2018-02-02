package com.neos.trackandroll.cervo.communication.anchor.udp;

import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public final class UdpPostman {

    /**
     * State of socket's postmanUDPClient
     */
    public static final int CONNEXION = 0;
    public static final int CONNECTED = 1;
    public static final int ERROR = 2;
    public static final int KILL = 3;

    /**
     * The packet buffer size
     */
    public static final int PACKET_BUFFER_SIZE = 5000;

    /**
     * The UDP port of the RTLS communication
     */
    private static final int UDP_RTLS_PORT = 8787;

    /**
     * State of the socket
     */
    public int stateSocket;

    /**
     * The type of encodage
     */
    private static final String ENCODAGE = "UTF-8";

    /**
     * The socket used for the connexion
     */
    private DatagramSocket mySocket;

    /**
     * The udp communication
     */
    private UdpAnchorCommunication udpAnchorCommunication;

    /**
     * Singleton management
     */
    private static UdpPostman instance;

    /**
     * Builder of the Postman
     * @param udpAnchorCommunication : the udp anchor communication instance
     */
    private UdpPostman(UdpAnchorCommunication udpAnchorCommunication) {
        stateSocket = CONNEXION;
        this.udpAnchorCommunication = udpAnchorCommunication;
        UdpPostman.SetUpConnexion setUpConnexion = new UdpPostman.SetUpConnexion();
        setUpConnexion.start();
    }

    /**
     * Getter of the instance Postman
     * @param udpAnchorCommunication : the udp anchor communication instance
     * @return the instance Postman
     */
    public static UdpPostman getInstance(UdpAnchorCommunication udpAnchorCommunication) {
        if (instance == null) {
            instance = new UdpPostman(udpAnchorCommunication);
        }
        return instance;
    }

    /**
     * read a message on the socket and return the message
     *
     * @return the message
     * @throws IOException : the input output exception thrown
     * @throws NullPointerException : the null pointer exception thrown
     */
    public String readMessage() throws IOException, NullPointerException {

        byte[] ddata = new byte[PACKET_BUFFER_SIZE];
        DatagramPacket dpack = new DatagramPacket(ddata, PACKET_BUFFER_SIZE);
        mySocket.receive(dpack);
        if (ddata != null && ddata.length > 0) {
            return new String(ddata);
        } else {
            return null;
        }
    }

    /**
     * process to connect the application to set up the connection socket
     */
    private class SetUpConnexion extends Thread {

        @Override
        public void run() {
            super.run();
            LogUtils.d("UDP Creation du socket");

            stateSocket = ERROR; // default error : connection not available / has failed

            // Set up the socket
            try {

                mySocket = new DatagramSocket(UDP_RTLS_PORT);
                stateSocket = CONNECTED;

                LogUtils.d("UDP Socket Created");
                udpAnchorCommunication.launchReadThread();

            } catch (ConnectException ce) {

                // appears when the network is not available (wifi may be disabled)
                LogUtils.e("UDP ConnectException : not found / Wifi interface disabled");

            } catch (IOException e) {

                // appears when the socket is already connected
                LogUtils.e("UDP SocketError debug");
                e.printStackTrace();
            }
        }
    }

    /**
     * Process called to close the socket
     */
    public void closeSocket() {
        if (this.mySocket != null) {
            if (this.mySocket.isConnected()) {
                this.mySocket.close();
                this.stateSocket = KILL;
            }
        }
    }

    /**
     * Getter of the socket state
     *
     * @return the state's socket
     */
    public int getStateSocket() {
        return stateSocket;
    }
}
