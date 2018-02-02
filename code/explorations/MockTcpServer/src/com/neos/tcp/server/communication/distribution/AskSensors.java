package com.neos.tcp.server.communication.distribution;

import com.neos.tcp.server.communication.message.Capteur;
import com.neos.tcp.server.communication.protocole.ProcessOut;
import com.neos.tcp.server.communication.proxy.encoder.InformSensors;
import com.neos.tcp.server.controller.Brain;

public class AskSensors implements ICommandFromClient {

    /**
     * Process called to convert params and call manager
     * @param params : the params to decode
     * @param brain : the brain
     */
    @Override
    public void convertMessageAndCallManager(String params, Brain brain) {
        // TODO action brain.getCharacterManager().subscribeNewPirate(port);
        Capteur capteur1 = new Capteur();
        capteur1.setCapteurId(3);
        capteur1.setCapteurType("brassard");


        Capteur capteur2 = new Capteur();
        capteur2.setCapteurId(3);
        capteur2.setCapteurType("dossard");

        Capteur[] capteurs = new Capteur[]{capteur1,capteur2};
        ((InformSensors) brain.getCommunication().getProxyClient().getEncoders().get(ProcessOut.PROXY_CLIENT_INFORM_SENSORS)).sendMessage(capteurs);
    }
}
