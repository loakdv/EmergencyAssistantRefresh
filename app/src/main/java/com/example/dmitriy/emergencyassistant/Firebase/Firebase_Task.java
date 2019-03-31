package com.example.dmitriy.emergencyassistant.Firebase;

public class Firebase_Task {

    public String needy_id;
    public String time;
    public int type;

    public Firebase_Task(String needy_id, String time, int type){
        this.needy_id=needy_id;
        this.time=time;
        this.type=type;
    }

    public Firebase_Task(){}

    public String getNeedy_id() {
        return this.needy_id;
    }


    public String getTime() {
        return this.time;
    }

    public int getType(){return this.type;}
}
