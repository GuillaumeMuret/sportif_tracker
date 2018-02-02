package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

public class ProcessNotifyStartSession implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG,"ProcessNotifyStartSession");
        DataStore.getInstance().getAppConfiguration().setApplicationState(Constant.APP_STATE_RUNNING_SESSION);
        return AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_START_SESSION;
    }
}
