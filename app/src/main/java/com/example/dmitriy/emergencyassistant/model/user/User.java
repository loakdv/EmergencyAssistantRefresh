/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/18/19 9:17 PM
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

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("middlename")
    @Expose
    private String middlename;


    @SerializedName("enable")
    @Expose
    private boolean enable = true;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("mobile")
    @Expose
    private String mobile;

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

    @SerializedName("version")
    @Expose
    private Long version;

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

    @SerializedName("users2")
    @Expose
    private List<User> users2;

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
        String data = "\n";
        data += "Nickname: "+this.nickname+"\n";
        data += "Email: "+this.email+"\n";
        data += "Password: "+this.password+"\n";
        data += "Firstname: "+this.firstname+"\n";
        data += "Lastname: "+this.lastname+"\n";
        return data;
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public List<User> getUsers2() {
        return users2;
    }

    public void setUsers2(List<User> users2) {
        this.users2 = users2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
