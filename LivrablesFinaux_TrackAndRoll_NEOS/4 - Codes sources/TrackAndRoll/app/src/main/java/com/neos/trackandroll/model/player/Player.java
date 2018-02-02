package com.neos.trackandroll.model.player;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.model.constant.Constant;

import java.util.ArrayList;

public class Player {

    @SerializedName("player_uuid")
    private String playerUUID;

    @SerializedName("player_first_name")
    private String firstName;

    @SerializedName("player_last_name")
    private String lastName;

    @SerializedName("player_associated_captor")
    private String associatedCaptor;

    @SerializedName("player_path_photo")
    private String pathPhoto;

    @SerializedName("player_post_name")
    private String postName;

    @SerializedName("player_number")
    private int playerNumber;

    @SerializedName("player_age")
    private int age;

    @SerializedName("player_session_list")
    private ArrayList<String> playerSessionList;

    private boolean selected;

    /**
     * Main constructor of the player
     * @param uuid
     * @param firstName
     * @param lastName
     * @param age
     * @param postName
     * @param playerNumber
     * @param pathPhoto
     */
    public Player(String uuid, String firstName, String lastName, int age, String postName, int playerNumber, String pathPhoto) {
        this.playerUUID = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.postName = postName;
        this.playerNumber = playerNumber;
        this.pathPhoto = pathPhoto;
        this.playerSessionList = new ArrayList<>();
        this.playerSessionList.add(0,Constant.DEFAULT_SESSION_NAME);
        this.selected = false;
    }

    /**
     * Method call to get the player's UUID
     * @return the playerUUID
     */
    public String getPlayerUUID() {
        return playerUUID;
    }

    /**
     * Method call to get the firstname of the player
     * @return the player's firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method call to set the firstname of the player
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method call to get the lastname of the player
     * @return the lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method call to set the lastname of the player
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method call to get the associated captor to a player
     * @return the associatedCaptor
     */
    public String getAssociatedCaptor() {
        return associatedCaptor;
    }

    /**
     * Method call to set a associated captor to a player
     * @param associatedCaptor
     */
    public void setAssociatedCaptor(String associatedCaptor) {
        this.associatedCaptor = associatedCaptor;
    }

    /**
     * Method call to get the users' path photo
     * @return the pathPhoto
     */
    public String getPathPhoto() {
        return pathPhoto == null
                ? ""
                : pathPhoto;
    }

    /**
     * Method call to set the pathPhoto to a player
     * @param pathPhoto
     */
    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    /**
     * Method call to get the postname of a player
     * @return the postname
     */
    public String getPostName() {
        return postName;
    }

    /**
     * Method call to set the postname of the player
     * @param postName
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * Method call to get the player number
     * @return the player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Method call to set the number of the player
     * @param playerNumber
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * Method call to get the age of the player
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Method call to set the age of the player
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method call to return if a player is selected
     * @return the boolean isSelected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * MEthod call to set a player as selected
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Method call to get the list of players in a session
     * @return
     */
    public ArrayList<String> getPlayerSessionList() {
        return playerSessionList;
    }

    public void setPlayerSessionList(ArrayList<String> playerSessionList) {
        this.playerSessionList = playerSessionList;
    }
}
