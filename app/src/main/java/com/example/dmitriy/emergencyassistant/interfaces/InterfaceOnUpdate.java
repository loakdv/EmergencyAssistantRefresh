/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces;

/*
Интерфйес который нужен для связи между фрагментом и активностью
 */
public interface InterfaceOnUpdate {
    void updateScreen(String id);
    void updateForLast();
}
