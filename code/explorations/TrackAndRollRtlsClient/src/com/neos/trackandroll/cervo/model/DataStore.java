package com.neos.trackandroll.cervo.model;

import java.util.HashMap;
import java.util.LinkedList;

public final class DataStore {

    /**
     * Singleton management
     */
    private static DataStore instance;

    /**
     * Private constructor of the instance
     */
    private DataStore() {
    }

    /**
     * Getter of the instance Brain
     *
     * @return the instance Brain
     */
    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    /**
     * The object list tags
     */
    private HashMap<String, Tag> tagMap;

    /**
     * Getter of the tag map
     * @return : the tag map
     */
    public HashMap<String, Tag> getTagMap() {
        return tagMap == null
                ? tagMap = new HashMap<>()
                : tagMap;
    }

    /**
     * Setter of the tag map
     * @param tagMap the tag map
     */
    public void setTagMap(HashMap<String, Tag> tagMap) {
        this.tagMap = tagMap;
    }

    /**
     * The object list bpm
     */
    private HashMap<String, LinkedList<BeatPerMinute>> beatPerMinuteMap;

    /**
     * Getter of the beat per minute map
     * @return : the bpm map
     */
    public HashMap<String, LinkedList<BeatPerMinute>> getBeatPerMinuteMap() {
        return beatPerMinuteMap == null
                ? beatPerMinuteMap = new HashMap<>()
                : beatPerMinuteMap;
    }

    /**
     * Setter of the bpm map
     * @param beatPerMinuteMap : the bpm map
     */
    public void setBeatPerMinuteMap(HashMap<String, LinkedList<BeatPerMinute>> beatPerMinuteMap) {
        this.beatPerMinuteMap = beatPerMinuteMap;
    }


    /**
     * The object list anchors
     */
    private HashMap<String, Anchor> anchorMap;

    /**
     * Getter of the anchor map
     * @return : the anchor map
     */
    public HashMap<String, Anchor> getAnchorMap() {
        return anchorMap == null
                ? anchorMap = new HashMap<>()
                : anchorMap;
    }

    /**
     * Setter of the anchor map
     * @param anchorMap : the anchor map
     */
    public void setAnchorMap(HashMap<String, Anchor> anchorMap) {
        this.anchorMap = anchorMap;
    }


    /**
     * The object tag positions map
     */
    private HashMap<String, LinkedList<TagPosition>> tagPositionsMap;

    /**
     * Getter of the tag position map
     * @return : the tag position map
     */
    public HashMap<String, LinkedList<TagPosition>> getTagPositionsMap() {
        return tagPositionsMap == null
                ? tagPositionsMap = new HashMap<>()
                : tagPositionsMap;
    }

    /**
     * Setter of the tag position map
     * @param tagPositionsMap : the tag position map
     */
    public void setTagPositionsMap(HashMap<String, LinkedList<TagPosition>> tagPositionsMap) {
        this.tagPositionsMap = tagPositionsMap;
    }

    /**
     * The object session time
     */
    private SessionTime sessionTime;

    /**
     * Getter of the session time object
     * @return : the session time object
     */
    public SessionTime getSessionTime() {
        return sessionTime == null
                ? sessionTime = new SessionTime()
                : sessionTime;
    }

    /**
     * Setter of the session time object
     * @param sessionTime : the session time
     */
    public void setSessionTime(SessionTime sessionTime) {
        this.sessionTime = sessionTime;
    }
}
