package com.neos.trackandroll.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.utils.DateUtils;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.FileUtils;
import com.neos.trackandroll.utils.LogUtils;
import com.neos.trackandroll.utils.MockUtils;
import com.neos.trackandroll.view.adapter.sensors.RunningSensorItemAdapter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class RunningSessionActivity extends AbstractActivity {

    private RecyclerView rvSensorItems;
    private RunningSensorItemAdapter sensorItemAdapter;
    private TextView tvSessionTime;
    boolean stopTime;
    private Handler handlerTimer;
    private String newSessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_running_session);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        tvSessionTime = (TextView) findViewById(R.id.tvSessionTime);
        rvSensorItems = (RecyclerView) findViewById(R.id.rvSensorItems);

        initRecyclerView();

        setScreenFromState();
    }

    /**
     * Process called to display the data on screen according to the application state
     */
    private void setScreenFromState(){
        Calendar endSessionCalendar = Calendar.getInstance();
        switch (DataStore.getInstance().getAppConfiguration().getApplicationState()){
            case Constant.APP_STATE_IDLE:
                stopTime = false;
                initTimeThread();
                break;

            case Constant.APP_STATE_SAVING:
                stopTime = true;
                endSessionCalendar.setTime(DataStore.getInstance().getRunningSessionEnded().getSessionDate());
                tvSessionTime.setText(DateUtils.convertTimeToHourMinSecond(DataStore.getInstance().getBeginSessionTime(),endSessionCalendar));
                savingDialog = DialogUtils.displayDialogSavingInProgress(this);
                if(MockUtils.IS_MOCKING){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            generateJsonSessionFile();
                        }
                    },2000);
                }
                break;

            case Constant.APP_STATE_SET_RUNNING_SESSION_NAME:
                stopTime = true;
                endSessionCalendar.setTime(DataStore.getInstance().getRunningSessionEnded().getSessionDate());
                tvSessionTime.setText(DateUtils.convertTimeToHourMinSecond(DataStore.getInstance().getBeginSessionTime(),endSessionCalendar));
                DialogUtils.displayDialogNameRunningSession(this,endSessionCalendar);
                break;

            case Constant.APP_STATE_WAIT_SESSION_NAME:
                onRunningSessionSaved();
                break;
        }
    }

    /**
     * method that says if the process SaveSession is received
     */
    @Override
    public void onProcessSessionSavedReceived() {
        super.onProcessSessionSavedReceived();
        if(DataStore.getInstance().getAppConfiguration().getApplicationState().equals(Constant.APP_STATE_SAVING)){
            onRunningSessionSaved();
        }
    }

    /**
     * method that init the RecyclerView
     */
    private void initRecyclerView() {

        // Init recycler view adapter

        sensorItemAdapter = new RunningSensorItemAdapter(
                getApplicationContext()
        );

        // Init recycler view
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setStackFromEnd(true);
        rvSensorItems.setAdapter(sensorItemAdapter);
        rvSensorItems.setHasFixedSize(true);
        rvSensorItems.setLayoutManager(llm);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        rvSensorItems.setItemAnimator(itemAnimator);
    }


    /**
     * method that does an actions in fuction of the button selected
     * @param item : the buttons in the action bar
     * @return : boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_stop_session:
                DialogUtils.displayDialogEndSession(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * method that creates the menu in the action bar
     * @param menu : the toolbar menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_running_session_activity_toolbar, menu);
        return true;
    }

    /**
     * method that say what to do when back button is pressed
     */
    @Override
    public void onBackPressed() {

    }

    /**
     * method that says if the connection notification is received
     */
    @Override
    public void onProcessNotifyConnectionReceived() {
        super.onProcessNotifyConnectionReceived();
        sensorItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProcessNotifyDisconnectionReceived() {
        super.onProcessNotifyDisconnectionReceived();
        sensorItemAdapter.notifyDataSetChanged();
    }

    /**
     * method that delete the running session
     */
    @Override
    public void deleteRunningSession() {
        super.deleteRunningSession();
        finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_SENSORS_MANAGER);
    }

    /**
     * method that saves and adds the new session
     * @param newSessionName : the new session
     */
    @Override
    public void saveAndAddNewSession(String newSessionName) {
        this.newSessionName = newSessionName;
        DataStore.getInstance().getRunningSessionEnded().setSessionName(newSessionName);

        DataStore.getInstance().getAppConfiguration().getSessionNameList().add(newSessionName);

        LogUtils.d(LogUtils.DEBUG_TAG,"Application state => "+DataStore.getInstance().getAppConfiguration().getApplicationState());

        if(DataStore.getInstance().getAppConfiguration().getApplicationState().equals(Constant.APP_STATE_WAIT_SESSION_NAME)){
            generateJsonSessionFile();
        }else{
            DataStore.getInstance().getAppConfiguration().setApplicationState(Constant.APP_STATE_SAVING);
        }

        LogUtils.d(LogUtils.DEBUG_TAG,"Application state => "+DataStore.getInstance().getAppConfiguration().getApplicationState());
        for(String uuid : DataStore.getInstance().getRunningSessionSensorMap().keySet()){
            DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().add(newSessionName);
        }
        FileUtils.saveAppConfiguration(this);
        setScreenFromState();
    }

    /**
     * method that says when the file is generated
     */
    @Override
    public void onFileGenerated() {
        super.onFileGenerated();
        onRunningSessionSaved();
    }

    /**
     * method that stop the timer of the running session
     */
    @Override
    public void stopRunningSessionTimeForSaving() {
        super.stopRunningSessionTimeForSaving();
        stopTime = true;
        Calendar calendar = Calendar.getInstance();
        DataStore.getInstance().getRunningSessionEnded().setSessionDate(calendar.getTime());
        DataStore.getInstance().getAppConfiguration().setApplicationState(Constant.APP_STATE_SET_RUNNING_SESSION_NAME);
        DialogUtils.displayDialogNameRunningSession(this,calendar);
    }

    /**
     * method that says when the running is saved
     */
    public void onRunningSessionSaved(){
        if(savingDialog != null && savingDialog.isShowing()){
            savingDialog.dismiss();
        }
        mapParams = new HashMap<>();
        mapParams.put(ServiceActivity.PARAM_SESSION_NAME,newSessionName);
        DataStore.getInstance().getAppConfiguration().setApplicationState(Constant.APP_STATE_IDLE);
        FileUtils.saveAppConfiguration(this);
        finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_SESSIONS_MANAGER,mapParams);
    }

    /**
     * Process called to update the time displayed on screen
     */
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            if (!stopTime) {
                tvSessionTime.setText(DateUtils.convertTimeToHourMinSecond(
                        DataStore.getInstance().getBeginSessionTime(),Calendar.getInstance()));
                handlerTimer.postDelayed(timeRunnable,1000);
            }
        }
    };

    /**
     * Process called to init the time thread that update the time displayed on screen
     */
    private void initTimeThread(){
        new SessionTimeThread().start();
        handlerTimer = new Handler();
        handlerTimer.postDelayed(timeRunnable,1000);
    }

    /**
     * Nested class of the thread that update the time on screen
     */
    private class SessionTimeThread extends Thread {

        @Override
        public void run() {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                            public void run() {
                                if(!stopTime) {
                                    new SessionTimeThread().start();
                                }
                            }
                    }
                    ,1000
            );
        }
    }
}
