package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.CardioData;
import com.neos.trackandroll.cervo.communication.maestro.message.Data;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.message.PositionData;
import com.neos.trackandroll.cervo.communication.maestro.message.Result;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.AbstractActionForBrain;
import com.neos.trackandroll.cervo.model.data.BeatPerMinute;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.TagPosition;
import com.neos.trackandroll.cervo.utils.DateUtils;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

public class ActionStopSavingAndSendData extends AbstractActionForBrain {

    /**
     * The number of milliseconds in one second
     */
    private static final int MILLISECONDS_IN_ONE_SECOND = 1000;

    /**
     * Main constructor of the action  to stop saving the data
     *
     * @param brain : the brain of the system
     */
    public ActionStopSavingAndSendData(Brain brain) {
        super(brain);
    }

    /**
     * Method called when the action has to be executed
     *
     * @param params : the different params given for the action
     */
    @Override
    public void execute(String[] params) {
        LogUtils.d("ActionStopSavingAndSendData");
        this.brain.setSavingData(false);
        DataStore.getInstance().getSessionTime().setEndSessionTime(Calendar.getInstance());
        this.brain.getMaestroCommunication().getMaestroProxy().sendMessage(
                new MaestroMessage(MaestroProcessOut.PROCESS_NOTIFY_END_SESSION, new Params()));

        Data data = new Data();

        data.setPositionDataMap(getPositionDataMessage());
        data.setCardioDataMap(getCardioDataMessage());

        // Set time for session
        data.setTotalSessionTimeInSec(
                (int) (DataStore.getInstance().getSessionTime().getEndSessionTime().getTimeInMillis()
                        - DataStore.getInstance().getSessionTime().getStartSessionTime().getTimeInMillis())
                        / MILLISECONDS_IN_ONE_SECOND
        );

        Params messageParams = new Params();
        messageParams.setData(data);
        MaestroMessage maestroMessage = new MaestroMessage(MaestroProcessOut.PROCESS_SAVE_SESSION, messageParams);
        DataStore.getInstance().removeAllData();
        brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
    }

    /**
     * Process called to prepare message for maestro with the position data
     *
     * @return : the position data
     */
    private HashMap<String, PositionData> getPositionDataMessage() {
        // Prepare message for maestro (position datas)
        // Here key is the captor id and value is the position data
        HashMap<String, PositionData> positionDataMap = new HashMap<>();
        for (String key : DataStore.getInstance().getTagPositionsMap().keySet()) {
            LinkedList<TagPosition> tagPositionList = DataStore.getInstance().getTagPositionsMap().get(key);
            LinkedList<Result> resultList = new LinkedList<>();

            float totalDistance = 0;

            for (int i = 0; i < tagPositionList.size(); i++) {

                Result result = new Result();
                result.setDate(DateUtils.getStringFromDate(tagPositionList.get(i).getDate()));
                result.setX(tagPositionList.get(i).getX());
                result.setY(tagPositionList.get(i).getY());

                resultList.add(result);
                if (i > 0) {
                    float xPlus1 = tagPositionList.get(i).getX();
                    float xMoins1 = tagPositionList.get(i - 1).getX();

                    float yPlus1 = tagPositionList.get(i).getY();
                    float yMoins1 = tagPositionList.get(i - 1).getY();

                    LogUtils.totalDistance("xPlus1 => " + xPlus1);
                    LogUtils.totalDistance("xMoins1 => " + xMoins1);
                    LogUtils.totalDistance("yPlus1 => " + yPlus1);
                    LogUtils.totalDistance("yMoins1 => " + yMoins1);

                    totalDistance += Math.sqrt(
                            (xPlus1 - xMoins1) * (xPlus1 - xMoins1)
                                    + (yPlus1 - yMoins1) * (yPlus1 - yMoins1)
                    );

                    LogUtils.totalDistance("totalDistance => " + totalDistance);
                }
            }
            PositionData positionData = new PositionData(key, totalDistance, resultList);
            positionDataMap.put(key, positionData);
        }
        return positionDataMap;
    }

    /**
     * Processs called to get the cardio data for sending to maestro
     *
     * @return : the cardio data map
     */
    private HashMap<String, CardioData> getCardioDataMessage() {
        // Prepare message for maestro (cardio datas)
        // Here key is the captor id and value is the cardio data
        HashMap<String, CardioData> cardioDataMap = new HashMap<>();
        for (String key : DataStore.getInstance().getBeatPerMinuteMap().keySet()) {
            LinkedList<BeatPerMinute> beatPerMinuteList = DataStore.getInstance().getBeatPerMinuteMap().get(key);
            LinkedList<Result> resultList = new LinkedList<>();

            int bpmMax = 0;
            for (int i = 0; i < beatPerMinuteList.size(); i++) {

                Result result = new Result();
                result.setDate(DateUtils.getStringFromDate(beatPerMinuteList.get(i).getDate()));
                result.setValeur(String.valueOf(beatPerMinuteList.get(i).getBpmValue()));

                resultList.add(result);
                if (beatPerMinuteList.get(i).getBpmValue() > bpmMax) {
                    bpmMax = beatPerMinuteList.get(i).getBpmValue();
                }
            }
            CardioData cardioData = new CardioData(key, resultList, bpmMax);
            cardioDataMap.put(key, cardioData);
        }
        return cardioDataMap;
    }
}
