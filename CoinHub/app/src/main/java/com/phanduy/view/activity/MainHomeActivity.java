package com.phanduy.view.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phanduy.GetTempleService;
import com.phanduy.GlobalInfo;
import com.phanduy.coinhub.R;
import com.phanduy.model.MyCoin;
import com.phanduy.model.ToolEntity;
import com.phanduy.model.coinmarket.CoinModel;
import com.phanduy.model.coinmarket.GlobalMarket;
import com.phanduy.store.AppSharePreference;
import com.phanduy.store.GlobalValue;
import com.phanduy.store.PortfolioDAO;
import com.phanduy.utils.ConfigUtility;
import com.phanduy.utils.DataManager;
import com.phanduy.utils.JsonFacetoryUtils;
import com.phanduy.view.base.BABaseActivity;
import com.phanduy.view.fragment.BaseFragment;
import com.phanduy.view.fragment.MenuLeft;
import com.phanduy.view.fragment.market.MainMarketFragment;
import com.phanduy.view.fragment.portfolio.MainPortfolioFragment;

import java.util.Collections;

@SuppressLint("ValidFragment")
public class MainHomeActivity extends BABaseActivity {

    RelativeLayout btnSideBar;
    ImageView toggleIcon;
    TextView textTitleHeader;

    RelativeLayout layoutHeader;
    public BaseFragment preFragment, curFragment;
    FragmentManager fragmentManager;

    private MenuLeft mFrag;
    private ToolEntity currentMenu;

    private final static int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 9669;

    private DrawerLayout mDrawerLayout;

    Intent getTempleService;


    AppSharePreference appSharePreference;

    PortfolioDAO portfolioDAO;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context,final Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case GlobalInfo.BroadcastAction.REFRESH_DATA:

                    AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            String priceResponse = intent.getStringExtra(GlobalInfo.BUNDLE_KEY_CM_PRICE_DATA);
                            String globalResponse = intent.getStringExtra(GlobalInfo.BUNDLE_KEY_GLOBAL_DATA);

