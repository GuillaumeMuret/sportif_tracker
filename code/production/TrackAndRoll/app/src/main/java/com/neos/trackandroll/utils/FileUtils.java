package com.neos.trackandroll.utils;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.AppConfiguration;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.session.PlayerSessionData;
import com.neos.trackandroll.model.session.Session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class FileUtils {

    private static final String JSON_EXTENSION = ".json";
    private static final String JSON_NAME_FILE = "TrackAndRollConfiguration.json";
    private static final String NAME_DIRECTORY = "TrackAndRollData";

    /**
     * Method that save the session file
     * @param session : the session
     */
    public static void saveSessionFile(Session session){
        saveInCustomRepo(session.getSessionName()+JSON_EXTENSION, session);
        // TODO
        //manageGlobalSession();
    }

    /**
     * Method that removes the session file
     * @param filterSession : the filter session
     */
    public static void removeSessionFile(String filterSession){
        removeSessionInCustomRepo(filterSession+JSON_EXTENSION);
    }

    /**
     * Method that save the app configuration
     * @param context : the context
     */
    public static void saveAppConfiguration(Context context){
        saveInEnvironmentData(context, DataStore.getInstance().getAppConfiguration());
        saveInCustomRepo(JSON_NAME_FILE, DataStore.getInstance().getAppConfiguration());
    }

    /**
     * Method that saves the environment data
     * @param context : the context
     * @param o : the object
     */
    private static void saveInEnvironmentData(Context context, Object o){
        exportJsonFile(new File((context.getFileStreamPath(JSON_NAME_FILE).getPath())), o);
    }

    /**
     * Method that saves the object in a repository
     * @param fileName : the file name
     * @param o : the object
     */
    private static void saveInCustomRepo(String fileName, Object o){
        exportJsonFile(new File(FileUtils.getRootFile(), fileName), o);
    }

    /**
     * Method that remove session in repository
     * @param fileName : the filename
     */
    private static void removeSessionInCustomRepo(String fileName){
        File file = new File(FileUtils.getRootFile(), fileName);
        boolean deleted = file.delete();
    }

    /**
     * Process called to get the root file
     * @return : the root file
     */
    static File getRootFile(){
        File root = new File(Environment.getExternalStorageDirectory(), NAME_DIRECTORY);
        if (!root.exists()) {
            root.mkdirs();
        }
        return root;
    }

    /**
     * Method that exports the Json file
     * @param configFile : the config file
     * @param o : the object to export
     */
    private static void exportJsonFile(File configFile, Object o){
        Gson gson = new Gson();

        // Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(o);

        try {
            FileWriter writer = new FileWriter(configFile);

            writer.append(jsonInString);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            LogUtils.e(LogUtils.DEBUG_TAG,"Exception export json file",e);
        }
    }

    /**
     * Process called to load the current json configuration file
     */
    public static void loadAppConfiguration(Context context){
        File configFile = new File((context.getFileStreamPath(JSON_NAME_FILE).getPath()));
        manageAppConfigurationFileJSON(configFile);
    }

    /**
     * Method that load a session file
     * @param fileName : the session file
     * @return the session
     */
    public static Session loadSessionFile(String fileName){
        Session session = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(FileUtils.getRootFile(), fileName+JSON_EXTENSION).getPath()));
            Gson gson = new Gson();
            session = gson.fromJson(bufferedReader, Session.class);
        } catch (FileNotFoundException e) {
            LogUtils.e(LogUtils.DEBUG_TAG, "Error file not found", e);
        }
        LogUtils.d(LogUtils.DEBUG_TAG,"Session == null ? => "+String.valueOf(session==null));
        return session;
    }

    /**
     * Manage the json file of the app configuration 
     * @param configFile : the config file
     */
    private static void manageAppConfigurationFileJSON(File configFile){
        Gson gson = new Gson();
        try {
            DataStore.getInstance().setAppConfiguration(gson.fromJson(new FileReader(configFile), AppConfiguration.class));
        } catch(Exception e){
            DataStore.getInstance().setAppConfiguration(new AppConfiguration());
            LogUtils.e(LogUtils.DEBUG_TAG,"FileNotFoundException => ",e);
        }
    }

    /**
     * Method that renames the session file
     * @param fromFile : the previous name
     * @param newFile : the new name
     */
    public static void renameSessionFile(String fromFile, String newFile) {
        File dir = getRootFile();
        if (dir.exists()) {
            File from = new File(dir, fromFile + JSON_EXTENSION);
            File to = new File(dir, newFile + JSON_EXTENSION);
            if (from.exists()) {
                from.renameTo(to);
            }
        }
    }

    /**
     * Method that manages the global session
     */
    private static void manageGlobalSession(){
        Session sessionGlobal = new Session(Constant.DEFAULT_SESSION_NAME);
        HashMap<String,PlayerSessionData> playerSessionDataMap = new HashMap<>();
        for(String uuid : DataStore.getInstance().getAppConfiguration().getPlayerMap().keySet()){
            PlayerSessionData playerSessionData = new PlayerSessionData();
            if(DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().size()>1) {
                for (int i = 1; i < DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().size(); i++) {
                    Session sessionX = loadSessionFile(DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().get(i));
                    playerSessionData.setPlayerBpmMax(playerSessionData.getPlayerBpmMax() + sessionX.getPlayerSessionDataMap().get(uuid).getPlayerBpmMax());
                    playerSessionData.setPlayerTotalDistance(playerSessionData.getPlayerTotalDistance() + sessionX.getPlayerSessionDataMap().get(uuid).getPlayerTotalDistance());
                    playerSessionData.setPlayerTotalSessionTimeInSec(playerSessionData.getPlayerTotalSessionTimeInSec() + sessionX.getPlayerSessionDataMap().get(uuid).getPlayerTotalSessionTimeInSec());
                }
                playerSessionData.setPlayerBpmMax(playerSessionData.getPlayerBpmMax() /
                        DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().size()-1
                );
                playerSessionData.setPlayerTotalDistance(playerSessionData.getPlayerTotalDistance() /
                        DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().size()-1
                );
                playerSessionData.setPlayerTotalSessionTimeInSec(playerSessionData.getPlayerTotalSessionTimeInSec() /
                        DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuid).getPlayerSessionList().size()-1
                );
                playerSessionDataMap.put(uuid, playerSessionData);
            }
        }
        sessionGlobal.setPlayerSessionDataMap(playerSessionDataMap);
        exportJsonFile(new File(FileUtils.getRootFile(), Constant.DEFAULT_SESSION_NAME+JSON_EXTENSION), sessionGlobal);
    }
}
