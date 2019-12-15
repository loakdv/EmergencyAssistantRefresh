/*
 *
 *  Created by Dmitry Garmyshev on 09.12.19 17:34
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 09.12.19 17:34
 *
 */

package com.example.dmitriy.emergencyassistant.model.service;

public enum TaskStatus {
    NEW("Новый"),

    PROCESSING("Выполняется"),

    PENDING("В ожидании"),

    SOLVED("Решено"),

    CLOSED("Закрыто");

    private String s;

    TaskStatus(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

}
