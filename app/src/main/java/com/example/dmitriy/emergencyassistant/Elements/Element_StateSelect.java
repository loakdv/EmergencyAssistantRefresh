package com.example.dmitriy.emergencyassistant.Elements;

public class Element_StateSelect {


    private String text;
    private int type;


    public Element_StateSelect(String text, int type){
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
