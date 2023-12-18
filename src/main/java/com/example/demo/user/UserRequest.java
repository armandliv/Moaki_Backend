package com.example.demo.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRequest {
    private User user;
    private String profilePicturePath;

    public UserRequest() {

    }

    public UserRequest(User user, String profilePicturePath) {
        this.user = user;
        this.profilePicturePath = profilePicturePath;
    }

    public User getUser() {
        return user;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
