/**
 * @file Communication.java
 * @brief Class of communication (create the postman and has all the proxys)
 *
 * @version 1.0
 * @date 14/04/2016
 * @author Guillaume Muret
 * @copyright
 *	Copyright (c) 2016, PRØVE
 * 	All rights reserved.
 * 	Redistribution and use in source and binary forms, with or without
 * 	modification, are permitted provided that the following conditions are met:
 *
 * 	* Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright
 * 	  notice, this list of conditions and the following disclaimer in the
 * 	  documentation and/or other materials provided with the distribution.
 * 	* Neither the name of PRØVE, Angers nor the
 * 	  names of its contributors may be used to endorse or promote products
 *   	derived from this software without specific prior written permission.
 *
 * 	THIS SOFTWARE IS PROVIDED BY PRØVE AND CONTRIBUTORS ``AS IS'' AND ANY
 * 	EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * 	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * 	DISCLAIMED. IN NO EVENT SHALL PRØVE AND CONTRIBUTORS BE LIABLE FOR ANY
 * 	DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * 	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * 	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * 	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * 	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * 	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package com.neos.trackandroll.communication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;
import com.neos.trackandroll.communication.proxy.ProxyBeagle;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

import java.io.IOException;

public final class Communication {


    /**
     * The client proxy
     */
    private ProxyBeagle proxyBeagle;

    /**
     * the handler for the timer
     */
    private Handler handlerTimer;

    /**
     * The postman who send the messages for the UI
     */
    private Postman postman;

    /**
     * The thread which ask to the communication to ask to the postman to read a message on the socket
     */
    private ReadThread readThread;

    /**
     * The Distributor of the different method call after a reception
     */
    private Distributor distributor;

    /**
     * The communication event of the app service
     */
    private CommunicationEvent communicationEvent;

    /**
     * The communication instance
     */
    public static Communication instance;

    /**
     * the runnable of the timer (we post an ALARM_UI if time is over)
     */
    private Runnable timerConnectedToCervo;

    /**
     * letterbox of the binder man
     */
    @SuppressLint("HandlerLeak")
    private Handler handlerService = new Handler() {
        @Override
        synchronized public void handleMessage(Message msg) {
            Gson gson = new Gson();
            com.neos.trackandroll.communication.message.Message receivedMessage = gson.fromJson(msg.getData().getString(
                    ProtocolVocabulary.COMMUNICATION_LETTER_BOX_KEY),
                    com.neos.trackandroll.communication.message.Message.class
            );
            try {
                communicationEvent.onSendActivityBroadcast(distributor.dispatch(receivedMessage));
            }catch (NullPointerException npe){
                LogUtils.e(LogUtils.DEBUG_TAG,"Command from server not found",npe);
            }
        }
    };

    /**
     * Builder of the communication
     */
    private Communication(CommunicationEvent communicationEvent) {
        // The communication event for the service
        this.communicationEvent = communicationEvent;

        //The postman
        this.postman = Postman.getInstance(this);

        //All the proxy
        this.proxyBeagle = ProxyBeagle.getInstance(this.postman);

        //The distributor
        this.distributor = Distributor.getInstance();
    }


    public static Communication getInstance(CommunicationEvent communicationEvent) {
        if (instance == null){
            instance = new Communication(communicationEvent);
        }
        return instance;
    }

    public void restartCommunication(){
        this.closeSocket();
        this.postman.restartCommunication();
    }

    /**
     * Process which ask to the postman to read a message
     *
     * @return the received message
     */
    public String readComMessage() throws IOException {
        if (this.getPostman().getStateSocket() == Postman.CONNECTED) {
            return postman.readMessage();
        }
        return null;
    }

    /**
     * Process called to close the socket
     */
    public void closeSocket() {
        if (this.postman != null) {
            this.postman.closeSocket();
        }
    }

    /**
     * Getter of the postman
     *
     * @return the postman
     */
    public Postman getPostman() {
        return this.postman;
    }

    public void onSocketConnected(){
        this.readThread = new ReadThread();
        this.readThread.start();
        this.communicationEvent.onSendActivityBroadcast(AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_OK);
    }

    public void onSocketConnectionFailed(){
        this.restartCommunication();
        this.communicationEvent.onSendActivityBroadcast(AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_KO);
    }

    /**
     * Reading thread class called to read a message
     */
    private class ReadThread extends Thread {

        /**
         * The received message from the socket
         */
        String receivedMessage;

        /**
         * Called to read a message and send this message to the binder man's letter box
         *
         * @throws IOException
         * @throws NullPointerException
         */
        private void manageReading() throws IOException, NullPointerException {

            // loop which read the buffer and block the task when nothing is received
            while ((receivedMessage = readComMessage()) == null) ;

            LogUtils.d(LogUtils.DEBUG_TAG,"MESSAGE RECU >" + receivedMessage);
            // Reset the timer -> we have a response from the beaglebone ! All clear !
            // resetTimer();
            // Send message to the BinderMan letter box
            Message m = new Message();
            Bundle b = new Bundle();
            b.putString(ProtocolVocabulary.COMMUNICATION_LETTER_BOX_KEY, receivedMessage);
            m.setData(b);
            handlerService.sendMessage(m);
        }

        /**
         * Process called when a "start" of the thread occur
         */
        @Override
        public void run() {

            try {
                // infinite loop. If a network problem occur : the infinite loop die
                while (true) {
                    // manage the message read
                    manageReading();
                }
            } catch (Exception e) {
                LogUtils.e(LogUtils.DEBUG_TAG, "Socket error",e);
                onSocketConnectionFailed();
            }
        }
    }

    public ProxyBeagle getProxyBeagle() {
        return proxyBeagle;
    }

    public boolean isBeagleSocketConnected(){
        if(postman.getStateSocket() == Postman.CONNECTED){
            return true;
        }
        else{
            return false;
        }
    }

    // TODO manage timer
    private void launchTimer(){
        LogUtils.d(LogUtils.DEBUG_TAG,"Timer launched");
        handlerTimer.postDelayed(timerConnectedToCervo=new Runnable() {
            @Override
            public void run() {
                // post a message when time is over
                LogUtils.d(LogUtils.DEBUG_TAG,"END OF THE TIMER");
                // TODO send something to the app service
            }
        }, Constant.TIME_BEFORE_BEAGLEBONE_SEND_SOMETHING);
    }


    /**
     * Reset the timer (launch previously) : the bus wil not be post (cf launchTimer)
     */
    private void resetTimer(){
        handlerTimer.removeCallbacks(timerConnectedToCervo);
    }
}