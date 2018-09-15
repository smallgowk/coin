package com.phanduy.view.fragment.market;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.phanduy.adapter.ListMarketCoinAdapter;
import com.phanduy.coinhub.R;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.StringUtility;
import com.phanduy.view.activity.MainHomeActivity;
import com.phanduy.view.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by duyuno on 4/11/18.
 */
public class AllMarketFragment extends BaseMarketSubFragment {

    private final String TAG = AllMarketFragment.class.getSimpleName();

    public static final int MODE_ALL = 0;
    public static final int MODE_PUMB = 1;
    public static final int MODE_DUMP = 2;

    MainHomeActivity coinHubActivity;

    View view;

    ListView listView;

    //    SwipeRefreshLayout swipeContainer;
    ListMarketCoinAdapter coinAdapter;

    private boolean isFirstLoad = true;

    boolean isInit;
    int mode;

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
        view = inflater.inflate(R.layout.fragment_all_market, container, false);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        initView();

//        fetchData();

//        startTimer();
        isInit = true;

        return view;
    }



    public void initView() {
        listView = (ListView) view.findViewById(R.id.listView);

        coinAdapter = new ListMarketCoinAdapter(getContext());


        listView.setAdapter(coinAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyCoin myCoin = coinAdapter.getItem(position);
//                EditCoinFragment editCoinFragment = new EditCoinFragment();
//                editCoinFragment.setMyCoin(myCoin);
//                addSubFragment(editCoinFragment);
            }
        });

    }


    public void fetchData() {
        if (GlobalValue.listAllCoins != null && !GlobalValue.listAllCoins.isEmpty() && coinAdapter != null) {
            coinAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
