package com.phanduy.model.response;

import com.phanduy.model.ColleagueGroup;

import java.util.ArrayList;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetColleagueGroupResponse extends ResponseObject{

    ArrayList<ColleagueGroup> listGroup;

    public ArrayList<ColleagueGroup> getListGroup() {
        return listGroup;
    }
}
