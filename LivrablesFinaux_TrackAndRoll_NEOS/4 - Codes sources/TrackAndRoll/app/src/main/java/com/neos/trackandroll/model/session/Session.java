package com.neos.trackandroll.model.session;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.utils.DateUtils;
import com.neos.trackandroll.utils.MockUtils;

import java.util.Date;
import java.util.HashMap;

public class Session {

    /**
     * Main constructor of Session
     * @param defaultName
     */
    public Session(String defaultName){
        this.sessionName = Constant.DEFAULT_SESSION_NAME;
        this.playerSessionDataMap = new HashMap<>();
    }

    /**
     * Empty Constructor of Session
     */
    public Session(){
        this.playerSessionDataMap = new HashMap<>();
    }

    /** Object ==> session date **/
    @SerializedName("session_date")
    private String sessionDate;

    /**
     * Method call to get the session date
     * @return the session date
     */
    public Date getSessionDate() {
        if(MockUtils.IS_MOCKING){
            return new Date();
        }else{
            return DateUtils.getDateFromString(sessionDate,DateUtils.FORMAT_DATE_TRACK_AND_ROLL);
        }
    }

    /**
     * Method call to set the session date
     * @param sessionDate
     */
    public void setSessionDate(Date sessionDate) {
        this.sessionDate = DateUtils.getStringFromDate(sessionDate);
    }

    /** Object ==> session name **/
    @SerializedName("session_name")
    private String sessionName;

    /**
     * Method call tp get the session name
     * @return the sessionName
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * Method call to set the session name
     * @param sessionName
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    /** Object ==> session data **/
    @SerializedName("session_data")
    private HashMap<String, PlayerSessionData> playerSessionDataMap;

    /**
     * Method call to get the player session data map
     * @return playerSessionDataMap
     */
    public HashMap<String, PlayerSessionData> getPlayerSessionDataMap() {
        return playerSessionDataMap;
    }

    /**
     * Method call to set the player session data map
     * @param playerSessionDataMap
     */
    public void setPlayerSessionDataMap(HashMap<String, PlayerSessionData> playerSessionDataMap) {
        this.playerSessionDataMap = playerSessionDataMap;
    }
}
