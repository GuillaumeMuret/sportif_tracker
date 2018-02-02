package com.neos.trackandroll.utils;

import com.neos.trackandroll.communication.message.CardioData;
import com.neos.trackandroll.communication.message.Data;
import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.communication.message.PositionData;
import com.neos.trackandroll.communication.message.Result;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.model.session.PlayerSessionData;
import com.neos.trackandroll.model.session.data.PlayerHeartBeatData;
import com.neos.trackandroll.model.session.data.PlayerPositionData;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class MockUtils {

    public static final boolean IS_MOCKING = false;

    private static final int TOTAL_MOCK_DATA = 10000;

    /**
     * Method that creates a fake sensor list
     */
    public static void mockSensorList(){
        if(DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().size()<=0){
            for(int i=1;i<6;i++){
                TrackAndRollSensor sensor = new TrackAndRollSensor(
                        String.valueOf(i),
                        TrackAndRollSensor.SENSOR_TYPE_POSITION,
                        "Dossard-"+i,
                        Constant.SENSOR_STATE_CONNECTION_PROGRESS
                );
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().add(sensor);
            }
            for(int i=1;i<6;i++){
                TrackAndRollSensor sensor = new TrackAndRollSensor(
                        String.valueOf(i),
                        TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT,
                        "Brassard-"+i,
                        Constant.SENSOR_STATE_CONNECTION_PROGRESS
                );
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList().add(sensor);
            }
        }
    }

    public static LinkedList<PlayerPositionData> mockPlayerPositionData(){
        LinkedList<PlayerPositionData> positionData = new LinkedList<>();

        // X
        positionData.add(new PlayerPositionData("x",0,0));
        positionData.add(new PlayerPositionData("x",1,0));
        positionData.add(new PlayerPositionData("x",2,0));
        positionData.add(new PlayerPositionData("x",3,0));
        positionData.add(new PlayerPositionData("x",4,0));
        positionData.add(new PlayerPositionData("x",5,0));
        positionData.add(new PlayerPositionData("x",6,0));
        positionData.add(new PlayerPositionData("x",7,0));
        positionData.add(new PlayerPositionData("x",8,0));
        positionData.add(new PlayerPositionData("x",9,0));
        positionData.add(new PlayerPositionData("x",10,0));
        positionData.add(new PlayerPositionData("x",20,0));
        positionData.add(new PlayerPositionData("x",30,0));

        // Y
        positionData.add(new PlayerPositionData("y",0,0));
        positionData.add(new PlayerPositionData("y",0,1));
        positionData.add(new PlayerPositionData("y",0,2));
        positionData.add(new PlayerPositionData("y",0,3));
        positionData.add(new PlayerPositionData("y",0,4));
        positionData.add(new PlayerPositionData("y",0,5));
        positionData.add(new PlayerPositionData("y",0,6));
        positionData.add(new PlayerPositionData("y",0,7));
        positionData.add(new PlayerPositionData("y",0,8));
        positionData.add(new PlayerPositionData("y",0,9));
        positionData.add(new PlayerPositionData("y",0,10));
        positionData.add(new PlayerPositionData("y",0,20));
        positionData.add(new PlayerPositionData("y",0,30));
        return positionData;
    }

    /**
     * Method that creates a fake player list
     */
    public static void mockPlayerList(){
        if(DataStore.getInstance().getAppConfiguration().getPlayerMap().size()==0) {
            Player player1 = new Player("1", "Baptiste", "Bouchut", 20, "Avant", 1, "/storage/sdcard0/DCIM/Camera/player1.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("1", player1);

            Player player2 = new Player("2", "Clément", "Belot", 20, "Avant", 23, "/storage/sdcard0/DCIM/Camera/player2.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("2", player2);

            Player player3 = new Player("3", "Geoffroy", "Tijou", 20, "Coach", 10, "/storage/sdcard0/DCIM/Camera/player3.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("3", player3);

            Player player4 = new Player("4", "Jérémy", "Lapresa", 20, "Avant", 7, "/storage/sdcard0/DCIM/Camera/player4.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("4", player4);

            Player player5 = new Player("5", "Jérôme", "Salley", 20, "Gardien", 21, "/storage/sdcard0/DCIM/Camera/player5.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("5", player5);

            Player player6 = new Player("6", "Karl", "Gabillet", 20, "Avant", 33, "/storage/sdcard0/DCIM/Camera/player6.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("6", player6);

            Player player7 = new Player("7", "Renaud", "Crignier", 20, "Avant", 89, "/storage/sdcard0/DCIM/Camera/player7.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("7", player7);

            Player player8 = new Player("8", "Romain", "Horrut", 20, "Arrière", 4, "/storage/sdcard0/DCIM/Camera/player8.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("8", player8);

            Player player9 = new Player("9", "Roman", "de Preval", 20, "Gardien", 31, "/storage/sdcard0/DCIM/Camera/player9.jpg");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("9", player9);

            Player player10 = new Player("10", "Jean-François", "Ladonne", 20, "avant", 29, "");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("10", player10);

            Player player11 = new Player("11", "Maxime", "Langlois", 20, "Arrière", 18, "");
            DataStore.getInstance().getAppConfiguration().getPlayerMap().put("11", player11);
        }
    }

    /**
     * Method that fakes a running session
     */
    public static void mockRunningSession(){
        // Create data
        HashMap<String, PlayerSessionData> playerSessionDataMap = new HashMap<>();
        PlayerSessionData playerSessionData = new PlayerSessionData();
        Params params = MockUtils.mockParamsForRunningSession();

        for (String uuid : DataStore.getInstance().getRunningSessionSensorMap().keySet()) {

            // Session for position data
            setPlayerPositionData(uuid, params, playerSessionData);

            // Session for heart beat data
            setPlayerHeartBeatData(uuid, params, playerSessionData);

            // Session for time
            setPlayerTimerData(uuid, params, playerSessionData);

            // Update player session data
            playerSessionDataMap.put(uuid, playerSessionData);
        }

        DataStore.getInstance().getRunningSessionEnded().setPlayerSessionDataMap(playerSessionDataMap);
    }

    /**
     * Method that creates fake params from runnning session
     * @return the param
     */
    private static Params mockParamsForRunningSession(){
        LinkedList<String> positionDataIdList = new LinkedList<>();
        LinkedList<String> cardioDataIdList = new LinkedList<>();
        for(String uuid : DataStore.getInstance().getRunningSessionSensorMap().keySet()){
            if(DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION)){
                positionDataIdList.add(DataStore.getInstance().getRunningSessionSensorMap().get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION));
            }
            if(DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION)){
                cardioDataIdList.add(DataStore.getInstance().getRunningSessionSensorMap().get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT));
            }
        }


        Params params = new Params();

        Data data = new Data();

        // Mock position data
        HashMap<String, PositionData> positionDataMap = new HashMap<>();
        for(int i=0;i<positionDataIdList.size();i++){
            PositionData positionData = new PositionData();
            positionData.setCapteurId(positionDataIdList.get(i));

            LinkedList<Result> results = new LinkedList<>();
            for(int j=0;j<TOTAL_MOCK_DATA;j++){
                Result result = new Result();
                result.setDate(new Date().toString());
                result.setX(new Random().nextFloat());
                result.setY(new Random().nextFloat());
                results.add(result);
            }
            positionData.setResults(results);
            positionData.setTotalDistance(new Random().nextFloat()*1000);
            positionDataMap.put(positionDataIdList.get(i),positionData);
        }
        data.setPositionDataMap(positionDataMap);

        // Mock heartBeat data
        HashMap<String, CardioData> cardioDataMap = new HashMap<>();
        for(int i=0;i<cardioDataIdList.size();i++){
            CardioData cardioData = new CardioData();
            cardioData.setCapteurId(cardioDataIdList.get(i));

            LinkedList<Result> results = new LinkedList<>();
            for(int j=0;j<TOTAL_MOCK_DATA;j++){
                Result result = new Result();
                result.setDate(new Date().toString());
                result.setValeur(new Random().nextInt());
                results.add(result);
            }
            cardioData.setResults(results);
            cardioData.setBpmMax(210);
            cardioDataMap.put(cardioDataIdList.get(i),cardioData);
        }
        data.setCardioDataMap(cardioDataMap);
        data.setTotalSessionTimeInSec(new Random().nextInt(10000));

        params.setDatas(data);
        return params;
    }

    /**
     * Method that sets the fake player position datas
     * @param uuid : the uuid of the player
     * @param params : the params of the sesion
     * @param playerSessionData : the data of the player
     */
    private static void setPlayerPositionData(String uuid, Params params, PlayerSessionData playerSessionData) {
        if (DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION)) {

            HashMap<String, PositionData> positionDataMap = params.getDatas().getPositionDataMap();
            LinkedList<PlayerPositionData> playerPositionDataList = new LinkedList<>();

            if(DataStore.getInstance().getRunningSessionSensorMap()
                    .get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION)){
                if(positionDataMap.get(
                        DataStore.getInstance().getRunningSessionSensorMap()
                                .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION)
                )!=null) {
                    LinkedList<Result> results = positionDataMap.get(
                            DataStore.getInstance().getRunningSessionSensorMap()
                                    .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION)
                    ).getResults();
                    for (int i = 0; i < results.size(); i++) {
                        playerPositionDataList.add(
                                new PlayerPositionData(
                                        results.get(i).getDate(),
                                        results.get(i).getX(),
                                        results.get(i).getY()
                                )
                        );
                    }
                    playerSessionData.setPlayerPositionData(playerPositionDataList);
                    playerSessionData.setPlayerTotalDistance(positionDataMap.get(DataStore.getInstance().getRunningSessionSensorMap()
                            .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION)
                    ).getTotalDistance());
                    playerSessionData.setSensorDossardId(DataStore.getInstance().getRunningSessionSensorMap()
                            .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION)
                    );
                }
            }
        }
    }

    /**
     * Method that sets the fake player hertbeat datas
     * @param uuid : the uuid of the player
     * @param params : the params of the sesion
     * @param playerSessionData : the data of the player
     */
    private static void setPlayerHeartBeatData(String uuid, Params params, PlayerSessionData playerSessionData) {
        if (DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)) {
            HashMap<String, CardioData> heartBeatDataMap = params.getDatas().getCardioDataMap();
            LinkedList<PlayerHeartBeatData> playerHeartBeatDataList = new LinkedList<>();

            if(DataStore.getInstance().getRunningSessionSensorMap()
                    .get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)) {
                if (heartBeatDataMap.get(
                        DataStore.getInstance().getRunningSessionSensorMap()
                                .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)) != null) {
                    LinkedList<Result> results = heartBeatDataMap.get(
                            DataStore.getInstance().getRunningSessionSensorMap()
                                    .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)).getResults();
                    for (int i = 0; i < results.size(); i++) {
                        playerHeartBeatDataList.add(
                                new PlayerHeartBeatData(
                                        results.get(i).getDate(),
                                        results.get(i).getValeur()
                                )
                        );
                    }

                    playerSessionData.setPlayerHeartBeatData(playerHeartBeatDataList);
                    playerSessionData.setPlayerBpmMax(heartBeatDataMap.get(DataStore.getInstance().getRunningSessionSensorMap()
                            .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)
                    ).getBpmMax());
                    playerSessionData.setSensorBrassardId(DataStore.getInstance().getRunningSessionSensorMap()
                            .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT));
                }
            }
        }
    }

    /**
     * Method that sets the player timer data
     * @param uuid : the uuid of the player
     * @param params : the params of the sesion
     * @param playerSessionData : the data of the player
     */
    private static void setPlayerTimerData(String uuid, Params params, PlayerSessionData playerSessionData) {
        playerSessionData.setPlayerTotalSessionTimeInSec(params.getDatas().getTotalSessionTimeInSec());
    }
}
