package com.example.dmitriy.emergencyassistant.roomDatabase.entities.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.dmitriy.emergencyassistant.model.user.UserRelation;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.organization.EntityOrganization;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.personal.EntityUserPersonal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Entity
public class EntityUser {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    @SerializedName("email")
    @Expose
    private String email;

    /*
    @SerializedName("locale")
    @Expose
    private Locale locale;
     */


    /*
    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateCreation;
     */

    /*
    @SerializedName("personal")
    @Expose
    private EntityUserPersonal personal;
     */

    /*
    @SerializedName("role")
    @Expose
    private UserRole role;
     */

    /*
    @SerializedName("organization")
    @Expose
    private EntityOrganization organization;
     */

    /*
    @SerializedName("descriptions")
    @Expose
    private String descriptions;
     */

    @SerializedName("notes")
    @Expose
    private String notes;

    /*
    @SerializedName("userRelations")
    @Expose
    private List<UserRelation> userRelations;
     */

    public EntityUser() {
    }

    public EntityUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public EntityUser(String id, String nickname, String password) {
        this(nickname,password);
        this.id = id;
    }

    public EntityUser(String id, String nickname, String password, UserRole userRole) {
        this(id, nickname,password);
        //this.role = userRole;
    }

    public EntityUser(String id, String nickname, String password, UserRole userRole, List<UserRelation> userRelations) {
        this(id, nickname,password,userRole);
        //this.userRelations = userRelations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /*
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public EntityUserPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(EntityUserPersonal personal) {
        this.personal = personal;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public EntityOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(EntityOrganization organization) {
        this.organization = organization;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    */
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /*
    public List<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(List<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }
     */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
