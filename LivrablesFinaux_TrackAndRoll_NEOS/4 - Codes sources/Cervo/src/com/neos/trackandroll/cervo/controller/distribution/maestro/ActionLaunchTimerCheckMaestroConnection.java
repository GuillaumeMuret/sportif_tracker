package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.model.constant.Constant;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

public class ActionLaunchTimerCheckMaestroConnection extends AbstractActionForBrain {

    /**
     * Main constructor of the action to launch the timer for check the maestro connection
     *
     * @param brain : the brain of the system
     */
    public ActionLaunchTimerCheckMaestroConnection(Brain brain) {
        super(brain);
    }

    /**
     * Method call to get to launch the timer for check the maestro connection
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionLaunchTimerCheckMaestroConnection");
        scheduleTimerCkeckMaestroConnection();
    }

    /**
     * Process called to schedule the timer for checking the maestro connection
     */
    private void scheduleTimerCkeckMaestroConnection() {
        if (brain.getTimerMaestroConnection() == null) {
            brain.setTimerMaestroConnection(new Timer());
        } else {
            brain.getTimerMaestroConnection().cancel();
            brain.setTimerMaestroConnection(new Timer());
        }
        brain.getTimerMaestroConnection().schedule(
                new ActionLaunchTimerCheckMaestroConnection.TimerCkeckMaestroConnection(),
                Constant.TIME_FOR_CHECK_MAESTRO_CONNECTION
        );
    }

    /**
     * Nested class of the task called after the timer spend
     */
    private class TimerCkeckMaestroConnection extends TimerTask {

        @Override
        public void run() {
            MaestroMessage maestroMessage = new MaestroMessage(MaestroProcessOut.PROCESS_CHECK_CONNECTION, new Params());
            brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
            scheduleTimerCkeckMaestroConnection();
        }
    }
}
