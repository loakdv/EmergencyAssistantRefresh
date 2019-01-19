package com.example.dmitriy.emergencyassistant;

public class Note {


    //Локальная переменная для создания текста заметки
    private  String text;

    private int id;

    public Note(String text, int id){
        this.id=id;
        this.text=text;
    }

    public String getText(){
        return this.text;
    }

    public  int getId(){
        return this.id;
    }
}
