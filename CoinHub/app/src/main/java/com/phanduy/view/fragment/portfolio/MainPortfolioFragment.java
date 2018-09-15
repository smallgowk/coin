package com.phanduy.view.fragment.portfolio;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phanduy.adapter.ListPortfolioCoinAdapter;
import com.phanduy.coinhub.R;
import com.phanduy.interfaces.ModifyListener;
import com.phanduy.model.MyCoin;
import com.phanduy.store.AppSharePreference;
import com.phanduy.store.GlobalValue;
import com.phanduy.store.PortfolioDAO;
import com.phanduy.utils.StringUtility;
import com.phanduy.view.activity.MainHomeActivity;
import com.phanduy.view.fragment.BaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duyuno on 4/11/18.
 */
public class MainPortfolioFragment extends BaseFragment {

    MainHomeActivity coinHubActivity;

    View view;

    TextView txtTotalBalanceBTC;
    TextView txtTotalBalanceUSD;
    TextView txtTotalBalanceVND;

    ButtonFloat btnAddCoin;

    ListView listView;

    ListPortfolioCoinAdapter coinAdapter;

    PortfolioDAO portfolioDAO;
    boolean isInit;

    double btcBlance = 0;
    double usdBlance = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        coinHubActivity = (MainHomeActivity) activity;
        portfolioDAO = new PortfolioDAO(activity);
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
        view = inflater.inflate(R.layout.fragment_main_portfolio, container, false);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        initView();

        initData();

//        startTimer();
        isInit = true;

