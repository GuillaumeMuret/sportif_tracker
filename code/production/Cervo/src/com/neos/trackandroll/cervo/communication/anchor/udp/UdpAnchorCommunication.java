package com.neos.trackandroll.cervo.communication.anchor.udp;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.ICommunicationState;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.IOException;

public final class UdpAnchorCommunication {

    /**
     * The index of the first occurence of this character
     */
    private static final int INDEX_FIRST_OCCURENCE_CHARACTER = 10;

    /**
     * Interface of the communication state
     */
    private ICommunicationState communicationState;

    /**
     * The bluetoothPostman who send the messages for the client
     */
    private UdpPostman udpPostman;

    /**
     * The UdpDistributor of the different methode call after a reception
     */
    private UdpDistributor udpDistributor;

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Read thread
     */
    private ReadThread readThread;

    /**
     * Singleton management
     */
    private static UdpAnchorCommunication instance;

    /**
     * Builder of the communication
     *
     * @param brain              : the brain
     * @param communicationState : the communication state interface
     */
    private UdpAnchorCommunication(Brain brain, ICommunicationState communicationState) {
        this.brain = brain;
        this.communicationState = communicationState;

        // Map of the read threads
        this.readThread = new ReadThread();

        //The udpDistributor
        this.udpDistributor = UdpDistributor.getInstance();

        //The udpPostman
        this.udpPostman = UdpPostman.getInstance(this);
    }

    /**
     * Getter of the instance UdpAnchorCommunication
     *
     * @param brain              : the brain
     * @param communicationState : the communication state interface
     * @return the instance UdpAnchorCommunication
     */
    public static UdpAnchorCommunication getInstance(Brain brain, ICommunicationState communicationState) {
        if (instance == null) {
            instance = new UdpAnchorCommunication(brain, communicationState);
        }
        return instance;
    }

    /**
     * Process which ask to the udpPostman to read a message
     *
     * @return : the socket message
     * @throws IOException : the input output exception
     */
    public String readComMessage() throws IOException {
        if (this.getUdpPostman().getStateSocket() == UdpPostman.CONNECTED) {
            return udpPostman.readMessage();
        }
        return null;
    }

    /**
     * Process called to launch the read thread on the communication socket
     */
    public void launchReadThread() {
        this.readThread.start();
    }


    /**
     * Getter of the udpPostman
     *
     * @return the udpPostman
     */
    public UdpPostman getUdpPostman() {
        return this.udpPostman;
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

            // Custom message to be a JSON Message
            receivedMessage = receivedMessage.substring(0, receivedMessage.indexOf(INDEX_FIRST_OCCURENCE_CHARACTER));
            LogUtils.udp("MESSAGE DECODE UDP > " + receivedMessage);

            try {
                Gson gson = new Gson();
                AnchorMessage message = gson.fromJson(receivedMessage, AnchorMessage.class);
                brain.manageActions(udpDistributor.dispatch(message));
            } catch (NullPointerException npe) {
                LogUtils.e("NullPointerException udpDistributor.dispatch => ", npe);
            }
        }

        /**
         * Process called when a "start" of the thread occur
         */
        @Override
        public void run() {
            LogUtils.d("UDP anchor Launch Read Thread ");
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
        }
    }
}