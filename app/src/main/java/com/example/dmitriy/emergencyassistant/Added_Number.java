package com.example.dmitriy.emergencyassistant;

public class Added_Number {

    /*
    Класс для списка добавленных номеров
     */

    private String number;
    private String name;
    private String id;

    public Added_Number(String number, String name, String id){
        this.number=number;
        this.name=name;
        this.id=id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
