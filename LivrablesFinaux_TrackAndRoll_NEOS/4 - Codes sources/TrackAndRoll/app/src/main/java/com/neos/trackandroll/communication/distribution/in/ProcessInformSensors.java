package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

import java.util.ArrayList;

public class ProcessInformSensors implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG,"ProcessInformSensors");

        ArrayList<TrackAndRollSensor> trackAndRollSensorList =
                DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList();

        ArrayList<String> sensorIdList = new ArrayList<>();

        for(int i=0;i<trackAndRollSensorList.size();i++){
            sensorIdList.add(trackAndRollSensorList.get(i).getSensorId());
        }

        for(int i=0;i<params.getCapteurs().length;i++) {
            if (sensorIdList.contains(params.getCapteurs()[i].getCapteurId())) {
                LogUtils.d(LogUtils.DEBUG_TAG,"Sensor is already in list");
                trackAndRollSensorList.get(sensorIdList.indexOf(params.getCapteurs()[i].getCapteurId()))
                        .setSensorState(params.getCapteurs()[i].getSensorState());
            } else {
                LogUtils.d(LogUtils.DEBUG_TAG,"Add new sensor");
                trackAndRollSensorList.add(
                        new TrackAndRollSensor(
                                params.getCapteurs()[i].getCapteurId(),
                                params.getCapteurs()[i].getCapteurType(),
                                params.getCapteurs()[i].getCapteurType() + "-" + params.getCapteurs()[i].getCapteurId(),
                                params.getCapteurs()[i].getSensorState()
                        )
                );
            }
        }
        return AppService.REQUEST_ACTIVITY_PROCESS_INFORM_SENSORS;
    }
}
