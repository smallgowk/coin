package com.phanduy.model.response;

/**
 * Created by duyuno on 11/14/16.
 */
public class ResponseObject {
    protected String message;
    protected int isSuccess;
    protected int isLogin;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getIsLogin() {
        return isLogin;
    }
}
