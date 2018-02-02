package com.neos.trackandroll.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.neos.trackandroll.communication.Communication;
import com.neos.trackandroll.communication.CommunicationEvent;
import com.neos.trackandroll.communication.protocole.ProcessOut;
import com.neos.trackandroll.utils.LogUtils;

public class AppService extends Service implements CommunicationEvent {

    public static final String REQUEST_ACTIVITY_PROCESS_NOTIFY_START_SESSION    = "REQUEST_ACTIVITY_PROCESS_NOTIFY_START_SESSION";
    public static final String REQUEST_ACTIVITY_PROCESS_NOTIFY_END_SESSION      = "REQUEST_ACTIVITY_PROCESS_NOTIFY_END_SESSION";
    public static final String REQUEST_ACTIVITY_PROCESS_NOTIFY_CONNECTION       = "REQUEST_ACTIVITY_PROCESS_NOTIFY_CONNECTION";
    public static final String REQUEST_ACTIVITY_PROCESS_NOTIFY_DISCONNECTION    = "REQUEST_ACTIVITY_PROCESS_NOTIFY_DISCONNECTION";
    public static final String REQUEST_ACTIVITY_PROCESS_INFORM_SENSORS          = "REQUEST_ACTIVITY_PROCESS_INFORM_SENSORS";
    public static final String REQUEST_ACTIVITY_PROCESS_SESSION_SAVED = "REQUEST_ACTIVITY_PROCESS_SESSION_SAVED";
    public static final String REQUEST_ACTIVITY_NOTHING = "REQUEST_ACTIVITY_NOTHING";

    public static final String REQUEST_SERVICE_PROCESS_ASK_SENSORS              = "REQUEST_SERVICE_PROCESS_ASK_SENSORS";
    public static final String REQUEST_SERVICE_PROCESS_START_SAVING             = "REQUEST_SERVICE_PROCESS_START_SAVING";
    public static final String REQUEST_SERVICE_PROCESS_STOP_SAVING              = "REQUEST_SERVICE_PROCESS_STOP_SAVING";
    public static final String REQUEST_SERVICE_PROCESS_DELETE_SAVING            = "REQUEST_SERVICE_PROCESS_DELETE_SAVING";


    public static final String REQUEST_SERVICE_CONNECTION_OK                    = "REQUEST_SERVICE_CONNECTION_OK";
    public static final String REQUEST_ACTIVITY_PROCESS_CONNECTION_OK           = "REQUEST_ACTIVITY_PROCESS_CONNECTION_OK";
    public static final String REQUEST_ACTIVITY_PROCESS_CONNECTION_KO = "REQUEST_ACTIVITY_PROCESS_CONNECTION_KO";

    private AppService.ServiceBroadcastReceiver serviceBroadcastReceiver;

    private boolean isRegistered = false;

    private Communication communication;

    /**
     * Process called at the creation of the service
     */
    @Override
    public void onCreate() {
        super.onCreate();
        registerBroadcastReceiver();
        this.communication = Communication.getInstance(this);
    }

    /**
     * Process called at the destruction of the service
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }

    /**
     * Process called when an intent launch the service
     * @param intent : the intent that launch the service
     * @param flags : the flag (that determine the service death)
     * @param startId : the id
     * @return : the type of service created
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //////////////////////////
    // BROADCAST MANAGEMENT //
    //////////////////////////

    /**
     * Process called to unregister on the broadcast receiver
     */
    protected void unregisterBroadcastReceiver() {
        if (serviceBroadcastReceiver != null && isRegistered) {
            isRegistered = false;
            this.unregisterReceiver(serviceBroadcastReceiver);
        }
    }

    /**
     * Process called to register on the broadcast receiver
     */
    protected void registerBroadcastReceiver() {
        if (!isRegistered) {
            isRegistered = true;
            serviceBroadcastReceiver = new ServiceBroadcastReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AppService.REQUEST_SERVICE_PROCESS_ASK_SENSORS);
            intentFilter.addAction(AppService.REQUEST_SERVICE_PROCESS_START_SAVING);
            intentFilter.addAction(AppService.REQUEST_SERVICE_PROCESS_STOP_SAVING);
            intentFilter.addAction(AppService.REQUEST_SERVICE_PROCESS_DELETE_SAVING);
            intentFilter.addAction(AppService.REQUEST_SERVICE_CONNECTION_OK);

            this.registerReceiver(serviceBroadcastReceiver, intentFilter);
        }
    }

    /**
     * Nested class of the service broadcast receiver that enable the communication with the
     * activity of the application
     */
    public class ServiceBroadcastReceiver extends BroadcastReceiver {

        /**
         * Process called when a broadcast has been received on the service broadcast receiver
         * @param context : the context
         * @param intent : the intent (to transmit data)
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.d(LogUtils.DEBUG_TAG, "Broadcast Receive => " + action);
            if(intent.getAction() != null){
                switch (intent.getAction()){
                    case AppService.REQUEST_SERVICE_PROCESS_ASK_SENSORS:
                        communication.getProxyBeagle().sendMessage(communication.getProxyBeagle().getEncoders().get(ProcessOut.PROCESS_ASK_SENSORS).encode());
                        break;

                    case AppService.REQUEST_SERVICE_PROCESS_START_SAVING:
                        communication.getProxyBeagle().sendMessage(communication.getProxyBeagle().getEncoders().get(ProcessOut.PROCESS_START_SAVING).encode());
                        break;

                    case AppService.REQUEST_SERVICE_PROCESS_STOP_SAVING:
                        communication.getProxyBeagle().sendMessage(communication.getProxyBeagle().getEncoders().get(ProcessOut.PROCESS_STOP_SAVING).encode());
                        break;

                    case AppService.REQUEST_SERVICE_PROCESS_DELETE_SAVING:
                        communication.getProxyBeagle().sendMessage(communication.getProxyBeagle().getEncoders().get(ProcessOut.PROCESS_DELETE_SAVING).encode());
                        break;

                    case AppService.REQUEST_SERVICE_CONNECTION_OK:
                        sendActivityRequest(communication.isBeagleSocketConnected()
                            ? REQUEST_ACTIVITY_PROCESS_CONNECTION_OK
                            : REQUEST_ACTIVITY_PROCESS_CONNECTION_KO
                        );
                        break;

                }
            }
        }
    }

    /**
     * Process called to send a broadcast to the activity
     * @param requestAction : the request action to execute on the activity
     */
    public void sendActivityRequest(String requestAction) {
        Intent intent = new Intent();
        intent.setAction(requestAction);
        this.sendBroadcast(intent);
    }

    /////////////////////////////////
    // END OF BROADCAST MANAGEMENT //
    /////////////////////////////////

    /**
     * Process called by the communication by the interface to sens an intent to the activity
     * @param action : the action to execute
     */
    @Override
    public void onSendActivityBroadcast(String action) {
        sendActivityRequest(action);
    }
}
