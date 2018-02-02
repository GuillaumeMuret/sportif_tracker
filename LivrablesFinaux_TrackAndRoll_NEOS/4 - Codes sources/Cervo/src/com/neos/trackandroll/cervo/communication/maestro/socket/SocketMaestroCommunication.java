package com.neos.trackandroll.cervo.communication.maestro.socket;

import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class SocketMaestroCommunication extends AbstractSocket {

    /**
     * the buffer used to read a message on the socket
     */
    private BufferedReader bufferedReader;

    /**
     * the buffer used to write a message on the socket
     */
    private BufferedWriter bufferedWriter;
    
    /**
     * The writing thread
     */
    private SocketMaestroCommunication.Write writingThread;

    /**
     * The socket used for the connexion
     */
    private Socket mySocket;

    /**
     * Main constructor of the connexion socket
     *
     * @param mySocket : socket of the connexion
     */
    public SocketMaestroCommunication(Socket mySocket) {
        this.mySocket = mySocket;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(this.mySocket.getInputStream(), AbstractSocket.ENCODAGE));
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(this.mySocket.getOutputStream(), AbstractSocket.ENCODAGE));
            stateSocket = StateSocket.CONNECTED;
        } catch (SocketTimeoutException ste) {
            // appears when the socket reach timeout limit
            LogUtils.e("SocketTimeoutException : not found");
        } catch (IOException e) {
            // appears when the socket is already connected
            LogUtils.e("SocketError debug");
            e.printStackTrace();
        }
    }

    /**
     * Process called to write a message on the socket
     * @param message : the message to send
     */
    public void writeMessage(String message) {
        if (writingThread != null) {
            while (writingThread.getState() != Thread.State.TERMINATED) {
                LogUtils.useless();
            }
            writingThread = new SocketMaestroCommunication.Write(message);
            writingThread.start();
        } else {
            writingThread = new SocketMaestroCommunication.Write(message);
            writingThread.start();
        }
    }

    /**
     * read a message on the socket and return the message
     *
     * @return the message
     * @throws IOException : exception of Input Output
     * @throws NullPointerException : the null pointer exception
     */
    public String readMessage() throws IOException, NullPointerException {
        return bufferedReader.readLine();
    }

    /**
     * Write on the BufferWriter to send a message
     */
    private class Write extends Thread {

        /**
         * The message to send
         */
        private String messageToSend;
    
        /**
         * Constructor of the Thread to write a message
         * @param messageToSend : the message to send
         */
        public Write(String messageToSend) {
            this.messageToSend = messageToSend;
        }

        /**
         * Called when the client is executed
         */
        @Override
        public synchronized void run() {
            if (mySocket != null) {
                if (!mySocket.isClosed()) {
                    try {
                        bufferedWriter.write(messageToSend, 0, messageToSend.length());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException e) {
                        stateSocket = StateSocket.ERROR;
                        LogUtils.e("WRITE ERROR",e);

                        ArrayList<ActionForBrain> actions = new ArrayList<>();
                        actions.add(new ActionForBrain(ActionForBrain.ACTION_STOP_TIMER_CHECK_MAESTRO_CONNECTION));
                        Brain.getInstance().manageActions(actions);
                    }
                } else {
                    stateSocket = StateSocket.ERROR;
                }
            }
        }
    }

    /**
     * Getter of the socket state
     *
     * @return the state's socket
     */
    public AbstractSocket.StateSocket getStateSocket() {
        return stateSocket;
    }
}
