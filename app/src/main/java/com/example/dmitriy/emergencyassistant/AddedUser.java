package com.example.dmitriy.emergencyassistant;

public class AddedUser {
    private String id;

    /*
    0 - родственник
    1 - врач
     */
    private int type;

    public AddedUser(String id, int type){
        this.id=id;
        this.type=type;
    }
}
