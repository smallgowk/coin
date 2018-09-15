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
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.interfaces.ConfirmListener;
import com.phanduy.model.MyCoin;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.DialogUtility;
import com.phanduy.view.activity.MainHomeActivity;

/**
 * Created by duyuno on 4/11/18.
 */
public class EditCoinFragment extends Fragment{

    MainHomeActivity coinHubActivity;

    MainPortfolioFragment mainPortfolioFragment;

    TextView txtSymBol;
    EditText edtAmmount;

    Button btnSave;
    Button btnDelete;

    MyCoin myCoin;

    View view;

    public void setMyCoin(MyCoin myCoin) {
        this.myCoin = myCoin;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        coinHubActivity = (MainHomeActivity) activity;
        mainPortfolioFragment = (MainPortfolioFragment)getParentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_coin, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        initView();

        return view;
    }

    public void initView() {
        txtSymBol = (TextView) view.findViewById(R.id.txtSymBol);
        edtAmmount = (EditText) view.findViewById(R.id.edtAmmount);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);

        txtSymBol.setText(myCoin.getSymbol());
        edtAmmount.setText("" + myCoin.getAmmount());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtility.showDialogConfirm(coinHubActivity, "Are you sure?", new ConfirmListener() {

                    @Override
                    public void doCancel() {

                    }

                    @Override
                    public void doAccept() {
                        delete();
                    }
                });


            }
        });
    }

    public void saveData() {

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

        myCoin.setAmmount(amount);
        GlobalValue.putCoinInfo(myCoin);

        mainPortfolioFragment.updateCoin(myCoin);
        mainPortfolioFragment.fetchData(true);

    }

    public void delete() {
        GlobalValue.deleteCoin(myCoin);
        mainPortfolioFragment.deleteCoin(myCoin);
        mainPortfolioFragment.fetchData(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
