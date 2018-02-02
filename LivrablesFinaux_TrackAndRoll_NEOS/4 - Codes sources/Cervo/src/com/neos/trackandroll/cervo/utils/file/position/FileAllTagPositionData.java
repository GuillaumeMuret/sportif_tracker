package com.neos.trackandroll.cervo.utils.file.position;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;

import java.util.LinkedList;

public class FileAllTagPositionData {

    /**
     * Tge file tag position data list
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_POSITION_DATAS)
    private LinkedList<FileTagPositionData> fileTagPositionDataList;

    /**
     * The file tag position data list
     * @return : the list of the tag position
     */
    public LinkedList<FileTagPositionData> getFileTagPositionDataList() {
        return fileTagPositionDataList;
    }

    /**
     * Setter of the file tag position data
     * @param fileTagPositionDataList : the file where the position are stored
     */
    public void setFileTagPositionDataList(LinkedList<FileTagPositionData> fileTagPositionDataList) {
        this.fileTagPositionDataList = fileTagPositionDataList;
    }

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    /**
     * The total distance
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_TOTAL_DISTANCE)
    private float totalDistance;

    /**
     * Getter of the sensor id
     * @return : the sensor id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor id
     * @param sensorId : the new sensor id
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the total distance
     * @return : the total distance
     */
    public float getTotalDistance() {
        return totalDistance;
    }

    /**
     * Setter of the total distance
     * @param totalDistance : the total distance
     */
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
}
