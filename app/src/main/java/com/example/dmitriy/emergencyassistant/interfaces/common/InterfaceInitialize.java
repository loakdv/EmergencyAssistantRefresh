/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:32 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:26 PM
 *
 */

package com.example.dmitriy.emergencyassistant.interfaces.common;

/*
Интерфейс который должен имплементировать каждая активность/фрагмент(где есть отображаемые на экране элементы)
Т.к. он нужен для базовой инициализации всех нудных элементов
 */
public interface InterfaceInitialize {

    void initializeScreenElements();
}