        return view;
    }

    public void initView() {
        txtTotalBalanceBTC = (TextView) view.findViewById(R.id.txtTotalBalanceBTC);
        txtTotalBalanceUSD = (TextView) view.findViewById(R.id.txtTotalBalanceUSD);
        txtTotalBalanceVND = (TextView) view.findViewById(R.id.txtTotalBalanceVND);
        btnAddCoin = (ButtonFloat) view.findViewById(R.id.btnAddCoin);
        listView = (ListView) view.findViewById(R.id.listView);
//        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshData();
//            }
//        });
////        // Configure the refreshing colors
//        swipeContainer.setColorScheme(R.color.orange_normal, R.color.orange_normal
//                , R.color.orange_normal, R.color.orange_normal);

        coinAdapter = new ListPortfolioCoinAdapter(getContext());

        coinAdapter.setModifyListener(modifyListener);


//        swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyCoin myCoin = coinAdapter.getItem(position);
//                EditCoinFragment editCoinFragment = new EditCoinFragment();
//                editCoinFragment.setMyCoin(myCoin);
//                addSubFragment(editCoinFragment);
//            }
//        });

        btnAddCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalValue.listAllCoins != null && !GlobalValue.listAllCoins.isEmpty()) {
                    AddCoinFragment addCoinFragment = new AddCoinFragment();
                    addSubFragment(addCoinFragment);
                    return;
                }
            }
        });

        listView.setAdapter(coinAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyCoin myCoin = coinAdapter.getItem(position);
                EditCoinFragment editCoinFragment = new EditCoinFragment();
                editCoinFragment.setMyCoin(myCoin);
                addSubFragment(editCoinFragment);
            }
        });
    }

    ModifyListener modifyListener = new ModifyListener() {
        @Override
        public void onDelete(final Object object) {
            portfolioDAO.delete((MyCoin) object);
            fetchData(false);
        }

        @Override
        public void onEdit(Object object) {
            EditCoinFragment editCoinFragment = new EditCoinFragment();
            editCoinFragment.setMyCoin((MyCoin) object);
            addSubFragment(editCoinFragment);
        }

        @Override
        public void onDetail(Object object) {

        }
    };

    public void initData() {

        AsyncTask<String, String, String> asyn = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {

                GlobalValue.listMyCoins = portfolioDAO.getMyCoinList();
                if(GlobalValue.listMyCoins != null) {
                    GlobalValue.initHash();
                    Collections.sort(GlobalValue.listMyCoins, GlobalValue.myComparator);

                } else {
                    String data = AppSharePreference.getInstance(coinHubActivity).getStringValue(AppSharePreference.LIST_COIN);
                    if (data != null && !data.isEmpty()) {
                        try {
                            Gson gson = new Gson();
                            Type type = new TypeToken<HashMap<String, MyCoin>>() {
                            }.getType();

                            GlobalValue.hashCoin = gson.fromJson(data, type);

                            GlobalValue.listMyCoins = new ArrayList<>();

                            for (Map.Entry<String, MyCoin> entry : GlobalValue.hashCoin.entrySet()) {
                                if (entry.getValue() instanceof MyCoin) {
                                    GlobalValue.listMyCoins.add(entry.getValue());
                                }
                            }

                            Collections.sort(GlobalValue.listMyCoins, GlobalValue.myComparator);

                            for(MyCoin myCoin : GlobalValue.listMyCoins) {
                                portfolioDAO.insert(myCoin);
                            }

                        } catch (Exception ex) {
//				AppSharePreference.getInstance(this).clear();
                        }
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                fetchData(false);
            }
        };

        asyn.execute();
    }

    public void fetchData(final boolean isRemoveSub) {

        AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                if (GlobalValue.listMyCoins != null && !GlobalValue.listMyCoins.isEmpty()) {
                    btcBlance = 0;
                    usdBlance = 0;

                    for (MyCoin myCoin : GlobalValue.listMyCoins) {
                        btcBlance += myCoin.getBTCBalance();
                        usdBlance += myCoin.getUSDBalance();
                    }


                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                coinAdapter.notifyDataSetChanged();
                txtTotalBalanceBTC.setText("" + StringUtility.convertDoubleToString(btcBlance) + "");
                txtTotalBalanceUSD.setText("" + StringUtility.convertDoubleToString(usdBlance) + " $");
                txtTotalBalanceVND.setText("" + StringUtility.convertDoubleToStringMUnit(usdBlance * 22675) + " VNĐ");
                if (isRemoveSub) {
                    removeSubFragment();
                }
            }
        };

        asyncTask.execute();




    }

    public void addCoin(MyCoin myCoin) {
        portfolioDAO.insert(myCoin);
    }

    public void updateCoin(MyCoin myCoin) {
        portfolioDAO.update(myCoin);
    }
    public void deleteCoin(MyCoin myCoin) {
        portfolioDAO.delete(myCoin);
    }

    public void refreshData() {

//        if(portfolioDAO != null && isInit) {
//
//            String response = intent.getStringExtra(GlobalInfo.BUNDLE_KEY_DATA);
//
//            if(response != null) {
//                GlobalValue.listAllCoins = JsonFacetoryUtils.parseData(response);
//
//                if (GlobalValue.listAllCoins != null && GlobalValue.hashCoin != null) {
//
//                    for (CoinModel coinMarketModel : GlobalValue.listAllCoins) {
//                        if (GlobalValue.hashCoin.containsKey(coinMarketModel.getSymbol())) {
////                            GlobalValue.putCoinInfo((MyCoin) coinMarketModel);
//                            if (GlobalValue.hashCoin.get(coinMarketModel.getSymbol()) instanceof MyCoin) {
//                                GlobalValue.hashCoin.get(coinMarketModel.getSymbol()).copyData(coinMarketModel);
//                                for (MyCoin myCoin1 : GlobalValue.listMyCoins) {
//                                    if (myCoin1.getId().equals(coinMarketModel.getId())) {
//                                        myCoin1.copyData(coinMarketModel);
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    Collections.sort(GlobalValue.listMyCoins, GlobalValue.myComparator);
//                    portfolioDAO.updateInfo(GlobalValue.listMyCoins);
//                }
//
////                        GlobalValue.listMyCoins = portfolioDAO.getMyCoinList();
////                        GlobalValue.initHash();
//                fetchData(false);
//            }
//
//
//        }

//        GetCMPrice getCMPrice = new GetCMPrice();
//        ApiController.doGet(coinHubActivity, getCMPrice, new ResponseListener() {
//            @Override
//            public void processResponse(String response) {
////                Gson gson = new GsonBuilder().setDateFormat(GlobalInfo.ServerConfig.DATE_FORMAT).create();
////                GlobalValue.listAllCoins = gson.fromJson(response, GetCMPricesResponse.class);
////                GlobalValue.listAllCoins = JsonFacetoryUtils.parseJson(response);
//
//                GlobalValue.listAllCoins = JsonFacetoryUtils.parseData(response);
//
//                if (GlobalValue.listAllCoins != null && GlobalValue.hashCoin != null) {
//
//                    for (CoinModel coinMarketModel : GlobalValue.listAllCoins) {
//                        if (GlobalValue.hashCoin.containsKey(coinMarketModel.getSymbol())) {
////                            GlobalValue.putCoinInfo((MyCoin) coinMarketModel);
//                            if (GlobalValue.hashCoin.get(coinMarketModel.getSymbol()) instanceof MyCoin) {
//                                GlobalValue.hashCoin.get(coinMarketModel.getSymbol()).copyData(coinMarketModel);
//                                for (MyCoin myCoin1 : GlobalValue.listMyCoins) {
//                                    if (myCoin1.getId().equals(coinMarketModel.getId())) {
//                                        myCoin1.copyData(coinMarketModel);
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    Collections.sort(GlobalValue.listMyCoins, GlobalValue.myComparator);
//
//                    fetchData(false);
//                }
//
////                swipeContainer.setRefreshing(false);
//            }
//
//            @Override
//            public void processResponse(int error, String content) {
////                swipeContainer.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onKeyBack() {
        if (findSubFragment() != null) {
            removeSubFragment();
            return true;
        } else {
            return false;
        }
    }
}
