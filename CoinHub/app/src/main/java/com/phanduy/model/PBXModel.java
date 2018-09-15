package com.phanduy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by duyuno on 7/14/17.
 */
public class PBXModel implements Parcelable {
    public boolean isSelected() {
        return false;
    }

    public void setSelected(boolean selected) {

    }

    public void changeSelected() {

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
