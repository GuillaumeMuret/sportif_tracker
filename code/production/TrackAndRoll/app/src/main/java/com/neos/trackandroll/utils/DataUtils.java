package com.neos.trackandroll.utils;

import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.view.adapter.sensors.SensorItemAdapterInformation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class DataUtils {

    /**
     * Method that converts a player list to string player list
     * @param playerList : the player list
     * @return the string player list
     */
    public static ArrayList<String> convertPlayerListToStringPlayerList(ArrayList<Player> playerList) {
        ArrayList<String> stringPlayerList = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {
            stringPlayerList.add(playerList.get(i).getLastName() + "\n" + playerList.get(i).getFirstName());
        }
        stringPlayerList.add(0, Constant.SENSOR_NON_ASSIGNED_NAME);
        return stringPlayerList;
    }

    /**
     * Method that converts the set session to string list
     * @param sessionSet : the session set
     * @return the session list
     */
    public static ArrayList<String> convertSetSessionToStringList(Set<String> sessionSet) {
        ArrayList<String> sessionList = new ArrayList<>();
        for (int i = 0; i < sessionSet.size(); i++) {
            sessionList.add((String) sessionSet.toArray()[i]);
        }
        Collections.sort(sessionList, new Comparator<String>() {
            @Override
            public int compare(String text1, String text2) {
                return text1.compareToIgnoreCase(text2);
            }
        });
        return sessionList;
    }

    public static void initSensorData(){
        try {
            for (int i = 0; i < DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().size(); i++) {
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i).setSensorState(Constant.SENSOR_STATE_DISCONNECTED);
            }
        }catch (NullPointerException npe){
            LogUtils.e(LogUtils.DEBUG_TAG,"NPE");
        }
    }

    /**
     * Method that converts a map to a player list
     * @param playerMap : the player map
     * @return : the player list
     */
    public static ArrayList<Player> convertMapToPlayerList(HashMap<String, Player> playerMap) {
        ArrayList<Player> playerList = new ArrayList<>();
        for (String key : playerMap.keySet()) {
            playerList.add(playerMap.get(key));
        }
        Collections.sort(playerList, new Comparator<Player>() {

            @Override
            public int compare(Player player1, Player player2) {
                return player1.getLastName().compareTo(player2.getLastName());
            }
        });
        DataStore.getInstance().setPlayerList(playerList);
        return playerList;
    }

    /**
     * Method that sort the player list
     * @param playerList : the player list
     * @return the sorted player list
     */
    public static ArrayList<Player> sortPlayerList(ArrayList<Player> playerList) {
        Collections.sort(playerList, new Comparator<Player>() {

            @Override
            public int compare(Player player1, Player player2) {
                return player1.getLastName().compareTo(player2.getLastName());
            }
        });
        return playerList;
    }

    /**
     * Method that gets the selected player position
     * @param playerList : the player list
     * @param playerKey : the player ket
     * @return the position of the selected player
     */
    public static int getSelectedPlayerPosition(ArrayList<Player> playerList, String playerKey) {
        int selectedPlayerPosition = 0;
        if (playerKey.equals(Constant.SENSOR_NON_ASSIGNED_NAME)) {
            selectedPlayerPosition = 0;
        } else {
            for (int i = 0; i < playerList.size() && selectedPlayerPosition == 0; i++) {
                if (playerList.get(i).getLastName().equals(playerKey)) {
                    selectedPlayerPosition = i + 1;
                }
            }
        }
        return selectedPlayerPosition;
    }

    /**
     * Method that gets the default session name
     * @param endInstant : the end instant of the session
     * @return the default session name
     */
    public static String getDefaultSessionName(Calendar endInstant) {
        return "Session_" + DateUtils.convertTimeDefaultSessionTime(endInstant);
    }

    /**
     * Method that convert the sensor list to position sensor list
     * @param sensorList : the sensor list
     * @param positionSensorList : the position sensor list
     * @param positionStringSensorList : the position string sensor list
     * @param heartBeatSensorList : the heart beat sensor list
     * @param heartBeatStringSensorList : the heart beat string sensor list
     */
    public static void convertSensorListToSensorTypeList(
            ArrayList<TrackAndRollSensor> sensorList,
            ArrayList<TrackAndRollSensor> positionSensorList,
            ArrayList<String> positionStringSensorList,
            ArrayList<TrackAndRollSensor> heartBeatSensorList,
            ArrayList<String> heartBeatStringSensorList
            ){
        for(int i=0;i<sensorList.size();i++) {
            if (sensorList.get(i).getSensorType().equals(TrackAndRollSensor.SENSOR_TYPE_POSITION)) {
                if (!positionStringSensorList.contains(sensorList.get(i).getCustomSensorId())) {
                    positionSensorList.add(sensorList.get(i));
                    positionStringSensorList.add(sensorList.get(i).getCustomSensorId());
                }
            }
            if (sensorList.get(i).getSensorType().equals(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)) {
                if (!heartBeatStringSensorList.contains(sensorList.get(i).getCustomSensorId())) {
                    heartBeatSensorList.add(sensorList.get(i));
                    heartBeatStringSensorList.add(sensorList.get(i).getCustomSensorId());
                }
            }
        }
    }

    /**
     * Method that gets the sensor ID by custom ID
     * @param sensorCustomId : the sensor custom ID
     * @return the id
     */
    public static String getSensorIdByCustomId(String sensorCustomId){
        for(int i=0;i<DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().size();i++){
            if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i)
                    .getCustomSensorId().equals(sensorCustomId)){
                return DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i)
                        .getSensorId();
            }
        }
        return "-1";
    }

    /**
     * Method that gets a position sensor in a list by id
     * @param positionSensorId : the position sensor
     * @return the position sensor index in list
     */
    public static int getPositionSensorInListById(String positionSensorId){
        int positionSensorIndexInList = -1;
        for(int i=0;i<DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().size() && positionSensorIndexInList == -1; i++){
            if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i).getSensorType().equals(TrackAndRollSensor.SENSOR_TYPE_POSITION)){
                if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i).getSensorId().equals(positionSensorId)){
                    positionSensorIndexInList = i;
                }
            }
        }
        return positionSensorIndexInList;
    }

    /**
     * Method that gets a heartbeat sensor in a list by id
     * @param heartBeatSensorId : the position sensor
     * @return the heartbeat sensor index in list
     */
    public static int getHeartBeatSensorInListById(String heartBeatSensorId){
        int positionSensorIndexInList = -1;
        for(int i=0;i<DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().size() && positionSensorIndexInList == -1; i++){
            if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i).getSensorType().equals(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)){
                if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().get(i).getSensorId().equals(heartBeatSensorId)){
                    positionSensorIndexInList = i;
                }
            }
        }
        return positionSensorIndexInList;
    }
}
