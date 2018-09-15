package com.phanduy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phanduy.view.fragment.market.AllMarketFragment;
import com.phanduy.view.fragment.market.BaseMarketSubFragment;
import com.phanduy.view.fragment.market.DumpMarketFragment;
import com.phanduy.view.fragment.market.FavoriteMarketFragment;
import com.phanduy.view.fragment.market.PumbMarketFragment;

import java.util.ArrayList;


public class MarketPagerAdapter extends FragmentPagerAdapter {
    //    private static final int NUMBER = 3;
    private static final int NUMBER = 4;

    public MarketPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    AllMarketFragment allMarketFragment;
    FavoriteMarketFragment favoriteMarketFragment;
    PumbMarketFragment pumbMarketFragment;
    DumpMarketFragment dumpMarketFragment;

    public void refreshData() {

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if(allMarketFragment == null) {
                    allMarketFragment = new AllMarketFragment();
                }
                return allMarketFragment;
            case 1:
                if(favoriteMarketFragment == null) {
                    favoriteMarketFragment = new FavoriteMarketFragment();
                }
                return favoriteMarketFragment;
            case 2:
                if(pumbMarketFragment == null) {
                    pumbMarketFragment = new PumbMarketFragment();
                }
                return pumbMarketFragment;
            case 3:
                if(dumpMarketFragment == null) {
                    dumpMarketFragment = new DumpMarketFragment();
                }
                return dumpMarketFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Favorite";
            case 2:
                return "Pumb";
            case 3:
                return "Dump";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return NUMBER;
    }
}
