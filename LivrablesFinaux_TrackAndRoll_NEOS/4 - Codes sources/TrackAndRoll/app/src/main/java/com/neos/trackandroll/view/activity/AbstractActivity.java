package com.neos.trackandroll.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.IFileState;
import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.CsvUtils;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.FileUtils;
import com.neos.trackandroll.utils.LogUtils;
import com.neos.trackandroll.utils.MockUtils;

import java.util.HashMap;

public abstract class AbstractActivity extends AppCompatActivity implements IFileState{

    protected boolean drawerOpen;
    protected HashMap<String, String> mapParams;

    private ServiceBroadcastReceiver serviceBroadcastReceiver;
    private boolean isRegistered = false;

    protected AbstractActivity me;
    protected MaterialDialog savingDialog;


    /**
     * Process called at the creation of the activity that extends this class
     * @param savedInstanceState : the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.me = this;
        registerBroadcastReceiver();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mapParams = new HashMap<>();
            for (String key : extras.keySet()) {
                try {
                    LogUtils.d(LogUtils.DEBUG_TAG, "KEY => " + key + " VALUE => " + extras.get(key));
                    mapParams.put(key, (String) extras.get(key));
                } catch (ClassCastException e) {
                    LogUtils.e(LogUtils.DEBUG_TAG, "Exception in onCreate AbstractActivity => ", e);
                }
            }
        }

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
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_START_SESSION);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_END_SESSION);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_CONNECTION);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_DISCONNECTION);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_INFORM_SENSORS);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_SESSION_SAVED);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_OK);
            intentFilter.addAction(AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_KO);

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

                    case AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_START_SESSION:
                        onProcessNotifyStartSessionReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_END_SESSION:
                        onProcessNotifyEndSessionReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_CONNECTION:
                        onProcessNotifyConnectionReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_DISCONNECTION:
                        onProcessNotifyDisconnectionReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_INFORM_SENSORS:
                        onProcessInformSensorsReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_SESSION_SAVED:
                        onProcessSessionSavedReceived();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_OK:
                        onConnectionOk();
                        break;

                    case AppService.REQUEST_ACTIVITY_PROCESS_CONNECTION_KO:
                        onConnectionFailed();
                        break;
                }
            }
        }
    }

    public void sendServiceRequest(String requestAction) {
        Intent intent = new Intent();
        intent.setAction(requestAction);
        this.sendBroadcast(intent);
        LogUtils.d(LogUtils.DEBUG_TAG,"sendServiceRequest => "+requestAction);
    }

    /////////////////////////////////
    // END OF BROADCAST MANAGEMENT //
    /////////////////////////////////

    /**
     * Process called by the user to finish the activity
     */
    @Override
    public void finish() {
        super.finish();
        unregisterBroadcastReceiver();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        drawerOpen = false;
    }

    /**
     * Process called when an activity will be started. This process override the super
     * class in order to set the animation of the transition
     * @param intent : the intent to change the activity
     * @param requestCode : the request code of the activity created
     */
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * Process called to finish the activity with a result and params
     * @param resultCode : the result code of the activity that will be destroyed
     * @param mapParams : the map params to pass between activities
     */
    protected void finishWithResult(int resultCode, HashMap<String, String> mapParams) {
        Intent intent = new Intent();
        if (mapParams != null) {
            for (String key : mapParams.keySet()) {
                String value = mapParams.get(key);
                intent.putExtra(key, value);
            }
        }
        setResult(resultCode, intent);
        finish();
    }

    /**
     * Process called to finish the activity with a result
     * @param resultCode : the result code of the activity that will be destroyed
     */
    protected void finishWithResult(int resultCode) {
        Intent intent = new Intent();
        setResult(resultCode, intent);
        finish();
    }

