package com.neos.trackandroll.cervo.model.data;

import java.util.Calendar;

public class SessionTime {

    /**
     * The start session time
     */
    private Calendar startSessionTime;

    /**
     * The end session time
     */
    private Calendar endSessionTime;

    /**
     * Getter of the start session time
     * @return : the start session time
     */
    public Calendar getStartSessionTime() {
        return startSessionTime;
    }

    /**
     * Setter of the start session time
     * @param startSessionTime : the start session time
     */
    public void setStartSessionTime(Calendar startSessionTime) {
        this.startSessionTime = startSessionTime;
    }

    /**
     * Getter of the end session time
     * @return : the end session time
     */
    public Calendar getEndSessionTime() {
        return endSessionTime;
    }

    /**
     * Setter of the end session time
     * @param endSessionTime : the end session time
     */
    public void setEndSessionTime(Calendar endSessionTime) {
        this.endSessionTime = endSessionTime;
    }
}
