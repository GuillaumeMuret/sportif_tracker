package com.neos.trackandroll.cervo.controller.distribution.maestro;

import com.neos.trackandroll.cervo.communication.maestro.message.CardioData;
import com.neos.trackandroll.cervo.communication.maestro.message.Data;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.message.PositionData;
import com.neos.trackandroll.cervo.communication.maestro.message.Result;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.IActionForBrain;
import com.neos.trackandroll.cervo.model.BeatPerMinute;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.TagPosition;
import com.neos.trackandroll.cervo.utils.DateUtils;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

public class ActionStopSavingAndSendData implements IActionForBrain {

    /**
     * The brain of the system
     */
    private Brain brain;

    /**
     * Main constructor of the action  to stop saving the data
     *
     * @param brain : the brain of the system
     */
    public ActionStopSavingAndSendData(Brain brain) {
        this.brain = brain;
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

                    totalDistance += Math.sqrt(
                            (xPlus1 - xMoins1) * (xPlus1 - xMoins1)
                                    + (yPlus1 - yMoins1) * (yPlus1 - yMoins1)
                    );
                }
            }
            PositionData positionData = new PositionData(key, totalDistance, resultList);
            positionDataMap.put(key, positionData);
        }
        data.setPositionDataMap(positionDataMap);

        // Prepare message for maestro (cardio datas)
        // Here key is the captor id and value is the cardio data
        HashMap<String, CardioData> cardioDataList = new HashMap<>();
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
            cardioDataList.put(key, cardioData);
        }
        data.setCardioDataMap(cardioDataList);

        // Set time for session
        data.setTotalSessionTimeInSec(
                DataStore.getInstance().getSessionTime().getEndSessionTime().get(Calendar.SECOND)
                        - DataStore.getInstance().getSessionTime().getStartSessionTime().get(Calendar.SECOND)
        );

        Params messageParams = new Params();
        messageParams.setData(data);
        MaestroMessage maestroMessage = new MaestroMessage(MaestroProcessOut.PROCESS_SAVE_SESSION, messageParams);
        brain.getMaestroCommunication().getMaestroProxy().sendMessage(maestroMessage);
    }
}
