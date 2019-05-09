package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class Firebase_Task {

    public String needy_id;
    public String time;
    public int type;
    public String date;

    public Firebase_Task(String needy_id, String time, int type, String date){
        this.needy_id=needy_id;
        this.time=time;
        this.type=type;
        this.date=date;
    }

    public Firebase_Task(){}

    public String getNeedy_id() {
        return this.needy_id;
    }

    public String getDate(){return this.date;}

    public String getTime() {
        return this.time;
    }

    public int getType(){return this.type;}
}
