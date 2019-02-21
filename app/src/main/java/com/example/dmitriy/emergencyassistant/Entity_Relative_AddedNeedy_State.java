package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Entity_Relative_AddedNeedy.class, parentColumns = "id", childColumns = "needy_id", onDelete = CASCADE),
        indices = {@Index(value = "needy_id", unique = false)})
public class Entity_Relative_AddedNeedy_State {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long needy_id;

    public int s9=0;
    public int s12=0;
    public int s15=0;
    public int s18=0;
    public int s21=0;
    public String date;

    public Entity_Relative_AddedNeedy_State(int s9, int s12, int s15, int s18, int s21, String date, long needy_id){
        this.s9=s9;
        this.s12=s12;
        this.s15=s15;
        this.s18=s18;
        this.s21=s21;
        this.date=date;
        this.needy_id=needy_id;
    }

    public long getId() {
        return this.id;
    }

    public long getNeedy_id() {
        return this.needy_id;
    }

    public int getS9() {
        return this.s9;
    }

    public int getS12() {
        return this.s12;
    }

    public int getS15() {
        return this.s15;
    }

    public int getS18() {
        return this.s18;
    }

    public int getS21() {
        return this.s21;
    }

    public String getDate() {
        return this.date;
    }
}
