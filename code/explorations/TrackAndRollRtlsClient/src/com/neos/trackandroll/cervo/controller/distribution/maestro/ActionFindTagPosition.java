package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.message.PreLoadedMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.TagPosition;
import com.neos.trackandroll.cervo.model.localisateur.Circle;
import com.neos.trackandroll.cervo.model.localisateur.Point;
import com.neos.trackandroll.cervo.utils.LogUtils;
import com.neos.trackandroll.cervo.utils.PositionFinderUtils;

import java.util.Calendar;
import java.util.LinkedList;

public class ActionFindTagPosition implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action find tag position (call trilateration algorithm)
     *
     * @param brain : the brain of the system
     */
    public ActionFindTagPosition(Brain brain) {
        this.brain = brain;
    }

    /**
     * Method call to find the best position with the different circles given by the system
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.point("ActionFindTagPosition");

        Gson gson = new Gson();
        AnchorMessage anchorUdpMessage = gson.fromJson(params[0], AnchorMessage.class);
        DataStore.getInstance().getTagPositionsMap().computeIfAbsent(anchorUdpMessage.getId(), k -> new LinkedList<>());

        LinkedList<Circle> circleList = new LinkedList<>();
        for (int i = 0; i < anchorUdpMessage.getMeasures().length; i++) {
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X0108.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X0108.getCoorX(),
                                PreLoadedMessage.SET_POS_0X0108.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00B8.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00B8.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00B8.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00C7.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00C7.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00C7.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00C4.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00C4.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00C4.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00B9.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00B9.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00B9.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00CA.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00CA.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00CA.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
        }

        LogUtils.udp("Circle list size = " + circleList.size());
        for (int i = 0; i < circleList.size(); i++) {
            LogUtils.udp("Circle value " + i + " = " + circleList.get(i).toString());
        }

        Point point = PositionFinderUtils.findBestCoordinate(circleList);
        if (point != null) {
            LogUtils.point("Point =>  " + point.toString());

            DataStore.getInstance().getTagPositionsMap().get(anchorUdpMessage.getId()).add(
                    new TagPosition(
                            Calendar.getInstance().getTime(),
                            point.x,
                            point.y
                    )
            );
        } else {
            LogUtils.udp("No point");
        }
    }
}
