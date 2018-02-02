package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionSendTagConnected extends AbstractActionForBrain {

    /**
     * Main construcgtor of the action to send if a tag is connected
     *
     * @param brain : the brain of the system
     */
    public ActionSendTagConnected(Brain brain) {
        super(brain);
    }

    /**
     * Method called to execute the action for brain
     * @param params : the different params given for the action
     */
    public void execute(String[] params) {
        LogUtils.d("Send tag connected => " + params[0]);

        Params maestroMessageParams = new Params();
        maestroMessageParams.setSensorId(params[0]);
        maestroMessageParams.setSensorType(MaestroProtocolVocabulary.CAPTEUR_TYPE_DOSSARD);
        MaestroMessage maestroMessage = new MaestroMessage(
                MaestroProcessOut.PROCESS_NOTIFY_CONNECTION, maestroMessageParams
        );
        brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
    }
}
