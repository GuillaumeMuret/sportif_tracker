package com.neos.trackandroll.cervo.utils.file.position;

import com.google.gson.Gson;
import com.neos.trackandroll.cervo.communication.maestro.message.MaestroMessage;
import com.neos.trackandroll.cervo.communication.maestro.message.Params;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProcessOut;
import com.neos.trackandroll.cervo.communication.maestro.protocol.MaestroProtocolVocabulary;
import com.neos.trackandroll.cervo.model.DataStore;
import com.neos.trackandroll.cervo.utils.DateUtils;
import com.neos.trackandroll.cervo.utils.FileUtils;
import com.neos.trackandroll.cervo.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PositionFileManager {

    public static final String POSITION_DIRECTORY = "Position";

    /**
     * Process called to create a directory
     * @return : the directory
     */
    private static File createDirectory() {
        File root = FileUtils.getRootFile();
        File subRoot = new File(root.getPath() + "/" + POSITION_DIRECTORY);
        if (!subRoot.exists()) {
            subRoot.mkdirs();
        }
        return subRoot;
    }

    /**
     * Process called to get the file from tag id
     * @param tagId : the tag id
     * @return : the specified file
     */
    private static File getFileFromTagId(String tagId) {
        File root = new File(createDirectory().getPath());
        File toWriteFile = new File(root, tagId);
        return toWriteFile;
    }

    /**
     * Process called to init the file position
     * @param tagId : the tag id
     */
    private static void initFilePosition(String tagId) {
        File toWriteFile = getFileFromTagId(tagId);
        try {
            if (!DataStore.getInstance().getPositionWriterMap().containsKey(tagId)) {
                DataStore.getInstance().getPositionWriterMap().put(
                        tagId,
                        new BufferedWriter(new FileWriter(toWriteFile, true))
                );
            }
            DataStore.getInstance().getPositionWriterMap().get(tagId).append(
                    "{\"" + MaestroProtocolVocabulary.JSON_POSITION_DATAS + "\"[");
        } catch (IOException e) {
            LogUtils.e("IO Exception ", e);
        }
    }

    /**
     * Process called to append in the file
     * @param tagId : the tag id
     * @param fileTagPositionData : the tag file
     */
    public static void appendTextForPosition(String tagId, FileTagPositionData fileTagPositionData) {
        if (!DataStore.getInstance().getPositionWriterMap().containsKey(tagId)) {
            initFilePosition(tagId);
        }
        try {
            Gson gson = new Gson();
            DataStore.getInstance().getPositionWriterMap().get(tagId).append(gson.toJson(fileTagPositionData) + ",");
        } catch (IOException e) {
            LogUtils.e("Error --> ", e);
        }
    }

    /**
     * Process called to close the file position
     * @param tagId the tag id
     * @return : the message to send to maestro
     */
    public static MaestroMessage closeFilePosition(String tagId) {
        Gson gson = new Gson();
        try {
            DataStore.getInstance().getPositionWriterMap().get(tagId).append(gson.toJson(
                    new FileTagPositionData(
                            DateUtils.getStringFromDate(new Date()),
                            0,
                            0)
                    )
                    + "]}"
            );
            return getAllTagPositionData(tagId);
        } catch (IOException e) {
            LogUtils.e("Error -> ", e);
        }
        return null;
    }

    /**
     * Process called to get all position data and send it to maestro
     * @param tagId : the tag id
     * @return : the maestro message
     */
    private static MaestroMessage getAllTagPositionData(String tagId) {
        MaestroMessage maestroMessage = null;
        Gson gson = new Gson();
        try {
            FileAllTagPositionData fileAllTagPositionData = gson.fromJson(
                    new FileReader(getFileFromTagId(tagId)),
                    FileAllTagPositionData.class
            );

            float totalDistance = 0;
            for (int i = 0; i < fileAllTagPositionData.getFileTagPositionDataList().size(); i++) {


                if (i > 0) {
                    float xPlus1 = fileAllTagPositionData.getFileTagPositionDataList().get(i).getX();
                    float xMoins1 = fileAllTagPositionData.getFileTagPositionDataList().get(i - 1).getX();

                    float yPlus1 = fileAllTagPositionData.getFileTagPositionDataList().get(i).getY();
                    float yMoins1 = fileAllTagPositionData.getFileTagPositionDataList().get(i - 1).getY();

                    LogUtils.d("xPlus1 => " + xPlus1);
                    LogUtils.d("xMoins1 => " + xMoins1);
                    LogUtils.d("yPlus1 => " + yPlus1);
                    LogUtils.d("yMoins1 => " + yMoins1);

                    totalDistance += Math.sqrt(
                            (xPlus1 - xMoins1) * (xPlus1 - xMoins1)
                                    + (yPlus1 - yMoins1) * (yPlus1 - yMoins1)
                    );

                    LogUtils.d("totalDistance => " + totalDistance);
                }
            }
            fileAllTagPositionData.setTotalDistance(totalDistance);
            fileAllTagPositionData.setSensorId(tagId);

            Params params = new Params();
            params.setFileAllTagPositionData(fileAllTagPositionData);


            maestroMessage = new MaestroMessage(MaestroProcessOut.POSITION_DATA, params);
        } catch (FileNotFoundException e) {
            LogUtils.e("Error => ", e);
        }
        return maestroMessage;
    }
}
