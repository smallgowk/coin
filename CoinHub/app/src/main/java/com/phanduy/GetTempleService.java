package com.phanduy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.phanduy.api.ApiController;
import com.phanduy.interfaces.ResponseListener;
import com.phanduy.model.request.GetCMGlobal;
import com.phanduy.model.request.GetCMPrice;
import com.phanduy.store.AppSharePreference;
import com.phanduy.store.PortfolioDAO;

import java.util.Timer;
import java.util.TimerTask;

public class GetTempleService extends Service {

    TimerTask timerTask;
    Timer timer;
    PortfolioDAO repo;
    AppSharePreference shipSSharePreference;

    private boolean isLoading;

//	Ringtone ringtone;
//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equalsIgnoreCase("UPDATE_CONFIG")) {
//                float alert_temple = intent.getFloatExtra("ALERT_TEMPLE", -1);
//                if(alert_temple > 0) {
//                    alertTemple = alert_temple;
//                }
//                isAlert = intent.getBooleanExtra("UPDATE_IS_ALERT", false);
//                isVibrate = intent.getBooleanExtra("UPDATE_IS_VIBRATE", false);
//            }
//        }
//    };


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TempleService", "onStartCommand");
        // super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        shipSSharePreference = AppSharePreference.getInstance(this);

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("UPDATE_CONFIG");
//
//        registerReceiver(broadcastReceiver, intentFilter);


        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (repo == null) {
                    repo = new PortfolioDAO(getBaseContext());
                }

                if(!isLoading) {
                    isLoading = true;


                    GetCMGlobal getCMGlobal = new GetCMGlobal();

                    ApiController.doGet(getBaseContext(), getCMGlobal, new ResponseListener() {
                        @Override
                        public void processResponse(final String glbResponse) {
                            GetCMPrice getCMPrice = new GetCMPrice();
                            ApiController.doGet(getBaseContext(), getCMPrice, new ResponseListener() {
                                @Override
                                public void processResponse(String response) {
                                    if(response != null) {
                                        Intent intent = new Intent(GlobalInfo.BroadcastAction.REFRESH_DATA);
                                        intent.putExtra(GlobalInfo.BUNDLE_KEY_CM_PRICE_DATA, response);
                                        intent.putExtra(GlobalInfo.BUNDLE_KEY_GLOBAL_DATA, glbResponse);
                                        sendBroadcast(intent);
                                    }

                                    isLoading = false;
                                }

                                @Override
                                public void processResponse(int error, String content) {
//                swipeContainer.setRefreshing(false);
                                    isLoading = false;
                                }
                            });
                        }

                        @Override
                        public void processResponse(int error, String content) {
                            isLoading = false;
                        }
                    });


                }
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 10, 3000);
    }

    @Override
    public void onDestroy() {
        if (timerTask != null) {
            timerTask.cancel();
        }

//        if (broadcastReceiver != null) {
//            unregisterReceiver(broadcastReceiver);
//        }

        super.onDestroy();
    }

}