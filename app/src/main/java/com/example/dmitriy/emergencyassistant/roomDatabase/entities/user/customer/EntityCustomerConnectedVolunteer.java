/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:52 PM
 *
 */

package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = EntityCustomer.class, parentColumns = "id", childColumns = "needyId", onDelete = CASCADE),
        indices = {@Index(value = "needyId", unique = false)})
public class EntityCustomerConnectedVolunteer {

    @PrimaryKey
    @NonNull
    public String id;
    public long needyId;
    public String name;
    public String surname;
    public String middlename;
    public String organization;
    public byte[] photo;

    public EntityCustomerConnectedVolunteer(String id, long needyId, String name, String surname, String middlename, String organization, byte[] photo){
        this.id=id;
        this.needyId=needyId;
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.organization=organization;
        this.photo=photo;
    }


    @NonNull
    public String getId() {
        return this.id;
    }

    public long getNeedyId() {
        return this.needyId;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getOrganization(){ return this.organization;}

    public byte[] getPhoto(){return this.photo;}
}
