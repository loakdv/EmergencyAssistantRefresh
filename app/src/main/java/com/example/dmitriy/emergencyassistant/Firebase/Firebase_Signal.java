package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class Firebase_Signal {

    private String initials;
    private String id;
    private int type;

    public Firebase_Signal(String initials, String id, int type){
        this.initials=initials;
        this.id=id;
        this.type=type;
    }

    public Firebase_Signal(){}

    public String getInitials() {
        return this.initials;
    }

    public String getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }
}
