package com.example.dmitriy.emergencyassistant.elements;

public class ElementFastUser {

    private String initials;
    private String email;
    private String password;
    private String id;
    private String type;

    public ElementFastUser(String initials, String email, String password, String id, String type){
        this.initials = initials;
        this.email = email;
        this.password = password;
        this.id = id;
        this.type = type;
    }

    public String getInitials() {
        return this.initials;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getId() {
        return this.id;
    }

    public String getType(){
        return this.type;
    }
}
