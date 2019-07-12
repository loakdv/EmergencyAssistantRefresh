/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.elements;


/*
Данный класс является простым элементом, служащий
обычной болванкой для хранения данных

Конкретно в этом случае он нужен для содержания данных
о "быстром" юзере
 */
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
