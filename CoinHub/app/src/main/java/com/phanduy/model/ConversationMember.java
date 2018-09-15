package com.phanduy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by duyuno on 7/19/17.
 */
public class ConversationMember implements Parcelable{
    private String memberName;
    private String phoneNumber;
    private int mute;

    public ConversationMember() {

    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String membephoneNumber) {
        this.phoneNumber = membephoneNumber;
    }

    public int getMute() {
        return mute;
    }

    public void setMute(int mute) {
        this.mute = mute;
    }

    public void changeMute() {
        if(mute == 1) {
            mute = 0;
        } else {
            mute = 1;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(memberName);
        dest.writeString(phoneNumber);
        dest.writeInt(mute);
    }

    public ConversationMember(Parcel in) {
        memberName = in.readString();
        phoneNumber = in.readString();
        mute = in.readInt();
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ConversationMember createFromParcel(Parcel in) {
            return new ConversationMember(in);
        }

        public ConversationMember[] newArray(int size) {
            return new ConversationMember[size];
        }
    };
}
