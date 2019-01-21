package com.example.dmitriy.emergencyassistant;

public class Added_User {


    /*
    Класс для списка добавленных пользователей из
     */

    private String id;

    /*
    0 - родственник
    1 - врач
     */
    private int type;

    public Added_User(String id, int type){
        this.id=id;
        this.type=type;
    }
}
