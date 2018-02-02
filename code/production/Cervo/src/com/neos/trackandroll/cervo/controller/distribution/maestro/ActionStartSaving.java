package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.Calendar;

public class ActionStartSaving extends AbstractActionForBrain {

    /**
     * Main constructor of the action to start saving data
     *
     * @param brain : the brain of the system
     */
    public ActionStartSaving(Brain brain) {
        super(brain);
    }

    /**
     * Method called to execute the action for brain
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionStartSaving");
        this.brain.setSavingData(true);
        DataStore.getInstance().getSessionTime().setStartSessionTime(Calendar.getInstance());
        this.brain.getMaestroCommunication().getMaestroProxy().sendMessage(
                new MaestroMessage(MaestroProcessOut.PROCESS_NOTIFY_START_SESSION,new Params()));
    }
}
