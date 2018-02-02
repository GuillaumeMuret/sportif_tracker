package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

public class ProcessNotifyEndSession implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG,"ProcessNotifyEndSession");
        return AppService.REQUEST_ACTIVITY_PROCESS_NOTIFY_END_SESSION;
    }
}
