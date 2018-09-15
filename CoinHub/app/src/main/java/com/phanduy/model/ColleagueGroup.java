package com.phanduy.model;

import java.util.ArrayList;

/**
 * Created by duyuno on 7/11/17.
 */
public class ColleagueGroup {
    private String groupName;
    private int groupId;
    private int totalMember;

    private boolean isSelected;

    private ArrayList<ColleagueModel> listMember;

    public String getGroupName() {
        return groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public ArrayList<ColleagueModel> getListMember() {
        return listMember;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean checkContainId(int id) {
        if(listMember == null || listMember.isEmpty()) return false;

        for(ColleagueModel colleagueModel : listMember) {
            if(colleagueModel.getColleagueId() == id) {
                return true;
            }
        }

        return false;
    }

    public void setListMember(ArrayList<ColleagueModel> listMember) {
        this.listMember = listMember;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
