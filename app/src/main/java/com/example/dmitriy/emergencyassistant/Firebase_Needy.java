package com.example.dmitriy.emergencyassistant;

public class Firebase_Needy {


    public String profile_id;


    /*
    0 - родственники и соц. работники
    1 - родственники
    2 - волонтёры/врачи
     */

    //Кому отправлять сигнал SOS
    public int sos_signal;
    //Кому отправлять сигнал Help
    public int help_signal;
    //Кому отправлять данные о состоянии
    public int state_signal;
    //Переменная с информацией о человеке
    public String info;

    // 0 - организация не выбрана
    public int organization;

    public Firebase_Needy(String profile_id, int sos_signal, int help_signal, int state_signal, String info, int organization){
        this.sos_signal=sos_signal;
        this.profile_id=profile_id;
        this.help_signal=help_signal;
        this.state_signal=state_signal;
        this.info=info;
        this.organization=organization;
    }

    public Firebase_Needy(){}

    public String getProfile_id() {
        return this.profile_id;
    }

    public int getSos_signal() {
        return this.sos_signal;
    }

    public int getHelp_signal() {
        return this.help_signal;
    }

    public int getState_signal() {
        return this.state_signal;
    }

    public String getInfo() {
        return this.info;
    }

    public int getOrganization(){
        return this.organization;
    }

}
