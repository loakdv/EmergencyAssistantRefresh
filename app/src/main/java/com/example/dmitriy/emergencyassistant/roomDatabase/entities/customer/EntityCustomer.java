package com.example.dmitriy.emergencyassistant.roomDatabase.entities.customer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmitriy.emergencyassistant.roomDatabase.entities.profile.EntityProfile;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityProfile.class, parentColumns = "id", childColumns = "profile_id", onDelete = CASCADE),
        indices = {@Index(value = "profile_id", unique = false)})
public class EntityCustomer {

    @PrimaryKey
    public long id;

    public String profile_id;


    /*
    0 - родственники и соц. работники
    1 - родственники
    2 - волонтёры/врачи
     */

    //Кому отправлять сигнал SOS
    public int sos_signal;
    //Кому отправлять сигнал Help
    public int help_signal;
    //Кому отправлять данные о состоянии
    public int state_signal;
    //Переменная с информацией о человеке
    public String info;

    // 0 - организация не выбрана
    public String organization;

    public EntityCustomer(String profile_id, int sos_signal, int help_signal, int state_signal, String info, String organization){
        this.sos_signal=sos_signal;
        this.profile_id=profile_id;
        this.help_signal=help_signal;
        this.state_signal=state_signal;
        this.info=info;
        this.organization=organization;
    }





    public long getId() {
        return this.id;
    }

    public String getProfile_id() {
        return this.profile_id;
    }


    public int getState_signal() {
        return this.state_signal;
    }

    public String getInfo() {
        return this.info;
    }

    public String getOrganization(){
        return this.organization;
    }
}
