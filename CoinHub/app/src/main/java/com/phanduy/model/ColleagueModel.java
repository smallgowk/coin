package com.phanduy.model;

import android.os.Parcel;

/**
 * Created by duyuno on 7/11/17.
 */
public class ColleagueModel extends PBXModel{

    private int colleagueId;
    private int userId;
    private int groupId;
    private String colleagueName;
    private String numberOfExt;
    private String departmentName;
    private String position;
    private String email;
    private String phoneNumber;
    private int deleteFlag;

    public ColleagueModel() {

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isSelected;

    private String iconUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean checkUrl() {
        return iconUrl != null && !iconUrl.isEmpty();
    }

    public int getColleagueId() {
        return colleagueId;
    }

    public String getColleagueName() {
        return colleagueName;
    }

    public String getNumberOfExt() {
        return numberOfExt;
    }

    public void setColleagueName(String colleagueName) {
        this.colleagueName = colleagueName;
    }

    public void setNumberOfExt(String numberOfExt) {
        this.numberOfExt = numberOfExt;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public void changeSelected() {
        isSelected = !isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getUserId() {
        return userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(colleagueId);
        dest.writeString(colleagueName);
        dest.writeString(numberOfExt);
    }

    public ColleagueModel(Parcel in) {
        colleagueId = in.readInt();
        colleagueName = in.readString();
        numberOfExt = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {
        public ColleagueModel createFromParcel(Parcel in) {
            return new ColleagueModel(in);
        }

        public ColleagueModel[] newArray(int size) {
            return new ColleagueModel[size];
        }
    };
}
