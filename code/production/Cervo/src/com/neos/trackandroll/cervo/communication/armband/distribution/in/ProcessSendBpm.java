package com.neos.trackandroll.cervo.communication.armband.distribution.in;

import com.neos.trackandroll.cervo.communication.armband.message.ArmbandMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.data.BeatPerMinute;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.ArmbandSensor;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class ProcessSendBpm implements ICommandArmbandIn {

    public static final int COEFF_OF_THE_BPM_FROM_10_SEC_TO_1_MIN = 6;

    /**
     * Process called by the distributor to convert the process send bpm
     *
     * @param armbandMessage : the armband message
     * @return : the action for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(ArmbandMessage armbandMessage) {
        LogUtils.d("ProcessSendBpm");
        ArrayList<ActionForBrain> actions = new ArrayList<>();

        // Add the sensor to the list
        if (DataStore.getInstance().getHeartBeatSensorMap().containsKey(armbandMessage.getParams().getSensorId())) {
            if (DataStore.getInstance().getHeartBeatSensorMap().get(armbandMessage.getParams().getSensorId()).
                    getSensorState() != ArmbandSensor.ArmbandSensorState.CONNECTED) {
                DataStore.getInstance().getHeartBeatSensorMap().get(armbandMessage.getParams().getSensorId()).setState(
                        ArmbandSensor.ArmbandSensorState.CONNECTED
                );
                LogUtils.d("Connected condition 1");
                actions.add(new ActionForBrain(
                        ActionForBrain.ACTION_SEND_ARMBAND_CONNECTED,
                        armbandMessage.getParams().getSensorId()
                ));
            } else {
                LogUtils.d("No condition");
            }
        } else {
            DataStore.getInstance().getHeartBeatSensorMap().put(
                    armbandMessage.getParams().getSensorId(),
                    new ArmbandSensor(
                            armbandMessage.getParams().getSensorId(),
                            ArmbandSensor.ArmbandSensorState.CONNECTED
                    )
            );
            LogUtils.d("Connected condition 2");
            actions.add(new ActionForBrain(
                    ActionForBrain.ACTION_SEND_ARMBAND_CONNECTED,
                    armbandMessage.getParams().getSensorId()
            ));
        }

        // Save received data
        if (Brain.getInstance().isSavingData()) {
            DataStore.getInstance().getBeatPerMinuteMap().computeIfAbsent(
                    armbandMessage.getParams().getSensorId(), k -> new LinkedList<>());
            DataStore.getInstance().getBeatPerMinuteMap().get(armbandMessage.getParams().getSensorId()).
                    add(new BeatPerMinute(
                            armbandMessage.getParams().getBpmForTenSec() * COEFF_OF_THE_BPM_FROM_10_SEC_TO_1_MIN,
                            armbandMessage.getParams().getSensorId(),
                            Calendar.getInstance().getTime())
                    );

        }

        // Relaunch the watchdog since data received
        actions.add(new ActionForBrain(
                ActionForBrain.ACTION_RELAUNCH_WATCHDOG_FOR_ARMBAND,
                armbandMessage.getParams().getSensorId())
        );
        return actions;
    }
}

