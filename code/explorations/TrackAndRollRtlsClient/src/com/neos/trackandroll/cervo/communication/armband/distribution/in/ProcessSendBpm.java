package com.neos.trackandroll.cervo.communication.armband.distribution.in;

import com.neos.trackandroll.cervo.communication.armband.message.ArmbandMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.BeatPerMinute;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class ProcessSendBpm implements ICommandBluetoothIn {

    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(ArmbandMessage armbandMessage) {
        LogUtils.d("ProcessSendBpm");
        ArrayList<ActionForBrain> actions = new ArrayList<>();

        if(Brain.getInstance().isSavingData()){
            DataStore.getInstance().getBeatPerMinuteMap().computeIfAbsent(armbandMessage.getParams().getSensorId(), k -> new LinkedList<>());
            DataStore.getInstance().getBeatPerMinuteMap().get(armbandMessage.getParams().getSensorId())
                    .add(new BeatPerMinute(
                        armbandMessage.getParams().getBpmForTenSec()*6,
                        armbandMessage.getParams().getSensorId(),
                        Calendar.getInstance().getTime())
                    );

        }
        actions.add(new ActionForBrain(ActionForBrain.ACTION_RELAUNCH_WATCHDOG_FOR_ARMBAND, armbandMessage.getParams().getSensorId()));
        return actions;
    }
}
