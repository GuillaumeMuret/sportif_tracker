package com.neos.tcp.server.utils;

import com.neos.tcp.server.model.constant.Constant;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    
    /**
     * Process called to save the game configuration in a json file
     */
    public static void saveGameConfiguration() {
        exportJsonFile(new File(FileUtils.getRootFile(), Constant.CONFIG_FILE_NAME));
    }
    
    /**
     * Process called to generate custom game configuration
     */
    public static void generateCustomGameConfiguration() {
        // useful saveGameConfiguration();
    }
    
    /**
     * Process called to get the root file
     *
     * @return : the root file
     */
    static File getRootFile() {
        File root = new File(Constant.CONFIG_DIRECTORY_NAME);
        boolean directoryCreated = true;
        if (!root.exists()) {
            directoryCreated = root.mkdirs();
        }
        if (directoryCreated) {
            return root;
        } else {
            return null;
        }
    }
    
    /**
     * Process called to export the json file in custom repository
     * @param configFile : the config json file
     */
    private static void exportJsonFile(File configFile) {
        Gson gson = new Gson();
        
        // Java object to JSON, and assign to a String
        // TODO create file String jsonInString = gson.toJson(GameConfig.getInstance());
        String jsonInString = "";
        try {
            FileWriter writer = new FileWriter(configFile);
            
            writer.append(jsonInString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LogUtils.error("exportJsonFile => ",e);
        }
    }
    
    /**
     * Process called to load the current json configuration file
     */
    public static void loadGameConfiguration() {
        File configFile = new File(FileUtils.getRootFile(), Constant.CONFIG_FILE_NAME);
        manageGameConfigFile(configFile);
    }
    
    /**
     * Manage the json file of the app configuration
     *
     * @param configFile : the config file
     */
    private static void manageGameConfigFile(File configFile) {
        Gson gson = new Gson();
        // TODO GameConfig.setInstance(gson.fromJson(new FileReader(configFile), GameConfig.class));
    }
}
