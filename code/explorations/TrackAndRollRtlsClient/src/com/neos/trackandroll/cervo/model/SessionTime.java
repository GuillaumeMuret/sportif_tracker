package com.neos.trackandroll.cervo.model;

import java.util.Calendar;

public class SessionTime {

    private Calendar startSessionTime;
    private Calendar endSessionTime;

    public Calendar getStartSessionTime() {
        return startSessionTime;
    }

    public void setStartSessionTime(Calendar startSessionTime) {
        this.startSessionTime = startSessionTime;
    }

    public Calendar getEndSessionTime() {
        return endSessionTime;
    }

    public void setEndSessionTime(Calendar endSessionTime) {
        this.endSessionTime = endSessionTime;
    }
}
