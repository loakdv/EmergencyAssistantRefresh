package com.example.dmitriy.emergencyassistant;

public class Firebase_Task {

    private String initials;
    private String id;
    private int type;

    public Firebase_Task(String initials, String id, int type){
        this.initials=initials;
        this.id=id;
        this.type=type;
    }

    public Firebase_Task(){}

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
