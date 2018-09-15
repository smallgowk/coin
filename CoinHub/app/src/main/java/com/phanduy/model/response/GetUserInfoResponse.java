package com.phanduy.model.response;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetUserInfoResponse extends SignInResponse{
    private String userName;
    private String callBack;

    public String getUserName() {
        return userName;
    }

    public String getCallBack() {
        return callBack;
    }
}
