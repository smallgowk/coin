package com.phanduy.model;

/**
 * Created by duyuno on 10/8/16.
 */
public class Handler {
    public long id;
    public String account;
    public String name;
    public String email;
    public String phoneNumber;
    public String avatar;
    public int likes;
    public int dislikes;

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public long getId() {
        return id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void increaseLike() {
        likes ++;
    }

    public void increaseDislike() {
        dislikes ++;
    }
}
