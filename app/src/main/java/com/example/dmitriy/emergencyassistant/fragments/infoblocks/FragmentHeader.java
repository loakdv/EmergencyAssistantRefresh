/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.infoblocks;

import android.arch.persistence.room.Room;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

import de.hdodenhof.circleimageview.CircleImageView;

/*
Фрагмент для отображения Header
Сделан отдельным фрагментом для гибкости настройки
 */
public class FragmentHeader extends Fragment implements InterfaceDataBaseWork,
        InterfaceInitialize {


    //Элементы на экране
    private TextView tv_Type;
    private CircleImageView imageView;

    //Объект БД
    private DataBaseAppDatabase dataBase;

    private EntityUser profile;

    private View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_topphoto, container, false);
        initializeDataBase();
        initializeScreenElements();
        return v;
    }


    //Инициализируем элементы на экране
    @Override
    public void initializeScreenElements() {

        tv_Type=v.findViewById(R.id.tv_leftDrawerType);
        imageView=v.findViewById(R.id.circle_TopPhoto);

        try{
            /*
            byte[] image=dataBase.dao_user().getProfile().getPhoto();
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);
             */
        }
        catch (Exception e){}

        {
            tv_Type.setText("Соц. работник");
        }
    }


    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        profile=dataBase.dao_user().getProfile();
    }

    @Override
    public void initializeList() {}


}
