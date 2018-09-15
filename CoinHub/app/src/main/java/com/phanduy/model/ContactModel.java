package com.phanduy.model;

import android.os.Parcel;

/**
 * Created by duyuno on 7/11/17.
 */
public class ContactModel extends PBXModel{
    String memberName;
    String phoneNumber;

    public ContactModel() {

    }

    public String getName() {
        return memberName;
    }

    public void setName(String name) {
        this.memberName = name;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(memberName);
        dest.writeString(phoneNumber);
    }

    public ContactModel(Parcel in) {
        memberName = in.readString();
        phoneNumber = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean selected) {

    }

    @Override
    public void changeSelected() {

    }
}
