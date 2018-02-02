package com.neos.trackandroll.cervo.communication.maestro;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.ICommunicationState;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.IOException;

public final class MaestroCommunication extends Thread implements ISocketState {

    /**
     * Interface of the communication state
     */
    private ICommunicationState communicationState;

    /**
     * The maestroPostman who send the messages for the client
     */
    private MaestroPostman maestroPostman;

    /**
     * The MaestroDistributor of the different method call after a reception
     */
    private MaestroDistributor maestroDistributor;

    /**
     * Proxy of the Brain (sendMessage the method to json format)
     */
    private MaestroProxy maestroProxy;

    /**
     * The read thread
     */
    private ReadThread readThread;

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Singleton management
     */
    private static MaestroCommunication instance;

    /**
     * Builder of the communication
     *
     * @param brain              : the brain
     * @param communicationState : the communication state interface
     */
    private MaestroCommunication(Brain brain, ICommunicationState communicationState) {
        this.brain = brain;
        this.communicationState = communicationState;

        //The maestroDistributor
        this.maestroDistributor = MaestroDistributor.getInstance(brain);

        //The maestroPostman
        this.maestroPostman = MaestroPostman.getInstance();
        this.maestroPostman.addStateSocketObservator(this);

        // the client proxy
        this.maestroProxy = MaestroProxy.getInstance(this.maestroPostman);
    }

    /**
     * Getter of the instance MaestroCommunication
     *
     * @param brain              : the brain
     * @param communicationState : the communication state interface
     * @return the instance MaestroCommunication
     */
    public static MaestroCommunication getInstance(Brain brain, ICommunicationState communicationState) {
        if (instance == null) {
            instance = new MaestroCommunication(brain, communicationState);
        }
        return instance;
    }

    /**
     * Process which ask to the maestroPostman to read a message
     *
     * @return : the socket message
     * @throws IOException : the input output exception
     */
    public String readComMessage() throws IOException {
        if (this.getMaestroPostman().getStateSocket() != null
                && this.getMaestroPostman().getStateSocket() == AbstractSocket.StateSocket.CONNECTED) {
            return maestroPostman.readMessage();
        }
        return null;
    }

    /**
     * Getter of the maestroPostman
     *
     * @return the maestroPostman
     */
    public MaestroPostman getMaestroPostman() {
        return this.maestroPostman;
    }

    /**
     * Process called by the postman when a new socket is connected
     */
    @Override
    public void onSocketConnected() {
        this.readThread = new ReadThread();
        this.readThread.start();
        this.communicationState.onTcpMaestroConnected();
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
            LogUtils.d("MESSAGE RECU > " + receivedMessage);

            // Send message to the dispatcher to be interpreted
            try {
                Gson gson = new Gson();
                MaestroMessage message = gson.fromJson(receivedMessage, MaestroMessage.class);
                brain.manageActions(maestroDistributor.dispatch(message));
            } catch (NullPointerException npe) {
                LogUtils.e("MAESTRO NullPointerException maestroDistributor.dispatch => ", npe);
            }
        }

        /**
         * Process called when a "start" of the thread occur
         */
        @Override
        public void run() {
            LogUtils.d("MAESTRO Launch Read Thread on port ");
            try {
                // infinite loop. If a network problem occur : the infinite loop die
                while (true) {
                    // manage the message read
                    manageReading();
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                LogUtils.d("MAESTRO No socket...");
            } catch (IOException ioe) {
                LogUtils.d("MAESTRO Socket closed...");
            }
            maestroPostman.removeSocketCommunication();
            communicationState.onTcpMaestroDisconnection();
        }
    }

    /**
     * Getter of the proxy client
     *
     * @return : the proxy client
     */
    public MaestroProxy getMaestroProxy() {
        return maestroProxy;
    }
}