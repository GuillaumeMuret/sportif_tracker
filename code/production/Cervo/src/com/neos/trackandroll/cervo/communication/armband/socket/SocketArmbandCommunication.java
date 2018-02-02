package com.neos.trackandroll.cervo.communication.armband.socket;

import com.neos.trackandroll.cervo.communication.AbstractSocket;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketArmbandCommunication extends AbstractSocket {

    /**
     * the buffer used to read a message on the socket
     */
    private BufferedReader bufferedReader;

    /**
     * the buffer used to write a message on the socket
     */
    private BufferedWriter bufferedWriter;

    /**
     * Main constructor of the armband socket with the python
     * @param mySocket : the socket of the python code
     */
    public SocketArmbandCommunication(Socket mySocket) {
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(mySocket.getInputStream(), AbstractSocket.ENCODAGE));
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(mySocket.getOutputStream(), AbstractSocket.ENCODAGE));
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
     * read a message on the socket and return the message
     *
     * @return the message
     * @throws IOException : exception of Input Output
     * @throws NullPointerException : the null pointer exception
     */
    //public SocketBluetoothMessage readMessage() throws IOException, NullPointerException {
    public String readMessage() throws IOException, NullPointerException {
        return bufferedReader.readLine();
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
