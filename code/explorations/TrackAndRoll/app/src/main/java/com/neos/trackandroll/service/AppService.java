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
import com.neos.trackandroll.utils.LogUtils;

public class AppService extends Service implements CommunicationEvent {

    public static final String REQUEST_ACTIVITY_ACTION_XXX = "REQUEST_ACTIVITY_ACTION_XXX";
    public static final String REQUEST_ACTIVITY_ACTION_YYY = "REQUEST_ACTIVITY_ACTION_YYY";

    public static final String REQUEST_SERVICE_ACTION_XXX = "REQUEST_SERVICE_ACTION_XXX";

    private AppService.ServiceBroadcastReceiver serviceBroadcastReceiver;

    private boolean isRegistered = false;

    private Communication communication;

    @Override
    public void onCreate() {
        super.onCreate();
        registerBroadcastReceiver();
        this.communication = Communication.getInstance(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }

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

    protected void unregisterBroadcastReceiver() {
        if (serviceBroadcastReceiver != null && isRegistered) {
            isRegistered = false;
            this.unregisterReceiver(serviceBroadcastReceiver);
        }
    }

    protected void registerBroadcastReceiver() {
        if (!isRegistered) {
            isRegistered = true;
            serviceBroadcastReceiver = new ServiceBroadcastReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_ACTION_XXX);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_ACTION_YYY);

            this.registerReceiver(serviceBroadcastReceiver, intentFilter);
        }
    }

    public class ServiceBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.d(LogUtils.DEBUG_TAG, "Broadcast Receive => " + action);
            if(intent.getAction() != null){
                switch (intent.getAction()){
                    case AppService.REQUEST_SERVICE_ACTION_XXX:
                        // TODO
                        break;

                }
            }
        }
    }

    public void sendActivityRequest(String requestAction) {
        Intent intent = new Intent();
        intent.setAction(requestAction);
        this.sendBroadcast(intent);
    }

    /////////////////////////////////
    // END OF BROADCAST MANAGEMENT //
    /////////////////////////////////

    @Override
    public void onSendActivityBroadcast(String action) {
        sendActivityRequest(action);
    }
}
