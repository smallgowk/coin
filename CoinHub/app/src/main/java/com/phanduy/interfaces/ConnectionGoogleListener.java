package com.phanduy.interfaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;

/**
 * Created by duyuno on 8/3/16.
 */
public interface ConnectionGoogleListener {
    public void onConnected(@Nullable Bundle bundle);
    public void onConnectionSuspended(int i);
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult);
}
