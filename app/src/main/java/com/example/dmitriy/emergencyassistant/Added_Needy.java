package com.example.dmitriy.emergencyassistant;

public class Added_Needy {

    /*
    Класс для списка добавленных людей нужд. в помощи
     */

    private String name;
    private String surname;
    private String middlename;
    private String info;
    private String id;

    private int state9;
    private int state12;
    private int state15;
    private int state18;
    private int state21;



    public Added_Needy(String name, String surname, String middlename, String info, String id
                      /*int s9, int s12, int s15, int s18, int s21*/){

        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;

        this.id=id;
        /*
        this.state9=s9;
        this.state12=s12;
        this.state15=s15;
        this.state18=s18;
        this.state21=s21;
        */
    }


    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getInfo() {
        return this.info;
    }

    public String getId() {
        return this.id;
    }
}
