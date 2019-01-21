package com.example.dmitriy.emergencyassistant;

public class AddedNumber {

    private String number;
    private String name;
    private String id;

    public AddedNumber(String number, String name, String id){
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
