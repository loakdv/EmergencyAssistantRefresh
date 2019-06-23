package com.example.dmitriy.emergencyassistant.models;

public class UserRelation {


    private User user;

    private boolean active;

    public UserRelation() {
    }

    public UserRelation(User user, boolean active) {
        this.user = user;
        this.active = active;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
