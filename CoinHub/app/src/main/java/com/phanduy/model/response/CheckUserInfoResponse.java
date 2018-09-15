package com.phanduy.model.response;

import com.phanduy.model.BabyInfo;
import com.phanduy.model.UserProfile;

import java.util.ArrayList;

/**
 * Created by duyuno on 11/14/16.
 */
public class CheckUserInfoResponse extends ResponseObject{
    UserProfile user;
    ArrayList<BabyInfo> baby;

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public ArrayList<BabyInfo> getBaby() {
        return baby;
    }

    public void setBaby(ArrayList<BabyInfo> baby) {
        this.baby = baby;
    }
}
