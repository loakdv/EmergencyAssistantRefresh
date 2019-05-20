package com.example.dmitriy.emergencyassistant.Firebase;

/*
POJO для Firebase
 */

public class FirebaseState {

    String needyId;
    int type;
    int percent;

    public FirebaseState(){}

    public FirebaseState(String needyId, int type, int percent){
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
