package com.neos.trackandroll.view.adapter.sensors;


public class SensorItemAdapterInformation {
    /**
     * the number of the player selected + 1, in the list
     */
    private int selectedPlayerPlusOne;

    /**
     * the number of the position sensor selected + 1, in the list
     */
    private int selectedPositionSensorPlusOne;

    /**
     * the number of the heartbeat sensors + 1, in the list
     */
    private int selectedHeartBeatSensorPlusOne;


    public int getSelectedPlayerPlusOne() {
        return selectedPlayerPlusOne;
    }

    public void setSelectedPlayerPlusOne(int selectedPlayerPlusOne) {
        this.selectedPlayerPlusOne = selectedPlayerPlusOne;
    }

    public int getSelectedPositionSensorPlusOne() {
        return selectedPositionSensorPlusOne;
    }

    public void setSelectedPositionSensorPlusOne(int selectedPositionSensorPlusOne) {
        this.selectedPositionSensorPlusOne = selectedPositionSensorPlusOne;
    }

    public int getSelectedHeartBeatSensorPlusOne() {
        return selectedHeartBeatSensorPlusOne;
    }

    public void setSelectedHeartBeatSensorPlusOne(int selectedHeartBeatSensorPlusOne) {
        this.selectedHeartBeatSensorPlusOne = selectedHeartBeatSensorPlusOne;
    }
}
