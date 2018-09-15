package com.phanduy.view.fragment.portfolio;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phanduy.coinhub.R;
import com.phanduy.model.MyCoin;
import com.phanduy.model.coinmarket.CoinModel;
import com.phanduy.store.GlobalValue;

/**
 * Created by duyuno on 4/11/18.
 */
public class AddCoinFragment extends Fragment{

    MainPortfolioFragment mainPortfolioFragment;

    EditText edtSymBol, edtAmmount;

    Button btnSave;
    Button btnCancel;

    View view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainPortfolioFragment = (MainPortfolioFragment)getParentFragment();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

//        mainPortfolioFragment = (MainPortfolioFragment) childFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_coin, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        initView();

        return view;
    }

    public void initView() {
        edtSymBol = (EditText) view.findViewById(R.id.edtSymBol);
        edtAmmount = (EditText) view.findViewById(R.id.edtAmmount);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPortfolioFragment.removeSubFragment();
            }
        });
    }

    public void saveData() {
        String symbol = edtSymBol.getText().toString().trim().toUpperCase();

        if(symbol.isEmpty()) return;

        double amount = 0;

        try {
            String amtStr = edtAmmount.getText().toString().trim();

            if(amtStr.isEmpty()) {
                amount = 0;
            } else {
                amount = Double.parseDouble(amtStr);
            }

        } catch (Exception e) {
            return;
        }

        if(GlobalValue.hashCoin != null && GlobalValue.hashCoin.containsKey(symbol)) {
            Toast.makeText(getContext(), "Coin đã add!", Toast.LENGTH_SHORT).show();
            return;
        }

        for(CoinModel coinModel : GlobalValue.listAllCoins) {
            if(coinModel.getSymbol().equals(symbol)) {
                MyCoin myCoin = new MyCoin();
                myCoin.copyData(coinModel);
                myCoin.setAmmount(amount);

                GlobalValue.putCoinInfo(myCoin);

                mainPortfolioFragment.addCoin(myCoin);
                mainPortfolioFragment.fetchData(true);
            }
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
