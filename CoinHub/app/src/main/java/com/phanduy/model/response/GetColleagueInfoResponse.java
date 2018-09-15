package com.phanduy.model.response;

import com.phanduy.model.ColleagueModel;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetColleagueInfoResponse extends ResponseObject{

    ColleagueModel memberInfo;

    public ColleagueModel getMemberInfo() {
        return memberInfo;
    }
}
