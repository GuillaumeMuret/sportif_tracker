package com.neos.trackandroll.communication.distribution.out;


import com.google.gson.Gson;
import com.neos.trackandroll.communication.message.Message;
import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.communication.protocole.ProcessOut;
import com.neos.trackandroll.utils.LogUtils;

public class ProcessDeleteSaving implements ICommandOut{


    @Override
    public String encode() {
        LogUtils.d(LogUtils.DEBUG_TAG,"ProcessDeleteSaving");
        Message message = new Message(ProcessOut.PROCESS_DELETE_SAVING, new Params());
        Gson gson = new Gson();
        return gson.toJson(message);
    }
}