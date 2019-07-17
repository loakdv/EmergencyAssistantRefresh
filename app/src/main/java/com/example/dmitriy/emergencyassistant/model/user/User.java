/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 3:22 PM
 *
 */

package com.example.dmitriy.emergencyassistant.model.user;

import android.provider.ContactsContract;

import com.example.dmitriy.emergencyassistant.model.organization.Organization;
import com.example.dmitriy.emergencyassistant.model.user.personal.UserPersonal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class User {

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

    @SerializedName("locale")
    @Expose
    private Locale locale;

    @SerializedName("dateCreation")
    @Expose
    private LocalDateTime dateCreation;

    @SerializedName("sub")
    @Expose
    private String sub;

    @SerializedName("dateEnable")
    @Expose
    private List<LocalDateTime> dateEnable;

    @SerializedName("personal")
    @Expose
    private UserPersonal personal;

    @SerializedName("role")
    @Expose
    private UserRole role;

    @SerializedName("roles")
    @Expose
    private List<UserRole> roles;

    @SerializedName("organization")
    @Expose
    private Organization organization;

    @SerializedName("organizations")
    @Expose
    private List<Organization> organizations;

    @SerializedName("authDateTime")
    @Expose
    private List<LocalDateTime> authDateTime;

    @SerializedName("descriptions")
    @Expose
    private String descriptions;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("users")
    @Expose
    private List<User> users;

    public User() {
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(String nickname, String password, UserRole userRole) {
        this(nickname,password);
        this.role = userRole;
    }

    @Override
    public String toString() {
        return this.nickname + " " + this.enable;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

//    public List<UserRelation> getUserRelations() {
//        return userRelations;
//    }
//
//    public void setUserRelations(List<UserRelation> userRelations) {
//        this.userRelations = userRelations;
//    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(UserPersonal personal) {
        this.personal = personal;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setDateEnable(List<LocalDateTime> dateEnable) {
        this.dateEnable = dateEnable;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<LocalDateTime> getDateEnable() {
        return dateEnable;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<LocalDateTime> getAuthDateTime() {
        return authDateTime;
    }

    public void setAuthDateTime(List<LocalDateTime> authDateTime) {
        this.authDateTime = authDateTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}