package com.example.dmitriy.emergencyassistant.elements;

/*
Эдемент который необходим для сбора данных о состоянии
Он содержит себе текст который будет отображаться на экране
А так же тип, благодаря которому будет определено состояние пользователя
 */
public class ElementStateSelect {


    private String text;
    private int type;


    public ElementStateSelect(String text, int type){
        this.text=text;
        this.type=type;
    }


    public String getText(){
        return this.text;
    }


    public int getType(){
        return this.type;
    }
}
