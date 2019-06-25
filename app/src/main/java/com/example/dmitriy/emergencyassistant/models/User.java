package com.example.dmitriy.emergencyassistant.models;

import com.example.dmitriy.emergencyassistant.models.personal.UserPersonal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class User {

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

    @SerializedName("dateCreation")
    @Expose
    private Date dateCreation;

    @SerializedName("personal")
    @Expose
    private UserPersonal personal;

    @SerializedName("role")
    @Expose
    private UserRole role;

    @SerializedName("organization")
    @Expose
    private Organization organization;

    @SerializedName("descriptions")
    @Expose
    private String descriptions;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("userRelations")
    @Expose
    private List<UserRelation> userRelations;

    public User() {
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(String id, String nickname, String password) {
        this(nickname,password);
        this.id = id;
    }

    public User(String id, String nickname, String password, UserRole userRole) {
        this(id, nickname,password);
        this.role = userRole;
    }

    public User(String id, String nickname, String password, UserRole userRole, List<UserRelation> userRelations) {
        this(id, nickname,password,userRole);
        this.userRelations = userRelations;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public UserPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(UserPersonal personal) {
        this.personal = personal;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(List<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }
}
