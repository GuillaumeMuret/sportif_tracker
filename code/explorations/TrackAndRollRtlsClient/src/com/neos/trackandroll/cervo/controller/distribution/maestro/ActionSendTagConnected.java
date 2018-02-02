package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.utils.LogUtils;

public class ActionSendTagConnected implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action to send when the tag is connected
     *
     * @param brain : the brain of the system
     */
    public ActionSendTagConnected(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method called to execute the action for brain
     * @param params : the different params given for the action
     */
    public void execute(String[] params) {
        LogUtils.d("Send tag connected => " + params[0]);

        Params maestroMessageParams = new Params();
        maestroMessageParams.setCapteurId(params[0]);
        maestroMessageParams.setCapteurType(MaestroProtocolVocabulary.CAPTEUR_TYPE_DOSSARD);
        MaestroMessage maestroMessage = new MaestroMessage(
                MaestroProcessOut.PROCESS_NOTIFY_CONNECTION, maestroMessageParams
        );
        brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
    }
}
