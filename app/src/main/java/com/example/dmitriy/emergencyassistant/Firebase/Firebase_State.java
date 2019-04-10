package com.example.dmitriy.emergencyassistant.Firebase;

public class Firebase_State {

    String needyId;
    int type;
    int percent;

    public Firebase_State(){}

    public Firebase_State(String needyId, int type, int percent){
        this.needyId=needyId;
        this.type=type;
        this.percent=percent;
    }

    public String getNeedyId() {
        return this.needyId;
    }

    public int getType() {
        return this.type;
    }

    public int getPercent() {
        return this.percent;
    }
}
