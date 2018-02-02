package com.neos.trackandroll.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.FileUtils;
import com.neos.trackandroll.utils.LogUtils;
import com.neos.trackandroll.view.adapter.sessions.IClickListener;
import com.neos.trackandroll.view.adapter.sessions.RecyclerTouchListener;
import com.neos.trackandroll.view.adapter.sessions.SessionItemAdapter;
import com.neos.trackandroll.view.adapter.sessions.SpinnerSessionArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SessionsManagerActivity extends AbstractActivityWithToolbar {

    private RecyclerView rvSessionItems;
    private SessionItemAdapter sessionItemAdapter;
    private String filterSession;
    private int filterSessionPosition;
    private MaterialSpinner spFilterSession;
    private ArrayList<Player> sessionPlayerList;
    private SpinnerSessionArrayAdapter spinnerAdapter ;
    private Session currentSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.inflateLayout(1,R.layout.activity_sessions_manager,(ViewGroup) findViewById(R.id.activity_session));

        rvSessionItems = (RecyclerView) findViewById(R.id.rvSessionItems);

        if(mapParams!=null && mapParams.get(ServiceActivity.PARAM_SESSION_NAME)!=null){
            filterSession = mapParams.get(ServiceActivity.PARAM_SESSION_NAME);
            filterSessionPosition = getPositionInList(filterSession);
        }else{
            filterSession = Constant.DEFAULT_SESSION_NAME;
            filterSessionPosition = 0;
        }
        sessionPlayerList = getSessionPlayerList(filterSession);


        initSpinnerView();
        initRecyclerView();
    }

    /**
     * method that intializes the SpinnerView
     */
    private void initSpinnerView(){
        // Initializing a String Array
        spinnerAdapter = new SpinnerSessionArrayAdapter(
                this,R.layout.spinner_item,
                DataStore.getInstance().getAppConfiguration().getSessionNameList()
        );
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spFilterSession = (MaterialSpinner) findViewById(R.id.spFilterSession);
        spFilterSession.setAdapter(spinnerAdapter);
        spFilterSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                filterSession = DataStore.getInstance().getAppConfiguration().getSessionNameList().get(position);
                sessionPlayerList = getSessionPlayerList(filterSession);
                LogUtils.d(LogUtils.DEBUG_TAG,"Session player list size = "+sessionPlayerList.size());
                filterSessionPosition = position;
                reinitSessionPlayerList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        spFilterSession.setSelection(filterSessionPosition);
    }

    /**
     * Method that returns the position of the player in the list
     * @param filterSession : the filter session
     * @return the position of the player in the list
     */
    private int getPositionInList(String filterSession){
        ArrayList<String> sessionNameList = DataStore.getInstance().getAppConfiguration().getSessionNameList();
        for(int i=0;i<sessionNameList.size();i++){
            if(sessionNameList.get(i).equals(filterSession)){
                return i;
            }
        }
        return 0;
    }

    /**
     * method that reinit the list of player
     */
    private void reinitSessionPlayerList(){
        sessionItemAdapter.setSessionPlayerList(sessionPlayerList);
        this.currentSession = FileUtils.loadSessionFile(this.filterSession);
        sessionItemAdapter.setFilterSession(filterSession,currentSession);
    }

    private ArrayList<Player> getSessionPlayerList(String filterSession){
        ArrayList<Player> sessionPlayerList = new ArrayList<>();
        ArrayList<Player> allPlayerList = DataStore.getInstance().getPlayerList();
        for(int i = 0;i<allPlayerList.size();i++){
            if(allPlayerList.get(i).getPlayerSessionList().contains(filterSession)){
                sessionPlayerList.add(allPlayerList.get(i));
            }
        }
        return sessionPlayerList;
    }

    /**
     * method that intializes the RecyclerView
     */
    private void initRecyclerView(){
        this.currentSession = FileUtils.loadSessionFile(this.filterSession);

        // Init recycler view adapter
        sessionItemAdapter = new SessionItemAdapter(
                getApplicationContext(),
                sessionPlayerList,
                filterSession,
                this.currentSession
        );

        // Init recycler view
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setStackFromEnd(true);
        rvSessionItems.setAdapter(sessionItemAdapter);
        rvSessionItems.setHasFixedSize(true);
        rvSessionItems.setLayoutManager(llm);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        rvSessionItems.setItemAnimator(itemAnimator);

        rvSessionItems.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvSessionItems, new IClickListener() {
            @Override
            public void onClick(View view, int position, MotionEvent e) {
                HashMap<String,String> mapParams = new HashMap<String, String>();
                if(position>=0 && position < sessionPlayerList.size()){
                    mapParams.put(ServiceActivity.PARAM_PLAYER_KEY, sessionPlayerList.get(position).getPlayerUUID());
                    mapParams.put(ServiceActivity.PARAM_ROOT_SCREEN,String.valueOf(ServiceActivity.DISPLAY_ACTIVITY_SESSIONS_MANAGER));
                    mapParams.put(ServiceActivity.PARAM_SESSION_NAME,filterSession);
                    finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_PLAYER_PROFILE,mapParams);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d("Long Click", "Long Click");
            }
        }));
        rvSessionItems.scrollToPosition(0);
    }

    /**
     * method that removes the session
     */
    @Override
    public void removeSession() {
        DataStore.getInstance().getAppConfiguration().getSessionNameList().remove(filterSession);
        for(int i=0;i<sessionPlayerList.size();i++){
            sessionPlayerList.get(i).getPlayerSessionList().remove(filterSession);
        }
        FileUtils.removeSessionFile(filterSession);

        filterSession = Constant.DEFAULT_SESSION_NAME;
        filterSessionPosition = 0;
        sessionPlayerList = getSessionPlayerList(filterSession);

        initSpinnerView();
        reinitSessionPlayerList();
        FileUtils.saveAppConfiguration(this);
    }

    /**
     * method that export the session
     * @param session : the session
     */
    @Override
    public void exportSession(Session session) {


    }


    /**
     * method that sets the session name
     * @param newSessionName : the new session name
     */
    @Override
    public void setSessionName(String newSessionName) {
        DataStore.getInstance().getAppConfiguration().getSessionNameList().set(filterSessionPosition,newSessionName);
        FileUtils.renameSessionFile(filterSession, newSessionName);

        for(int i=0;i<sessionPlayerList.size();i++){
            sessionPlayerList.get(i).getPlayerSessionList().set(sessionPlayerList.get(i).getPlayerSessionList().indexOf(filterSession), newSessionName);
        }
        filterSession = newSessionName;
        sessionPlayerList = getSessionPlayerList(filterSession);
        TextView textView = (TextView)spFilterSession.getSelectedView();
        textView.setText(newSessionName);

        sessionItemAdapter.notifyDataSetChanged();
        spinnerAdapter.notifyDataSetChanged();

        FileUtils.saveAppConfiguration(this);
    }

    /**
     * method that does an actions in fuction of the button selected
     * @param item : the buttons in the action bar
     * @return : boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete_session:
                if(!filterSession.equals(Constant.DEFAULT_SESSION_NAME)){
                    DialogUtils.displayDialogDeleteSession(this);
                }else{
                    DialogUtils.displayDialogCannotRemoveGlobalSession(this);
                }
                return true;

            case R.id.action_edit_session:
                if(!filterSession.equals(Constant.DEFAULT_SESSION_NAME)){
                    DialogUtils.displayDialogSetNameSession(this,filterSession);
                }else{
                    DialogUtils.displayDialogCannotCustomGlobalSession(this);
                }
                return true;

            case R.id.action_export_csv:
                DialogUtils.displayDialogSureToExportSession(this, currentSession);
                return true;

            case R.id.action_add_session:
                finishWithResult(ServiceActivity.DISPLAY_ACTIVITY_SENSORS_MANAGER);
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
        getMenuInflater().inflate(R.menu.menu_sessions_manager_activity_toolbar, menu);
        return true;
    }
}
