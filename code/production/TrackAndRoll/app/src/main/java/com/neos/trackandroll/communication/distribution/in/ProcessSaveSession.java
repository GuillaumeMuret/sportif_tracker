package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.CardioData;
import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.communication.message.PositionData;
import com.neos.trackandroll.communication.message.Result;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.model.session.PlayerSessionData;
import com.neos.trackandroll.model.session.data.PlayerHeartBeatData;
import com.neos.trackandroll.model.session.data.PlayerPositionData;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;
import com.neos.trackandroll.utils.FileUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ProcessSaveSession implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG, "ProcessSaveSession");

        // Create data
        HashMap<String, PlayerSessionData> playerSessionDataMap = new HashMap<>();

        for (String uuid : DataStore.getInstance().getRunningSessionSensorMap().keySet()) {

            PlayerSessionData playerSessionData = new PlayerSessionData();

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

        LogUtils.d(LogUtils.DEBUG_TAG,"Application state = "+DataStore.getInstance().getAppConfiguration().getApplicationState());
        if(DataStore.getInstance().getAppConfiguration().getApplicationState().equals(Constant.APP_STATE_SAVING)){
            FileUtils.saveSessionFile(DataStore.getInstance().getRunningSessionEnded());
        }else {
            DataStore.getInstance().getAppConfiguration().setApplicationState(Constant.APP_STATE_WAIT_SESSION_NAME);
        }

        return AppService.REQUEST_ACTIVITY_PROCESS_SESSION_SAVED;
    }

    private void setPlayerPositionData(String uuid, Params params, PlayerSessionData playerSessionData) {
        if (DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION)) {

            HashMap<String, PositionData> positionDataMap = params.getDatas().getPositionDataMap();
            LinkedList<PlayerPositionData> playerPositionDataList = new LinkedList<>();

            try {
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
            }catch (NullPointerException npe){
                LogUtils.e(LogUtils.DEBUG_TAG,"Null pointer exception setPlayerPositionData ",npe);
            }
            playerSessionData.setPlayerPositionData(playerPositionDataList);
            playerSessionData.setPlayerTotalDistance(positionDataMap.get(DataStore.getInstance().getRunningSessionSensorMap()
                    .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION)
            ).getTotalDistance());
            playerSessionData.setSensorDossardId(DataStore.getInstance().getRunningSessionSensorMap()
                    .get(uuid).get(TrackAndRollSensor.SENSOR_TYPE_POSITION));
        }
    }

    private void setPlayerHeartBeatData(String uuid, Params params, PlayerSessionData playerSessionData) {
        if (DataStore.getInstance().getRunningSessionSensorMap().get(uuid).containsKey(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)) {
            HashMap<String, CardioData> heartBeatDataMap = params.getDatas().getCardioDataMap();
            LinkedList<PlayerHeartBeatData> playerHeartBeatDataList = new LinkedList<>();

            try {
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
            }catch (NullPointerException npe){
                LogUtils.e(LogUtils.DEBUG_TAG,"Null poitner exception setPlayer heart beat data",npe);
            }
        }
    }

    private static void setPlayerTimerData(String uuid, Params params, PlayerSessionData playerSessionData) {
        playerSessionData.setPlayerTotalSessionTimeInSec(params.getDatas().getTotalSessionTimeInSec());
    }
}