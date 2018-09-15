package com.phanduy.model.response;

/**
 * Created by duyuno on 11/14/16.
 */
public class SignInResponse extends ResponseObject{

    private String token;
    private int userId;
    private String userName;
    private String callBack;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCallBack() {
        return callBack;
    }
}
