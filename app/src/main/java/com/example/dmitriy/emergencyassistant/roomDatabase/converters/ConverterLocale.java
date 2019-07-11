/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Locale;

public class ConverterLocale {

    @TypeConverter
    public String fromLocale(Locale locale) {
        return locale.toString();
    }


}
