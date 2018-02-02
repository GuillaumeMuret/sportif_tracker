package com.neos.trackandroll.model.account;

import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.model.constant.Constant;

import java.util.HashMap;

public class Account {
    @SerializedName("player_last_name")
    private String lastName;

    @SerializedName("player_first_name")
    private String firstName;

    @SerializedName("player_username")
    private String username;

    @SerializedName("player_email")
    private String email;

    @SerializedName("player_password")
    private String password;

    @SerializedName("player_path_photo")
    private String pathPhoto;


    /**
     * Main constructor of the account of a user
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param password
     * @param pathPhoto
     */
    public Account(String firstName, String lastName, String username, String email, String password, String pathPhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.pathPhoto = pathPhoto;
    }

    /**
     * Empty constructor of the account of a user
     */
    public Account(){
        this.firstName = Constant.ACCOUNT_FIRST_NAME;
        this.lastName = Constant.ACCOUNT_LAST_NAME;
        this.username = Constant.ACCOUNT_USERNAME;
        this.email = Constant.ACCOUNT_EMAIL;
        this.password = Constant.ACCOUNT_PASSWORD;
        this.pathPhoto = "";
    }

    /**
     * Method call to get the firstname of the user
     * @return the firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method call to set the firstname of the user
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method call to get the lastname of the user
     * @return the lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method call to set the lastname of the user
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method call to get the username of the user
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method call to set the username of the user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method call to get the email address of the user
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method call to set the email address of the user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method call to get the password of the user
     * @return
     */
    public String getPassword() {
        return password;
    }


    /**
     * Method call to set the password of the user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method call to get the users' path photo
     * @return
     */
    public String getPathPhoto() {
        return pathPhoto == null
                ? ""
                : pathPhoto;
    }

    /**
     * Method call to set the users' path photo
     * @param pathPhoto
     */
    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

}
