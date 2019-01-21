package com.example.dmitriy.emergencyassistant;

public class Added_Note {


    /*
    Класс для списка добавленных заметок
     */

    //Локальная переменная для создания текста заметки
    private  String text;

    private int id;

    public Added_Note(String text, int id){
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
