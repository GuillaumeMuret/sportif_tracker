package com.neos.trackandroll.utils;


import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.session.Session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class CsvUtils{

    public static final String SEPARATOR_COLUMN_CSV = ";";


    /**
     * Method that generates the session name line
     * @param session : the session
     * @return the line to write in file
     */
    private static String generateSessionNameLine(Session session){
        String sessionNameLineToWrite = "";
        sessionNameLineToWrite
                += "Session : "
                + SEPARATOR_COLUMN_CSV
                + session.getSessionName()
                + "\n";
        return sessionNameLineToWrite;
    }

    /**
     * Method that generates the session date line
     * @param session : the session
     * @return the line to write in file
     */
    private static String generateSessionDateLine(Session session){
        String sessionDateLineToWrite = "";
        sessionDateLineToWrite
                += "Date : "
                + SEPARATOR_COLUMN_CSV
                + DateUtils.getStringFromDate(session.getSessionDate())
                + "\n\n";
        return sessionDateLineToWrite;
    }

    /**
     * Method that generates the player informations line
     * @param session : the session
     * @param uuid : the uuid of the player
     * @return the line to write in file
     */
    private static String generatePlayerInfosLine(Session session, String uuid){
        String playerInfosLineToWrite = "";
        playerInfosLineToWrite
                += "Joueur : "
                + SEPARATOR_COLUMN_CSV
                + DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getFirstName()
                + " "
                + DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getLastName()
                + "\n"
                + session.getPlayerSessionDataMap().get(uuid).getSensorBrassardId()
                + SEPARATOR_COLUMN_CSV
                + session.getPlayerSessionDataMap().get(uuid).getSensorDossardId()
                + "\n";
        return playerInfosLineToWrite;
    }

    /**
     * Method that generates the position measure dates line
     * @param session : the session
     * @param uuid : the uuid of the player
     * @return the line to write in file
     */
    private static String generatePositionDateLine(Session session, String uuid){
        String positionDateLineToWrite = "Date : " + SEPARATOR_COLUMN_CSV;
        for(int i=0; i<session.getPlayerSessionDataMap().get(uuid).getPlayerPositionData().size(); i++){
            positionDateLineToWrite
                    += session.getPlayerSessionDataMap().get(uuid).getPlayerPositionData().get(i).getDate()
                    + SEPARATOR_COLUMN_CSV;
        }
        return positionDateLineToWrite;
    }

    /**
     * Method that generates the position value line
     * @param session : the session
     * @param uuid : the uuid of the player
     * @return the line to write in file
     */
    private static String generatePositionLine(Session session, String uuid){
        String positionLineToWrite = "Positions : " + SEPARATOR_COLUMN_CSV;
        for(int i=0; i<session.getPlayerSessionDataMap().get(uuid).getPlayerPositionData().size(); i++){
            positionLineToWrite
                    += session.getPlayerSessionDataMap().get(uuid).getPlayerPositionData().get(i).getPosX()
                    + ","
                    + session.getPlayerSessionDataMap().get(uuid).getPlayerPositionData().get(i).getPosY()
                    + SEPARATOR_COLUMN_CSV;
        }
        return positionLineToWrite;
    }

    /**
     * Method that generates the heartbeat value dates line
     * @param session : the session
     * @param uuid : the uuid of the player
     * @return the line to write in file
     */
    private static String generateHbDateLine(Session session, String uuid){
        String hbDateLineToWrite = "Date : " + SEPARATOR_COLUMN_CSV;
        for(int i=0; i<session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData().size(); i++){
            hbDateLineToWrite
                    += session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData().get(i).getDate()
                    + SEPARATOR_COLUMN_CSV;
        }
        return hbDateLineToWrite;
    }

    /**
     * Method that generates the heartbeat values line
     * @param session : the session
     * @param uuid : the uuid of the player
     * @return the line to write in file
     */
    private static String generateHbLine(Session session, String uuid){
        String hbLineToWrite = "BPM : " + SEPARATOR_COLUMN_CSV;
        for(int i=0; i<session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData().size(); i++){
            hbLineToWrite
                    += session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData().get(i).getBpm()
                    + SEPARATOR_COLUMN_CSV;
        }
        return hbLineToWrite;
    }

    /**
     * Method that generates the CSV file
     * @param session : the session
     */
    public static void generateCsvFile(Session session) {
        try {

            String fileName = "CSV_TrackAndRoll_" + session.getSessionName() + ".csv";
            File dataSessionFile = new File(FileUtils.getRootFile(), fileName);

            dataSessionFile.setReadable(true, false);
            dataSessionFile.setExecutable(true, false);
            dataSessionFile.setWritable(true, false);

            Writer writer = new OutputStreamWriter(
                    new FileOutputStream(dataSessionFile),
                    Charset.forName("UTF-8").newEncoder()
            );

            writer.append(generateSessionNameLine(session));
            writer.append(generateSessionDateLine(session));

            for (String key : session.getPlayerSessionDataMap().keySet()) {
                String uuid = key;//session.getPlayerSessionDataMap().get(String.valueOf(i)).getPlayerUUID();

                writer.append(generatePlayerInfosLine(session, uuid));
                writer.append(generatePositionDateLine(session, uuid)); writer.append("\n");
                writer.append(generatePositionLine(session, uuid)); writer.append("\n");
                writer.append(generateHbDateLine(session, uuid)); writer.append("\n");
                writer.append(generateHbLine(session, uuid)); writer.append("\n");
                writer.append("\n\n");
            }

            writer.flush();
            writer.close();

            LogUtils.d(LogUtils.DEBUG_TAG, "Saved in :" + dataSessionFile);

        }catch(Exception e){
            LogUtils.e(LogUtils.DEBUG_TAG, "Error", e);
        }
    }


}
