package com.phanduy.view.fragment.market;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phanduy.adapter.MarketPagerAdapter;
import com.phanduy.coinhub.R;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.StringUtility;
import com.phanduy.view.activity.MainHomeActivity;
import com.phanduy.view.custom.tabpager.SmartTabLayout;
import com.phanduy.view.fragment.BaseFragment;

/**
 * Created by duyuno on 4/11/18.
 */
public class MainMarketFragment extends BaseFragment {

    public static final int MODE_ALL = 0;
    public static final int MODE_PUMB = 1;
    public static final int MODE_DUMP = 2;

    MainHomeActivity coinHubActivity;

    View view;

    TextView txtMarketCap;
    TextView txt24hVol;
    TextView txtBTCDominance;

    private boolean isFirstLoad = true;

    boolean isInit;
    int mode;

    private ViewPager viewPager;
    private MarketPagerAdapter marketPagerAdapter;
    private SmartTabLayout viewPagerTab;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        coinHubActivity = (MainHomeActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_market, container, false);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        initView();

        fetchData();

//        startTimer();
        isInit = true;

        return view;
    }



    public void initView() {
        txtMarketCap = (TextView) view.findViewById(R.id.txtMarketCap);
        txt24hVol = (TextView) view.findViewById(R.id.txt24hVol);
        txtBTCDominance = (TextView) view.findViewById(R.id.txtBTCDominance);

        viewPager = (ViewPager) view.findViewById(R.id.viewpagerSeries);
        viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);

        marketPagerAdapter = new MarketPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(marketPagerAdapter);
        viewPagerTab.setViewPager(viewPager);

    }

    public void fetchData() {
        if (GlobalValue.listAllCoins != null && !GlobalValue.listAllCoins.isEmpty() && marketPagerAdapter != null) {
            BaseMarketSubFragment baseMarketSubFragment = (BaseMarketSubFragment) marketPagerAdapter.getItem(viewPager.getCurrentItem());
            if(baseMarketSubFragment != null) {
                baseMarketSubFragment.fetchData();
            }
        }

        if(GlobalValue.globalMarket != null) {
            txtMarketCap.setText("" + StringUtility.convertDoubleToString(GlobalValue.globalMarket.getTotal_market_cap_usd()) + "$");
            txt24hVol.setText("" + StringUtility.convertDoubleToString(GlobalValue.globalMarket.getTotal_24h_volume_usd()) + "$");
            txtBTCDominance.setText("" + StringUtility.convertDoubleToString(GlobalValue.globalMarket.getBitcoin_percentage_of_market_cap()) + "%");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onKeyBack() {
        return false;
    }

}
