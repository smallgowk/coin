package com.phanduy.model;

/**
 * Created by duyuno on 7/30/16.
 */
public class TargetModel {

    public static final int TYPE_REAL_OBJECT = 0;
    public static final int TYPE_LOADING_STATE_OBJECT = 1;
    public static final int TYPE_NOINFO_STATE_OBJECT = 2;
    public static final int TYPE_TITLE_STATE_OBJECT = 3;
    public int objectType;
    public String objectState;

    private int id;
    private String name;
    private String phone;
    private String creator;
    private String email;
    private String avatar;
    private int status;
    private int vip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getObjectState() {
        return objectState;
    }

    public void setObjectState(String objectState) {
        this.objectState = objectState;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
