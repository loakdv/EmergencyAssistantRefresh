package com.example.dmitriy.emergencyassistant.retrofit.pojo.login;

/*
POJO для Firebase
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJONeedy {

    @SerializedName("profile_id")
    @Expose
    public String profile_id;


    /*
    0 - родственники и соц. работники
    1 - родственники
    2 - волонтёры/врачи
     */


    //Кому отправлять сигнал SOS
    @SerializedName("sos_signal")
    @Expose
    public int sos_signal;

    //Кому отправлять сигнал Help
    @SerializedName("help_signal")
    @Expose
    public int help_signal;

    //Кому отправлять данные о состоянии
    @SerializedName("state_signal")
    @Expose
    public int state_signal;

    //Переменная с информацией о человеке
    @SerializedName("info")
    @Expose
    public String info;

    // 0 - организация не выбрана
    @SerializedName("organization")
    @Expose
    public String organization;

    public POJONeedy(String profile_id, int sos_signal, int help_signal, int state_signal, String info, String organization){
        this.sos_signal=sos_signal;
        this.profile_id=profile_id;
        this.help_signal=help_signal;
        this.state_signal=state_signal;
        this.info=info;
        this.organization=organization;
    }

    public POJONeedy(){}

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

    public String getOrganization(){
        return this.organization;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public void setSos_signal(int sos_signal) {
        this.sos_signal = sos_signal;
    }

    public void setHelp_signal(int help_signal) {
        this.help_signal = help_signal;
    }

    public void setState_signal(int state_signal) {
        this.state_signal = state_signal;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
