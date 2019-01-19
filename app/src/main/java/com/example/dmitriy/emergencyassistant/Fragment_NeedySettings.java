package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment_NeedySettings extends Fragment {

    //Временная переменная для сохранения настроек
    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";


    //Элементы экрана
    EditText etSurname;
    EditText etName;
    EditText etMiddleName;
    EditText etInfo;
    Button btnSave;



    /*
    Кнопки для выбора фрагмента
    Фрагменты для добавления номеров, врачей, родственников
     */
    Button btn_Numbers;
    Button btn_Relatives;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needysettings, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnSave:
                        saveSettings();
                        break;
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                    case R.id.btn_Profiles:
                        startRelatives();
                        break;
                }
            }
        };
        Log.i("LOG_TAG", "--- Created activity NeedySettings ---");
        //Загружаем настрокий
        loadSettings();
        //Инициализация элементов экрана
        etSurname=v.findViewById(R.id.etSurname);
        etSurname.setText(Profile.getSurname());

        etName=v.findViewById(R.id.etName);
        etName.setText(Profile.getName());

        etMiddleName=v.findViewById(R.id.etMiddleName);
        etMiddleName.setText(Profile.getMiddlename());

        etInfo=v.findViewById(R.id.etInfo);
        etInfo.setText(Needy.getInfo());

        btnSave=v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(oclBtn);

        btn_Numbers=v.findViewById(R.id.btn_Numbers);
        btn_Numbers.setOnClickListener(oclBtn);

        btn_Relatives=v.findViewById(R.id.btn_Profiles);
        btn_Relatives.setOnClickListener(oclBtn);
        return v;
    }




    //Тестовая функция сохранения данных
    private void saveSettings(){
        settingsPref=this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor=settingsPref.edit();
        settingsEditor.putInt("type", Profile.getType());
        settingsEditor.putInt("sos_signal", Needy.getSignalSOS());
        settingsEditor.putInt("help_signal", Needy.getSignalHelp());
        settingsEditor.putInt("state_signal", Needy.getSignalState());
        settingsEditor.putString("name", etName.getText().toString());
        settingsEditor.putString("info", etInfo.getText().toString());
        settingsEditor.putString("surname", etSurname.getText().toString());
        settingsEditor.putString("middlename", etMiddleName.getText().toString());
        settingsEditor.apply();
        Log.i("LOG_TAG", "--- Settings saved! ---");
    }


    //Временный метод загрузки данных
    private void loadSettings(){
        Log.i("LOG_TAG", "--- Load settings! ---");
        settingsPref = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int savedtype=settingsPref.getInt("type", 0);
        int savedsos=settingsPref.getInt("sos_signal", 0);
        int savedhelp=settingsPref.getInt("help_signal", 0);
        int savedstate=settingsPref.getInt("state_signal", 0);
        boolean savedlogged=settingsPref.getBoolean("logged", true);
        String savedinfo=settingsPref.getString("info", "");
        String savedname=settingsPref.getString("name", "");
        String savedsurname=settingsPref.getString("surname", "");
        String savedmiddlename=settingsPref.getString("middlename", "");
        Log.i("LOG_TAG", "--- Settings loaded! ---");

        //Устанавливаем нужным классам нужные данные
        Needy.setInfo(savedinfo);
        Profile.setType(savedtype);
        Profile.setName(savedname);
        Needy.setSignalSOS(savedsos);
        Profile.setLogged(savedlogged);
        Needy.setSignalHelp(savedhelp);
        Needy.setSignalState(savedstate);
        Profile.setSurname(savedsurname);
        Profile.setMiddlename(savedmiddlename);
        Log.i("LOG_TAG", "--- Set settings... ---");


        //Выводим нужную нам информацию в логи
        Log.i("LOG_TAG", "--- Type: "+Profile.getType());
        Log.i("LOG_TAG", "--- Surname: "+Profile.getSurname());
        Log.i("LOG_TAG", "--- Name: "+Profile.getName());
        Log.i("LOG_TAG", "--- MiddleName: "+Profile.getMiddlename());
        Log.i("LOG_TAG", "--- Info: "+Needy.getInfo());
        Log.i("LOG_TAG", "--- SOS signal: "+Needy.getSignalSOS());
        Log.i("LOG_TAG", "--- HELP signal: "+Needy.getSignalHelp());
        Log.i("LOG_TAG", "--- State signal: "+Needy.getSignalState());
    }


    private void startNumbers(){
        Intent numbers=new Intent(getContext(), Activity_Dialog_Numbers.class);
        startActivity(numbers);
    }

    private void startRelatives(){
        Intent relatives=new Intent(getContext(), Activity_Dialog_Relatives.class);
        startActivity(relatives);
    }
}
