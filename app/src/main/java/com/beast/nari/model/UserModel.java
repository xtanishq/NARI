package com.beast.nari.model;

public class UserModel {

    String userID, userName, profilePic;

    public UserModel(String userID, String userName, String profilePic) {
        this.userID = userID;
        this.userName = userName;
        this.profilePic = profilePic;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
