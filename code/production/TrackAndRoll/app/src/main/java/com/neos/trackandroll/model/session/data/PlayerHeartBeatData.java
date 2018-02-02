package com.neos.trackandroll.model.session.data;

import com.google.gson.annotations.SerializedName;

public class PlayerHeartBeatData {

    @SerializedName("date")
    private String date;

    @SerializedName("bpm")
    private int bpm;

    /**
     * Main constructor of PlayerHeartBeatData
     * @param date
     * @param bpm
     */
    public PlayerHeartBeatData(String date, int bpm) {
        this.date = date;
        this.bpm = bpm;
    }

    /**
     * Method call to get the date
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Method call to set the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method call to get the bpm
     * @return the bpm
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * Method call to set the bpm
     * @param bpm
     */
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
}