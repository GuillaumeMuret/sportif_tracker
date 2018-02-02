package com.neos.trackandroll.cervo.controller.distribution.anchor;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.communication.anchor.message.PreLoadedMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.TagPosition;
import com.neos.trackandroll.cervo.model.localisateur.Circle;
import com.neos.trackandroll.cervo.model.localisateur.Point;
import com.neos.trackandroll.cervo.utils.DateUtils;
import com.neos.trackandroll.cervo.utils.LogUtils;
import com.neos.trackandroll.cervo.utils.PositionFinderUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class ActionFindTagPosition extends AbstractActionForBrain {

    public static final String FILE_DATA_POSITION_LOG = "Data/DataPosition.txt";

    /**
     * Main constructor of the action find tag position (call trilateration algorithm)
     *
     * @param brain : the brain of the system
     */
    public ActionFindTagPosition(Brain brain) {
        super(brain);
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

        LinkedList<Circle> circleList = getCircleList(anchorUdpMessage);

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
            LogUtils.e("Trilateration fatal error point == null");
        }

        if (LogUtils.ACTIVATE_DATA_POSITION) {
            DataPosition dataPosition = new DataPosition();
            dataPosition.circleList = circleList;
            dataPosition.findedPoint = point;
            dataPosition.date = DateUtils.getStringFromDate(new Date());
            try {
                Files.write(Paths.get(FILE_DATA_POSITION_LOG),
                        gson.toJson(dataPosition).getBytes(), StandardOpenOption.APPEND);
                Files.write(Paths.get(FILE_DATA_POSITION_LOG), ",".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                LogUtils.e("Error IOException", e);
                //exception handling left as an exercise for the reader
            }
        }
    }

    public class DataPosition {
        public LinkedList<Circle> circleList;
        public Point findedPoint;
        public String date;
    }

    /**
     * Process called to get the circle list with the udp message
     * @param anchorUdpMessage : the anchor udp message
     * @return : the circle list
     */
    public LinkedList<Circle> getCircleList(AnchorMessage anchorUdpMessage) {
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

            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00BB.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00BB.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00BB.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }

            if (anchorUdpMessage.getMeasures()[i].getAnchor().equals(PreLoadedMessage.SET_POS_0X00DF.getId())) {
                circleList.add(new Circle(
                        new Point(
                                PreLoadedMessage.SET_POS_0X00DF.getCoorX(),
                                PreLoadedMessage.SET_POS_0X00DF.getCoorY()
                        ),
                        anchorUdpMessage.getMeasures()[i].getDistance()
                ));
            }
        }
        return circleList;
    }
}
