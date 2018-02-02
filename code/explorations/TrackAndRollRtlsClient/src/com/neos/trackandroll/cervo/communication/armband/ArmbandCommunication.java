package com.neos.trackandroll.cervo.communication.armband;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.ICommunicationState;
import com.neos.trackandroll.cervo.communication.armband.message.ArmbandMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;

public final class ArmbandCommunication {

    /**
     * Interface of the communication state
     */
    private ICommunicationState communicationState;

    /**
     * The bluetoothPostman who send the messages for the client
     */
    private ArmbandPythonTcpPostman bluetoothPostman;

    /**
     * The UdpDistributor of the different methode call after a reception
     */
    private ArmbandDistributor armbandDistributor;

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Singleton management
     */
    private static ArmbandCommunication instance;

    /**
     * Builder of the communication
     *
     * @param brain : the brain
     * @param communicationState : the communication state interface
     */
    private ArmbandCommunication(Brain brain, ICommunicationState communicationState) {
        this.brain = brain;
        this.communicationState = communicationState;
        this.armbandDistributor = ArmbandDistributor.getInstance();
        this.bluetoothPostman = ArmbandPythonTcpPostman.getInstance(this);

        PythonExecutor.LaunchScript launchScript = new PythonExecutor().new LaunchScript();

        try {
            launchScript.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Getter of the instance UdpAnchorCommunication
     *
     * @param brain : the brain
     * @param communicationState : the communication state interface
     * @return the instance UdpAnchorCommunication
     */
    public static ArmbandCommunication getInstance(Brain brain, ICommunicationState communicationState) {
        if (instance == null) {
            instance = new ArmbandCommunication(brain, communicationState);
        }
        return instance;
    }

    /**
     * Process which ask to the udpPostmanClient to read a message
     *
     * @return : the socket message
     * @throws IOException : the input output exception
     */
    private String readComMessage() throws IOException {
        return bluetoothPostman.readMessage();
    }

    /**
     * Process called to launch the read thread on the TCP IP socket
     */
    public void launchReadThread() {
        new ReadThread().start();
    }

    /**
     * Process called to relaunch the communication with the Armband
     * @param brain : the brain of the system
     * @param communicationState : the communication state interface
     */
    public void relaunchCommunication(Brain brain, ICommunicationState communicationState) {
        instance = new ArmbandCommunication(brain, communicationState);
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
            LogUtils.d("MESSAGE RECU BLUETOOTH > " + receivedMessage);

            try {
                Gson gson = new Gson();
                ArmbandMessage armbandMessage = gson.fromJson(receivedMessage, ArmbandMessage.class);
                armbandDistributor.dispatch(armbandMessage);
                brain.manageActions(armbandDistributor.dispatch(armbandMessage));
            } catch (Exception e) {
                LogUtils.e("Exception distributor.dispatch !", e);
            }
        }

        /**
         * Process called when a "start" of the thread occur
         */
        @Override
        public void run() {
            LogUtils.d("Launch Read Thread socket armband ");
            try {
                // infinite loop. If a network problem occur : the infinite loop die
                while (true) {
                    // manage the message read
                    manageReading();
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                LogUtils.d("No socket...");
            } catch (IOException ioe) {
                LogUtils.d("Socket closed on socket armband");
                bluetoothPostman.removeSocketCommunication();

                ArrayList<ActionForBrain> actions = new ArrayList<>();
                actions.add(new ActionForBrain(ActionForBrain.ACTION_MANAGE_PYTHON_DISCONNECTED));
                brain.manageActions(actions);
            }
        }
    }
}