    public void displaySessionActivity(){
        finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_SESSIONS_MANAGER, mapParams);
    }

    /**
     * Process called to manage the event when a player is removed of the app
     * @param player : the player to remove
     */
    protected void manageRemovePlayer(Player player) {
        DataStore.getInstance().getAppConfiguration().getPlayerMap().remove(player.getPlayerUUID());
        DataStore.getInstance().getPlayerList().remove(player);
    }

    /**
     * Process called to close the drawer of the activity
     */
    protected void closeDrawer() {

    }

    /**
     * Process called to remove the player of the activity
     */
    public void removePlayer() {

    }

    /**
     * Process called to remove the session of the activity
     */
    public void removeSession() {

    }

    /**
     * Process called to set the session name
     * @param newSessionName : the new session name
     */
    public void setSessionName(String newSessionName) {

    }

    /**
     * Process called to remove the player session
     */
    public void removePlayerSession() {

    }

    /**
     * Process called to delete the running session
     */
    public void deleteRunningSession() {
        if(!MockUtils.IS_MOCKING){
            sendServiceRequest(AppService.REQUEST_SERVICE_PROCESS_DELETE_SAVING);
        }
    }

    /**
     * Process called to stop the running session time
     */
    public void stopRunningSessionTimeForSaving() {
        if(!MockUtils.IS_MOCKING){
            sendServiceRequest(AppService.REQUEST_SERVICE_PROCESS_STOP_SAVING);
        }
    }

    /**
     * Process called to add a new session
     * @param newSessionName : the new session
     */
    public void saveAndAddNewSession(String newSessionName) {
    }

    /**
     * Process called to export the sesion
     * @param session : the new session
     */
    public void exportSession(Session session) {


    }

    /**
     * Process called to quit the application with the right result code
     */
    public void quitApplication() {
        finishWithResult(RESULT_OK);
    }

    /**
     * Process called when the user click on the back of his device
     */
    @Override
    public void onBackPressed() {
        if (drawerOpen) {
            closeDrawer();
        } else {
            DialogUtils.displayDialogSureToQuitApp(this);
        }
    }


    public void onProcessNotifyStartSessionReceived(){

    }

    public void onProcessNotifyEndSessionReceived(){

    }

    public void onProcessNotifyConnectionReceived(){

    }

    public void onProcessNotifyDisconnectionReceived(){

    }

    public void onProcessInformSensorsReceived(){

    }

    public void onProcessSessionSavedReceived(){

    }

    public void onConnectionOk(){

    }

    public void onConnectionFailed(){

    }

    /**
     * Process that calls the Help Activity
     */
    public void startHelpActivity(){
        finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_HELP);
    }

    @Override
    public void onFileGenerated() {
        if(this.savingDialog != null && this.savingDialog.isShowing()){
            this.savingDialog.dismiss();
        }
    }

    protected void generateJsonSessionFile(){
        new ThreadGenerateJsonFile(this).start();
    }

    public void generateCsvSessionFile(Session session){
        new ThreadGenerateCsvFile(this, session).start();
    }

    private class ThreadGenerateJsonFile extends Thread{

        private IFileState iFileState;
        private Session session;

        public ThreadGenerateJsonFile(IFileState iFileState){
            this.iFileState = iFileState;
        }

        @Override
        public void run() {
            super.run();
            if(MockUtils.IS_MOCKING){
                MockUtils.mockRunningSession();
            }
            FileUtils.saveSessionFile(DataStore.getInstance().getRunningSessionEnded());
            iFileState.onFileGenerated();
        }
    }

    private class ThreadGenerateCsvFile extends Thread{

        private IFileState iFileState;
        private Session session;

        public ThreadGenerateCsvFile(IFileState iFileState, Session session){
            this.iFileState = iFileState;
            this.session = session;
        }

        @Override
        public void run() {
            super.run();
            CsvUtils.generateCsvFile(session);
            iFileState.onFileGenerated();
        }
    }

    public void displaySaveInProgress(){
        this.savingDialog = DialogUtils.displayDialogSavingInProgress(this);
        this.savingDialog.show();
    }
}