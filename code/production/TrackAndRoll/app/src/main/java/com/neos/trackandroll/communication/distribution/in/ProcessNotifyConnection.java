package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

import java.util.ArrayList;

public class ProcessNotifyConnection implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG,"ProcessNotifyConnection");

        ArrayList<TrackAndRollSensor> trackAndRollSensorList =
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList();

        ArrayList<String> sensorIdList = new ArrayList<>();

        for(int i=0;i<trackAndRollSensorList.size();i++){
            sensorIdList.add(trackAndRollSensorList.get(i).getCustomSensorId());
        }

        if (sensorIdList.contains(params.getCapteurType() + "-" + params.getCapteurId())) {
            TrackAndRollSensor trackAndRollSensor =
                    trackAndRollSensorList.get(sensorIdList.indexOf(params.getCapteurType() + "-" + params.getCapteurId()));
            trackAndRollSensor.setSensorState(Constant.SENSOR_STATE_CONNECTED);
        } else {
            LogUtils.d(LogUtils.DEBUG_TAG,"Sensor type = "+params.getCapteurType());
            trackAndRollSensorList.add(
                    new TrackAndRollSensor(
                            params.getCapteurId(),
                            params.getCapteurType(),
                            params.getCapteurType() + "-" + params.getCapteurId(),
                            Constant.SENSOR_STATE_CONNECTED)
            );
        }

        return AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_CONNECTION;
    }
}
