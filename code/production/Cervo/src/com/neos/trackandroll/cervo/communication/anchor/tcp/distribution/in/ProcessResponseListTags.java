package com.neos.trackandroll.cervo.communication.anchor.tcp.distribution.in;

import com.neos.trackandroll.cervo.communication.anchor.message.AnchorMessage;
import com.neos.trackandroll.cervo.controller.distribution.ActionForBrain;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.model.data.Tag;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;

public class ProcessResponseListTags implements ICommandTcpAnchorIn {

    /**
     * Process called by the distributor to convert the process list tags
     *
     * @param anchorResponseMessage : the anchor response message
     * @return : the action for the brain
     */
    @Override
    public ArrayList<ActionForBrain> convertAndGetAction(AnchorMessage anchorResponseMessage) {
        LogUtils.d("Response List Tags");

        ArrayList<ActionForBrain> actions = new ArrayList<>();

        for (int i = 0; i < anchorResponseMessage.getTags().length; i++) {
            String tagId = anchorResponseMessage.getTags()[i].getId();
            if (DataStore.getInstance().getTagMap().get(tagId) == null) {
                LogUtils.d("NEW TAG => " + tagId);
                anchorResponseMessage.getTags()[i].setState(Tag.TagState.UNKNOWN);
                DataStore.getInstance().getTagMap().put(tagId, anchorResponseMessage.getTags()[i]);
            }
        }

        return actions;
    }
}
