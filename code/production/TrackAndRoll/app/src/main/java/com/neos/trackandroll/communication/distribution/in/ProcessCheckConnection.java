package com.neos.trackandroll.communication.distribution.in;

import com.neos.trackandroll.communication.message.Params;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.service.AppService;
import com.neos.trackandroll.utils.LogUtils;

public class ProcessCheckConnection implements ICommandIn {

    @Override
    public String convertAndGetAction(Params params) {
        LogUtils.d(LogUtils.DEBUG_TAG,"Process check connection");
        return AppService.REQUEST_ACTIVITY_NOTHING;
    }
}
