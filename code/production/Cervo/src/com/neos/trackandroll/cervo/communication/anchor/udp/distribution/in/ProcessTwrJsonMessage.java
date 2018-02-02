package com.neos.trackandroll.cervo.communication.anchor.udp.distribution.in;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.Brain;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.data.Anchor;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.Tag;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessTwrJsonMessage implements ICommandUdpAnchorIn {

    /**
     * Process called by the distributor to convert the package received by the anchor in UDP
     * @param anchorUdpMessage : the anchor response message
     * @return : the action list for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetActions(AnchorMessage anchorUdpMessage) {
        ArrayList<ActionForBrain> actions = new ArrayList<>();

        // manage mask to have a good id
        anchorUdpMessage.setId(new StringBuilder(anchorUdpMessage.getId()).insert(2, "00").toString());
        LogUtils.udp("UDP ID RECEIVED => " + anchorUdpMessage.getId());

        // Is the id a tag :
        if (DataStore.getInstance().getTagMap().containsKey(anchorUdpMessage.getId())) {
            String tagId = anchorUdpMessage.getId();
            actions.add(new ActionForBrain(ActionForBrain.ACTION_RELAUNCH_WATCHDOG_FOR_TAG, tagId));

            // Set state of the tag to connected and ask to send it to maestro
            if (!DataStore.getInstance().getTagMap().get(tagId).getState().equals(Tag.TagState.CONNECTED)) {
                DataStore.getInstance().getTagMap().get(tagId).setState(Tag.TagState.CONNECTED);
                actions.add(new ActionForBrain(ActionForBrain.ACTION_SEND_TAG_CONNECTED, tagId));
            }

            // Manage the anchor measure
            for (int i = 0; i < anchorUdpMessage.getMeasures().length; i++) {
                String anchorId = anchorUdpMessage.getMeasures()[i].getAnchor();

                if (Brain.getInstance().isSavingData()) {
                    Gson gson = new Gson();
                    actions.add(new ActionForBrain(ActionForBrain.ACTION_FIND_TAG_POSITION, gson.toJson(anchorUdpMessage)));
                }

                // If the anchor is known
                if (DataStore.getInstance().getAnchorMap().containsKey(anchorId)) {
                    if (!DataStore.getInstance().getAnchorMap().get(anchorId).
                            getState().equals(Anchor.AnchorState.CONNECTED)) {
                        DataStore.getInstance().getAnchorMap().get(anchorId).setState(Anchor.AnchorState.CONNECTED);
                    }
                    // Else if the system don't know the anchor
                } else {
                    actions.add(new ActionForBrain(ActionForBrain.ACTION_ASK_TAGS_AND_ANCHORS));
                }
            }
        // Else if the id is an anchor
        } else if (DataStore.getInstance().getAnchorMap().containsKey(anchorUdpMessage.getId())) {
            String anchorId = anchorUdpMessage.getId();
            if (!DataStore.getInstance().getAnchorMap().get(anchorId).getState().equals(Anchor.AnchorState.CONNECTED)) {
                DataStore.getInstance().getAnchorMap().get(anchorId).setState(Anchor.AnchorState.CONNECTED);
            }
        // Else relaunch a list of all
        } else {
            actions.add(new ActionForBrain(ActionForBrain.ACTION_ASK_TAGS_AND_ANCHORS));
        }

        for (String key : DataStore.getInstance().getTagMap().keySet()) {
            LogUtils.udp("TAG IN HASH MAP = " + DataStore.getInstance().getTagMap().get(key).getId());
        }
        for (String key : DataStore.getInstance().getAnchorMap().keySet()) {
            LogUtils.udp("ANCHOR IN HASH MAP = " + DataStore.getInstance().getAnchorMap().get(key).getId());
        }

        return actions;
    }
}
