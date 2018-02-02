package com.neos.trackandroll.model.session;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.model.session.data.PlayerAccelerationData;
import com.neos.trackandroll.model.session.data.PlayerHeartBeatData;
import com.neos.trackandroll.model.session.data.PlayerPositionData;
import com.neos.trackandroll.model.session.data.PlayerSpeedData;

import java.util.LinkedList;

public class PlayerSessionData {

    @SerializedName("player_uuid")
    private String playerUUID;

    @SerializedName("sensor_brassard_id")
    private String sensorBrassardId;

    @SerializedName("sensor_dossard_id")
    private String sensorDossardId;

    @SerializedName("player_positions")
    private LinkedList<PlayerPositionData> playerPositionData;

    @SerializedName("player_speed")
    private LinkedList<PlayerSpeedData> playerSpeedData;

    @SerializedName("player_acceleration")
    private LinkedList<PlayerAccelerationData> playerAccelerationData;

    @SerializedName("player_heart_beat")
    private LinkedList<PlayerHeartBeatData> playerHeartBeatData;

    @SerializedName("player_average_speed")
    private float playerAverageSpeed;

    @SerializedName("player_total_distance")
    private float playerTotalDistance;

    @SerializedName("player_total_session_time")
    private int playerTotalSessionTimeInSec;

    @SerializedName("player_bpm_max")
    private int playerBpmMax;

    @SerializedName("player_energy")
    private int playerEnergy;

    /**
     * Method call to get the UUID player
     * @return the UUID
     */
    public String getPlayerUUID() {
        return playerUUID;
    }

    /**
     * Method call to set the UUID player
     * @param playerUUID
     */
    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    /**
     * Method call to get the sensor brassard id
     * @return the sensor brassard id
     */
    public String getSensorBrassardId() {
        return sensorBrassardId;
    }

    /**
     * Method call to set the sensor brassard id
     * @param sensorBrassardId
     */
    public void setSensorBrassardId(String sensorBrassardId) {
        this.sensorBrassardId = sensorBrassardId;
    }

    /**
     * Method call to get the sensor dossard id
     * @return sensor dossard id
     */
    public String getSensorDossardId() {
        return sensorDossardId;
    }

    /**
     * Method call to set the sensor dossard id
     * @param sensorDossardId
     */
    public void setSensorDossardId(String sensorDossardId) {
        this.sensorDossardId = sensorDossardId;
    }

    /**
     * Method call to get the data position of the players
     * @return player position data linkedlist
     */
    public LinkedList<PlayerPositionData> getPlayerPositionData() {
        return playerPositionData;
    }

    /**
     * Method call to set the player position data linkedlist
     * @param playerPositionData
     */
    public void setPlayerPositionData(LinkedList<PlayerPositionData> playerPositionData) {
        this.playerPositionData = playerPositionData;
    }

    /**
     * Method call to get the data speed of the players
     * @return playerSpeedData
     */
    public LinkedList<PlayerSpeedData> getPlayerSpeedData() {
        return playerSpeedData;
    }

    /**
     * Method call to set the player speed data
     * @param playerSpeedData
     */
    public void setPlayerSpeedData(LinkedList<PlayerSpeedData> playerSpeedData) {
        this.playerSpeedData = playerSpeedData;
    }

    /**
     * Method call to get the player acceleration data linkedlist
     * @return
     */
    public LinkedList<PlayerAccelerationData> getPlayerAccelerationData() {
        return playerAccelerationData;
    }

    /**
     * Method call to set the player acceleration data
     * @param playerAccelerationData
     */
    public void setPlayerAccelerationData(LinkedList<PlayerAccelerationData> playerAccelerationData) {
        this.playerAccelerationData = playerAccelerationData;
    }

    /**
     * Method call to get the player heart beat data linkedlist
     * @return playerHeartBeatData
     */
    public LinkedList<PlayerHeartBeatData> getPlayerHeartBeatData() {
        return playerHeartBeatData;
    }

    /**
     * Method call to set the player heart beat data
     * @param playerHeartBeatData
     */
    public void setPlayerHeartBeatData(LinkedList<PlayerHeartBeatData> playerHeartBeatData) {
        this.playerHeartBeatData = playerHeartBeatData;
    }

    /**
     * Method call to get player average speed
     * @return playerAverageSpeed
     */
    public float getPlayerAverageSpeed() {
        return playerAverageSpeed;
    }

    /**
     * Method call to set the player average speed
     * @param playerAverageSpeed
     */
    public void setPlayerAverageSpeed(float playerAverageSpeed) {
        this.playerAverageSpeed = playerAverageSpeed;
    }

    /**
     * Method call to get player total distance
     * @return playerTotalDistance
     */
    public float getPlayerTotalDistance() {
        return playerTotalDistance;
    }

    /**
     * Method call to set the player total distance
     * @param playerTotalDistance
     */
    public void setPlayerTotalDistance(float playerTotalDistance) {
        this.playerTotalDistance = playerTotalDistance;
    }

    /**
     * Method call to get the player total session in second
     * @return playerTotalSessionTimeInSec
     */
    public int getPlayerTotalSessionTimeInSec() {
        return playerTotalSessionTimeInSec;
    }

    /**
     * Method call to set the player total session data in second
     * @param playerTotalSessionTimeInSec
     */
    public void setPlayerTotalSessionTimeInSec(int playerTotalSessionTimeInSec) {
        this.playerTotalSessionTimeInSec = playerTotalSessionTimeInSec;
    }

    /**
     * Method call to get the player bpm max
     * @return playerBpmMax
     */
    public int getPlayerBpmMax() {
        return playerBpmMax;
    }

    /**
     * Method call to set the player bpm max
     * @param playerBpmMax
     */
    public void setPlayerBpmMax(int playerBpmMax) {
        this.playerBpmMax = playerBpmMax;
    }

    /**
     * Method call to get the player energy
     * @return playerEnergy
     */
    public int getPlayerEnergy() {
        return playerEnergy;
    }

    /**
     * Method call to set the player energy
     * @param playerEnergy
     */
    public void setPlayerEnergy(int playerEnergy) {
        this.playerEnergy = playerEnergy;
    }
}
