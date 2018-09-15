package com.phanduy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotifyModel implements Parcelable {
	
	public static final String ENROLLED = "STUDENT_ENROLLED";
	public static final String BROADSCAST = "BROADSCAST";

	private int sourceId;
	private int actionId;
	private String action;


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(sourceId);
		dest.writeInt(actionId);
		dest.writeString(action);

	}

	// Parcelling part
	public NotifyModel(Parcel in) {
		this.sourceId = in.readInt();
		this.actionId = in.readInt();
		this.action = in.readString();
	}

	@SuppressWarnings("rawtypes")
	public static final Creator CREATOR = new Creator() {
		public NotifyModel createFromParcel(Parcel in) {
			return new NotifyModel(in);
		}

		public NotifyModel[] newArray(int size) {
			return new NotifyModel[size];
		}
	};

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
