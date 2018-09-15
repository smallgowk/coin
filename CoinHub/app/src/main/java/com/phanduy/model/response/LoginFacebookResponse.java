package com.phanduy.model.response;

import com.phanduy.model.UserProfile;

/**
 * Created by duyuno on 11/24/16.
 */
public class LoginFacebookResponse extends ResponseObject {
    UserProfile user;

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
