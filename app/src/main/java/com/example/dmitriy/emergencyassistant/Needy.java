package com.example.dmitriy.emergencyassistant;

public class Needy extends Profile{

    /*
    Почти все перменные относятся к настройкам Needy
     */

    //Переменная отвечает за свитч проверки состояния
    private static boolean checkstate=true;

    /*
    0 - родственники и соц. работники
    1 - родственники
    2 - волонтёры/врачи
     */

    //Кому отправлять сигнал SOS
    private static int signalSOS=0;
    //Кому отправлять сигнал Help
    private static int signalHelp=0;
    //Кому отправлять данные о состоянии
    private static int signalState=0;
    //Переменная с информацией о человеке
    private static String info;


    public static boolean isCheckstate() {
        return checkstate;
    }

    public static void setCheckstate(boolean checkstate) {
        Needy.checkstate = checkstate;
    }

    public static int getSignalSOS() {
        return signalSOS;
    }

    public static void setSignalSOS(int signalSO) {
        Needy.signalSOS = signalSO;
    }

    public static int getSignalHelp() {
        return signalHelp;
    }

    public static void setSignalHelp(int signalHelp) {
        Needy.signalHelp = signalHelp;
    }

    public static int getSignalState() {
        return signalState;
    }

    public static void setSignalState(int signalState) {
        Needy.signalState = signalState;
    }

    public static String getInfo() {
        return info;
    }

    public static void setInfo(String info) {
        Needy.info = info;
    }
}
