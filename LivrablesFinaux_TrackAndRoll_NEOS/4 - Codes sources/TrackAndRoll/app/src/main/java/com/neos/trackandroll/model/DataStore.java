package com.neos.trackandroll.model;

import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.utils.DataUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DataStore {

    /**
     * The instance of the DataStore
     */
    private static DataStore instance;

    /**
     * Getter of the instance DataStore
     * @return the DataStore instance
     */
    public static DataStore getInstance() {
        if (instance == null) {
            synchronized (DataStore.class) {
                if (instance == null) {
                    instance = new DataStore();
                }
            }
        }
        return instance;
    }

    /**
     * Object ==> PlayerList
     **/
    private AppConfiguration appConfiguration;

    public AppConfiguration getAppConfiguration() {
        return appConfiguration;
    }

    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    /**
     * Object ==> runningSessionSensorMap
     **/
    // First hashMap Key is the player uuid , value HashMap
    // 2sd HashMap key is sensor type and value is sensor id
    private HashMap<String,HashMap<String,String>> runningSessionSensorMap;

    public HashMap<String, HashMap<String, String>> getRunningSessionSensorMap() {
        return runningSessionSensorMap == null
                ? runningSessionSensorMap = new HashMap<>()
                : runningSessionSensorMap;
    }

    public void setRunningSessionSensorMap(HashMap<String, HashMap<String, String>> runningSessionSensorMap) {
        this.runningSessionSensorMap = runningSessionSensorMap;
    }

    /**
     * Object ==> beginSessionTime
     **/
    private Calendar beginSessionTime;

    /**
     * Method call to get the begin session time
     * @return beginSessionTime
     */
    public Calendar getBeginSessionTime() {
        return beginSessionTime;
    }

    /**
     * Method call to set the begin session time
     * @param beginSessionTime
     */
    public void setBeginSessionTime(Calendar beginSessionTime) {
        this.beginSessionTime = beginSessionTime;
    }

    /**
     * Object ==> endSessionTime
     **/
    private Session runningSessionEnded;

    /**
     * Method call get the running session ended
     * @return runningSessionEnded
     */
    public Session getRunningSessionEnded() {
        return runningSessionEnded == null
                ? runningSessionEnded = new Session()
                : runningSessionEnded;
    }

    /**
     * Method call to set the running session ended
     * @param runningSessionEnded
     */
    public void setRunningSessionEnded(Session runningSessionEnded) {
        this.runningSessionEnded = runningSessionEnded;
    }

    /**
     * Object ==> player List
     **/
    private ArrayList<Player> playerList;

    /**
     * Method call to get the player list
     * @return playerList
     */
    public ArrayList<Player> getPlayerList() {
        return playerList == null
                ? playerList = DataUtils.sortPlayerList(DataUtils.convertMapToPlayerList(DataStore.getInstance().getAppConfiguration().getPlayerMap()))
                : DataUtils.sortPlayerList(playerList);
    }

    /**
     * Method call to set the player list
     * @param playerList
     */
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
}
