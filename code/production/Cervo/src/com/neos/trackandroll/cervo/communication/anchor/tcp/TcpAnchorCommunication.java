package com.neos.trackandroll.cervo.communication.anchor.tcp;

import com.neos.trackandroll.cervo.communication.ICommunicationState;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.utils.LogUtils;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.google.gson.Gson;

import java.io.IOException;

public final class TcpAnchorCommunication {

    /**
     * Interface of the communication state
     */
    private ICommunicationState communicationState;

    /**
     * The bluetoothPostman who send the messages for the client
     */
    private TcpAnchorPostmanClient tcpAnchorPostmanClient;

    /**
     * The UdpDistributor of the different method call after a reception
     */
    private TcpAnchorDistributor tcpAnchorDistributor;

    /**
     * Proxy of the Brain (sendMessage the method to json format)
     */
    private TcpAnchorProxy tcpAnchorProxy;

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * The read thread
     */
    private ReadThread readThread;

    /**
     * Singleton management
     */
    private static TcpAnchorCommunication instance;

    /**
     * Builder of the communication
     * @param brain : the brain
     * @param communicationState : the communication state interface
     */
    private TcpAnchorCommunication(Brain brain, ICommunicationState communicationState) {
        this.brain = brain;
        this.communicationState = communicationState;
    
        //The tcpAnchorDistributor
        this.tcpAnchorDistributor = TcpAnchorDistributor.getInstance();
        
        //The tcpAnchorPostmanClient
        this.tcpAnchorPostmanClient = TcpAnchorPostmanClient.getInstance(this);

        // the client proxy
        this.tcpAnchorProxy = TcpAnchorProxy.getInstance(this.tcpAnchorPostmanClient);
    }

    /**
     * Getter of the instance UdpAnchorCommunication
     * @param brain : the brain
     * @param communicationState : the communication state interface
     * @return the instance UdpAnchorCommunication
     */
    public static TcpAnchorCommunication getInstance (Brain brain, ICommunicationState communicationState) {
        if (instance == null) {
            instance = new TcpAnchorCommunication(brain, communicationState);
        }
        return instance;
    }

    /**
     * Process called to reinit the communication
     */
    public void relaunchCommunication() {
        //The tcpAnchorPostmanClient
        this.tcpAnchorPostmanClient.relaunchPostman();
    }

    /**
     * Process which ask to the tcpAnchorPostmanClient to read a message
     * @return : the socket message
     * @throws IOException : the input output exception
     */
    public String readComMessage() throws IOException {
        if (this.getTcpAnchorPostmanClient().getStateSocket() == TcpAnchorPostmanClient.CONNECTED) {
            return tcpAnchorPostmanClient.readMessage();
        }
        return null;
    }

    /**
     * Process called ot launch the read thread on the communication socket
     */
    public void launchReadThread() {
        this.readThread = new ReadThread();
        this.readThread.start();
        this.communicationState.onTcpAnchorConnected();
    }

    /**
     * Getter of the tcpAnchorPostmanClient
     *
     * @return the tcpAnchorPostmanClient
     */
    public TcpAnchorPostmanClient getTcpAnchorPostmanClient() {
        return this.tcpAnchorPostmanClient;
    }

    /**
     * Reading thread class called to read a message
     */
    private class ReadThread extends Thread {

        /**
         * The received message from the socket
         */
        private String receivedMessage;

        /**
         * Called to read a message and send this message to the binder man's letter box
         *
         * @throws IOException          : The Input Output exception
         * @throws NullPointerException : The null pointer exception
         */
        private void manageReading() throws IOException, NullPointerException {
            // loop which read the buffer and block the task when nothing is received
            while ((receivedMessage = readComMessage()) == null) {
                LogUtils.useless();
            }
            LogUtils.d("MESSAGE RECU TCP ANCHOR > " + receivedMessage);
            try {
                Gson gson = new Gson();
                AnchorMessage anchorMessage = gson.fromJson(receivedMessage,AnchorMessage.class);
                brain.manageActions(tcpAnchorDistributor.dispatch(anchorMessage));
            } catch (Exception e) {
                communicationState.onTcpAnchorDisconnection();
                LogUtils.e("Exception tcpAnchorDistributor.dispatch => ", e);
            }
        }

        /**
         * Process called when a "start" of the thread occur
         */
        @Override
        public void run() {
            LogUtils.d("TCP anchor Launch Read Thread ");
            try {
                // infinite loop. If a network problem occur : the infinite loop die
                while (true) {
                    // manage the message read
                    manageReading();
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                LogUtils.e("No socket...");
            } catch (IOException ioe) {
                LogUtils.e("Socket closed !!!!!!!!!!");
            }
            brain.onTcpAnchorDisconnection();
        }
    }
    
    /**
     * Getter of the proxy client
     * @return : the proxy client
     */
    public TcpAnchorProxy getTcpAnchorProxy() {
        return tcpAnchorProxy;
    }

    /**
     * Getter of the communication state
     * @return the communication state
     */
    public ICommunicationState getCommunicationState() {
        return communicationState;
    }
}