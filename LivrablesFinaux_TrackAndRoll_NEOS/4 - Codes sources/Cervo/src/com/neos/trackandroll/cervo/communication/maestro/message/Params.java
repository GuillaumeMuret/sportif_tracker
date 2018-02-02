package com.neos.trackandroll.cervo.communication.maestro.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;
import com.neos.trackandroll.cervo.utils.file.position.FileAllTagPositionData;

import java.util.LinkedList;

public class Params {

    /**
     * The sensors list
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_CAPTEURS)
    private LinkedList<Sensor> sensors;

    /**
     * The data of the param
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_DATAS)
    private Data data;

    /**
     * The sensor id
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_ID)
    private String sensorId;

    /**
     * The sensor type
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_SENSOR_TYPE)
    private String sensorType;

    /**
     * The file all tag position data
     */
    @SerializedName(MaestroProtocolVocabulary.JSON_POSITION_TAG_DATA)
    private FileAllTagPositionData fileAllTagPositionData;

    /**
     * Getter of the sensor list
     *
     * @return : the sensor list
     */
    public LinkedList<Sensor> getSensors() {
        return sensors;
    }

    /**
     * Setter of the sensor list
     *
     * @param sensors : the new sensor list
     */
    public void setSensors(LinkedList<Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * Getter of the sensor data
     *
     * @return : the sensor data
     */
    public Data getData() {
        return data;
    }

    /**
     * Setter of the sensor data
     *
     * @param data : the sensor data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Getter of the sensor type
     *
     * @return : the sensor type
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Setter of the sensor type
     *
     * @param sensorType : the sensor type
     */
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Getter of the sensor id
     *
     * @return : the sensor id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Setter of the sensor id
     *
     * @param sensorId : the sensor id
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Getter of the file with all tag position data
     *
     * @return : the file with all tag position data
     */
    public FileAllTagPositionData getFileAllTagPositionData() {
        return fileAllTagPositionData;
    }

    /**
     * Setter of the file all tag position data
     * @param fileAllTagPositionData : the all file position data
     */
    public void setFileAllTagPositionData(FileAllTagPositionData fileAllTagPositionData) {
        this.fileAllTagPositionData = fileAllTagPositionData;
    }
}