                            if(priceResponse != null) {

                                if(curFragment instanceof MainPortfolioFragment) {
                                    GlobalValue.listAllCoins = JsonFacetoryUtils.parseData(priceResponse);
                                    if (GlobalValue.listAllCoins != null && GlobalValue.hashCoin != null) {

                                        for (CoinModel coinMarketModel : GlobalValue.listAllCoins) {
                                            if (GlobalValue.hashCoin.containsKey(coinMarketModel.getSymbol())) {
                                                if (GlobalValue.hashCoin.get(coinMarketModel.getSymbol()) instanceof MyCoin) {
                                                    GlobalValue.hashCoin.get(coinMarketModel.getSymbol()).copyData(coinMarketModel);
                                                    for (MyCoin myCoin1 : GlobalValue.listMyCoins) {
                                                        if (myCoin1.getId().equals(coinMarketModel.getId())) {
                                                            myCoin1.copyData(coinMarketModel);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        Collections.sort(GlobalValue.listMyCoins, GlobalValue.myComparator);
                                        portfolioDAO.updateInfo(GlobalValue.listMyCoins);
                                    }


                                } else if(curFragment instanceof MainMarketFragment) {
                                    GlobalValue.listAllCoins = JsonFacetoryUtils.parseData(priceResponse);
                                    if(globalResponse != null) {
                                        Gson gson = new Gson();
                                        GlobalValue.globalMarket = gson.fromJson(globalResponse, GlobalMarket.class);
                                    }

                                }

                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            if(curFragment instanceof MainPortfolioFragment) {
                                ((MainPortfolioFragment) curFragment).fetchData(false);
                            } else if(curFragment instanceof MainMarketFragment) {
                                ((MainMarketFragment) curFragment).fetchData();
                            }
                        }
                    };

                    asyncTask.execute();

                    break;
            }

        }
    };

    //    private GoogleApiClient client;
    public MainHomeActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConfigUtility.getConfigs(this);

        portfolioDAO = new PortfolioDAO(this);

        if (savedInstanceState == null) {
            Log.e("savedInstanceState", "null");
        } else {

            Intent refresh = new Intent(this, MainHomeActivity.class);
            refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(refresh);// Start the same Activity
        }

        DataManager.initMenu(this);

        appSharePreference = AppSharePreference.getInstance(this);

        GlobalInfo.getInstance().setActivityContext(this);
        GlobalInfo.getInstance().setAppContext(this);

        // set the Above View
        setContentView(R.layout.layout_main_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        layoutHeader = (RelativeLayout) findViewById(R.id.layoutHeader);
        toggleIcon = (ImageView) findViewById(R.id.toggleIcon);
        textTitleHeader = (TextView) findViewById(R.id.txtTitleHeader);


        btnSideBar = (RelativeLayout) findViewById(R.id.btnSideBar);

        showTitleHeader(getResources().getString(R.string.labelMainHome));

        initSideBar(savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        init();
    }

    public void init() {
        setupMainFragment(DataManager.getTopMenu());
    }

    public void changeColorPrimary(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(color));
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
    }

    public void showTitleHeader(String title) {
        textTitleHeader.setVisibility(View.VISIBLE);
        textTitleHeader.setText(title);
    }

    public void onClickSideBar(View v) {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START, true);
        }
    }

    public void onClickHeaderRight(View v) {

    }
    public void onClickServiceInfo(View v) {
        startActivity(new Intent(MainHomeActivity.this.getBaseContext(), LinkActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
    public void onClickVersionInfo(View v) {
        startActivity(new Intent(MainHomeActivity.this.getBaseContext(), VersionInfoActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void initSideBar(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            mFrag = new MenuLeft();
            t.replace(R.id.left_drawer, mFrag);
            t.commit();
        } else {
            mFrag = (MenuLeft) this.getSupportFragmentManager().findFragmentById(R.id.left_drawer);
        }

    }

    public void setupMainFragment(ToolEntity toolEntity) {

        currentMenu = toolEntity;

//        if(toolEntity.getToolId() == ToolEntity.MENU_CONFERENCE) {
//            showRightIcon(R.drawable.icon_thietlap);
//        } else {
//            hideRightIcon();
//        }

        showTitleHeader(toolEntity.getToolName());

        if (curFragment != null && curFragment.getClass().getName().equals(toolEntity.getFragmentName())) {
            mDrawerLayout.closeDrawers();
            return;
        } else {
            preFragment = curFragment;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = fragmentManager.findFragmentByTag(toolEntity.getFragmentName());

            if (fragment == null) {
                fragment = Fragment.instantiate(this, toolEntity.getFragmentName());
                fragmentTransaction.add(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
            } else {
                if (!fragment.isAdded()) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.show(fragment);
            }
            curFragment = (BaseFragment) fragment;
//
            if (preFragment != null) {
                fragmentTransaction.hide(preFragment);
            } else {
                preFragment = curFragment;
            }
            fragmentTransaction.commit();

        }

    }

    public void onClickMenu(ToolEntity toolEntity) {

        if(currentMenu != null && currentMenu.getToolId() == toolEntity.getToolId()) {
            return;
        }

        if (toolEntity != null) {
            int catId = toolEntity.getToolId();
            switch (catId) {
                case ToolEntity.MENU_PORT_FOLIO:
                    setupMainFragment(toolEntity);
                    break;
                case ToolEntity.MENU_COIN_MARKET_CAP:
                    setupMainFragment(toolEntity);
                    break;
                case ToolEntity.MENU_COIN_EVENT:
                    break;
                case ToolEntity.MENU_ICOS:
                    break;
                case ToolEntity.MENU_ABOUT:
                    break;
            }

            mDrawerLayout.closeDrawers();

        }
    }

    private long preTimeStamp;

    public void onKeyBack() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            if(curFragment != null) {
                if(!curFragment.onKeyBack()) {
                    onQuit();
                }
            } else {
                onQuit();
            }

        }

    }

    private void onQuit() {
        long current = System.currentTimeMillis();
        if (current - preTimeStamp > 2000) {
            Toast.makeText(this, getResources().getString(R.string.msgExitApp), Toast.LENGTH_SHORT).show();
            preTimeStamp = current;
        } else {
            // TCPControl.getInstance().disconnect();
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onKeyBack();
                break;
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
//            switch (requestCode) {
//                case GlobalInfo.REQUEST_CODE_DETAIL:
//                case GlobalInfo.REQUEST_CODE_CREATE:
//
//                    boolean isUpdateListBilling = data.getBooleanExtra(GlobalInfo.BUNDLE_KEY_IS_UPDATE, false);
//                    if (!isUpdateListBilling) {
//                        return;
//                    }
//                    List<Fragment> allFragments = getSupportFragmentManager().getFragments();
//                    if (allFragments == null || allFragments.isEmpty()) {
//                        return;
//                    }
//
//                    for (Fragment fragment : allFragments) {
//                        if (fragment instanceof BaseFragment) {
//                            ((BaseFragment) fragment).onKeySearch();
//                        }
//                    }
//
//                    break;
//                case GlobalInfo.REQUEST_CODE_ACCOUNT:
//                    boolean isUpdate = data.getBooleanExtra(GlobalInfo.BUNDLE_KEY_IS_UPDATE, false);
//                    if (isUpdate) {
//                        doLogout();
//                    }
//                    break;
//            }
        }
    }

    @Override
    protected void onDestroy() {


//        if (GlobalValue.hashCoin != null && !GlobalValue.hashCoin.isEmpty()) {
//            Gson gson = new Gson();
//            String data = gson.toJson(GlobalValue.hashCoin);
//            AppSharePreference.getInstance(this).putStringValue(AppSharePreference.LIST_COIN, data);
//        }

        if (getTempleService != null) {
            stopService(getTempleService);
        }

        unregisterReceiver(broadcastReceiver);

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    boolean isRegisterd;

    @Override
    protected void onPause() {
//        DeployGate.unregisterCallback(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
//        DeployGate.registerCallback(this, true);

        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalInfo.BroadcastAction.REFRESH_DATA);
        registerReceiver(broadcastReceiver, filter);


        if (getTempleService == null) {
            getTempleService = new Intent(getBaseContext(), GetTempleService.class);
        }

        startService(getTempleService);



        super.onResume();
    }

//    @Override
//    public void onInitialized(boolean arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onStatusChanged(boolean arg0, boolean arg1, String arg2, boolean arg3) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onUpdateAvailable(int arg0, String arg1, int arg2) {
//        // TODO Auto-generated method stub
//
//    }

    @Override
    public void onStart() {
        super.onStart();
    }
}