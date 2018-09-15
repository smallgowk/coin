package com.phanduy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.phanduy.utils.DateUtil;

import java.util.Date;

/**
 * Created by duyuno on 7/14/17.
 */
public class ConversationModel implements Parcelable {

    private int conversationType;

    private int voiceConferenceId;
    private String voiceConferenceName;
//    private String start;
//    private String ended;
    private int totalMember;

    private String typeName;
    private Date start;
    private Date ended;

    public ConversationModel() {

    }

    public int getVoiceConferenceId() {
        return voiceConferenceId;
    }

    public String getVoiceConferenceName() {
        return voiceConferenceName;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public int getConversationType() {
        return conversationType;
    }

    public void setConversationType(int conversationType) {
        this.conversationType = conversationType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public Date getStartDate() {
        return start;
    }

    public void setStartDate(Date startDate) {
        this.start = startDate;
    }

    public Date getEndDate() {
        return ended;
    }

    public void setEndDate(Date endDate) {
        this.ended = endDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(conversationType);
        dest.writeInt(voiceConferenceId);
        dest.writeString(voiceConferenceName);
        dest.writeString(typeName);
        dest.writeString(DateUtil.convertFullDate(start));
        dest.writeString(DateUtil.convertFullDate(ended));
        dest.writeInt(totalMember);

    }

    // Parcelling part
    public ConversationModel(Parcel in) {
        this.conversationType = in.readInt();
        this.voiceConferenceId = in.readInt();
        this.voiceConferenceName = in.readString();
        this.typeName = in.readString();
        this.start = DateUtil.getConvertDate(in.readString());
        this.ended = DateUtil.getConvertDate(in.readString());
        this.totalMember = in.readInt();
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {
        public ConversationModel createFromParcel(Parcel in) {
            return new ConversationModel(in);
        }

        public ConversationModel[] newArray(int size) {
            return new ConversationModel[size];
        }
    };
}
