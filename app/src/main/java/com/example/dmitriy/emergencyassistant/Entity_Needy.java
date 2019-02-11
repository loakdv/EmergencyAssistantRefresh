package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Profile.class, parentColumns = "id", childColumns = "profile_id", onDelete = CASCADE))
public class Entity_Needy {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long profile_id;


    /*
    0 - родственники и соц. работники
    1 - родственники
    2 - волонтёры/врачи
     */

    //Кому отправлять сигнал SOS
    public int sos_signal=0;
    //Кому отправлять сигнал Help
    public int help_signal=0;
    //Кому отправлять данные о состоянии
    public int state_signal=0;
    //Переменная с информацией о человеке
    public String info;

    public Entity_Needy(long profile_id, int sos, int help, int state, String info){
        this.sos_signal=sos;
        this.profile_id=profile_id;
        this.help_signal=help;
        this.state_signal=state;
        this.info=info;
    }


    public long getId() {
        return this.id;
    }

    public long getProfile_id() {
        return this.profile_id;
    }

    public int getSos_signal() {
        return this.sos_signal;
    }

    public int getHelp_signal() {
        return this.help_signal;
    }

    public int getState_signal() {
        return this.state_signal;
    }

    public String getInfo() {
        return this.info;
    }
}
