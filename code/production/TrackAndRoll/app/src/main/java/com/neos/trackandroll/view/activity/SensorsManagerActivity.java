package com.neos.trackandroll.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.FileUtils;
import com.neos.trackandroll.utils.LogUtils;
import com.neos.trackandroll.utils.MockUtils;
import com.neos.trackandroll.view.adapter.sensors.SensorItemAdapter;

import java.util.Calendar;

public class SensorsManagerActivity extends AbstractActivityWithToolbar {

    private RecyclerView rvSensorItems;
    private SensorItemAdapter sensorItemAdapter;

    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.inflateLayout(2, R.layout.activity_sensors_manager, (ViewGroup) findViewById(R.id.activity_sensors_manager));

        rvSensorItems = (RecyclerView) findViewById(R.id.rvSensorItems);
        initRecyclerView();

        if(!MockUtils.IS_MOCKING){
            this.dialog = DialogUtils.displayDialogTargetReconnecting(this);
            sendServiceRequest(AppService.REQUEST_SERVICE_CONNECTION_OK);
        }
    }


    /**
     * method that initializes the RecyclerView
     */
    private void initRecyclerView() {

        // Init recycler view adapter
        sensorItemAdapter = new SensorItemAdapter(
                getApplicationContext(),
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList()
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
        rvSensorItems.scrollToPosition(0);
    }

    /**
     * method that prepares the sensors list for the running session
     */
    private void prepareRunningSessionSensorList(){
        DataStore.getInstance().setRunningSessionSensorMap(sensorItemAdapter.convertAdapterInformationToRunningSessionMap());
    }


    /**
     * method that does an actions in fuction of the button selected
     * @param item : the buttons in the action bar
     * @return : boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_launch_session:
                if(!MockUtils.IS_MOCKING){
                    sendServiceRequest(AppService.REQUEST_SERVICE_PROCESS_START_SAVING);
                }
                prepareRunningSessionSensorList();
                DataStore.getInstance().setBeginSessionTime(Calendar.getInstance());
                FileUtils.saveAppConfiguration(this);
                finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_RUNNING_SESSION);
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
        getMenuInflater().inflate(R.menu.menu_sensors_manager_activity_toolbar, menu);
        return true;
    }

    /**
     * methods that says if connection is ok
     */
    @Override
    public void onConnectionOk(){
        dialog.dismiss();
        sendServiceRequest(AppService.REQUEST_SERVICE_PROCESS_ASK_SENSORS);
        LogUtils.d(LogUtils.DEBUG_TAG,"on ConnectionOk");
    }

    /**
     * methods that says if connection failed
     */
    @Override
    public void onConnectionFailed(){

    }

    /**
     * methods that says if the list of sensors is received
     */
    @Override
    public void onProcessInformSensorsReceived() {
        super.onProcessInformSensorsReceived();
        sensorItemAdapter.setSensorList(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList());
        sensorItemAdapter.notifyDataSetChanged();
    }

    /**
     * methods that says if connection is the connection notification is received
     */
    @Override
    public void onProcessNotifyConnectionReceived() {
        super.onProcessNotifyConnectionReceived();
        sensorItemAdapter.setSensorList(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList());
        sensorItemAdapter.notifyDataSetChanged();
    }

    /**
     * methods that says if disconnection is the connection notification is received
     */
    @Override
    public void onProcessNotifyDisconnectionReceived() {
        super.onProcessNotifyDisconnectionReceived();
        sensorItemAdapter.notifyDataSetChanged();
    }
}
