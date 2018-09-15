package com.phanduy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.store.AppSharePreference;
import com.phanduy.utils.ConfigUtility;
import com.phanduy.view.base.BABaseActivity;

/**
 * Created by duyuno on 11/22/16.
 */
public class VersionInfoActivity extends BABaseActivity {

    AppSharePreference appSharePreference;

    TextView txtTitleHeader, txtVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appSharePreference = AppSharePreference.getInstance(this);
        setContentView(R.layout.layout_version_activity);
        initView();
        initData();
    }

    public void initView() {
        txtTitleHeader = (TextView) findViewById(R.id.txtTitleHeader);
        txtVersion = (TextView) findViewById(R.id.txtVersion);
    }
    public void initData() {
        txtTitleHeader.setText(getString(R.string.labelVersionInfo));
        txtVersion.setText("Phiên bản " + ConfigUtility.androidVersionName);
    }

    public void onClickBack(View v) {
        onKeyBack();
    }

    @Override
    public void onKeyBack() {
        finish();
    }

}
