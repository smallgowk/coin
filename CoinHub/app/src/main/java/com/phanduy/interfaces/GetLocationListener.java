package com.phanduy.interfaces;

import android.location.Location;

/**
 * Created by duyuno on 8/3/16.
 */
public interface GetLocationListener {
    public void onNotConnected();
    public void onComplete(Location location);
    public void onErrorForPermission();
}
