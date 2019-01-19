package com.example.dmitriy.emergencyassistant;

public class Profile {
    private static boolean logged=true;
    /*
    0 - Needy
    1 - Relative
    2 - Doctor
    3 - Volunteer
     */
    private static int type=0;
    private static String surname;
    private static String name;
    private static String middlename;
    private static int phonenumber;

    private static boolean doctor;

    public static boolean isDoctor() {
        return doctor;
    }

    public static void setDoctor(boolean doctor) {
        Profile.doctor = doctor;
    }

    public static int getPhonenumber() {
        return phonenumber;
    }

    public static void setPhonenumber(int phonenumber) {
        Profile.phonenumber = phonenumber;
    }

    public static boolean isLogged() {
        return logged;
    }

    public static void setLogged(boolean logged) {
        Profile.logged = logged;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        Profile.type = type;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        Profile.surname = surname;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Profile.name = name;
    }

    public static String getMiddlename() {
        return middlename;
    }

    public static void setMiddlename(String middlename) {
        Profile.middlename = middlename;
    }
}
