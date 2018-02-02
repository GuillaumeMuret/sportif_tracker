package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.Main;
import com.neos.trackandroll.cervo.communication.maestro.message.Sensor;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.Tag;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.HashMap;

public class ActionSendAllTagsForMaestro implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action  to send all tags for maestro
     *
     * @param brain : the brain of the system
     */
    public ActionSendAllTagsForMaestro(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method called when the action has to be executed
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("Action send all tags");

        Params maestroMessageParams = new Params();
        HashMap<String, Tag> tagHashMap = DataStore.getInstance().getTagMap();
        Sensor[] sensors = new Sensor[tagHashMap.size()];

        int i = 0;
        for (String tagId : tagHashMap.keySet()) {
            sensors[i] = new Sensor(tagId, MaestroProtocolVocabulary.CAPTEUR_TYPE_DOSSARD);
        }
        maestroMessageParams.setSensors(sensors);
        MaestroMessage maestroMessage = new MaestroMessage(MaestroProcessOut.PROCESS_INFORM_SENSORS, maestroMessageParams);

        if (Main.IS_MOCKING) {
            brain.getMaestroCommunication().getMaestroPostman().writeMessage(
                    "{ \"process\" : \"informSensors\", \"params\" : { \"sensors\" : [ { \"capteurID\" : \"1\", "
                            + "\"capteurType\" : \"brassard\" }, { \"capteurID\" : \"2\", \"capteurType\" : \"brassard\""
                            + " }, { \"capteurID\" : \"3\", \"capteurType\" : \"brassard\" }, { \"capteurID\" : \"1\","
                            + " \"capteurType\" : \"dossard\" }, { \"capteurID\" : \"3\", \"capteurType\" : \"dossard\" "
                            + "} ] } }");
        } else {
            brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
        }
    }
}
