package com.phanduy.view.fragment.market;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.phanduy.coinhub.R;

public abstract class BaseMarketSubFragment extends Fragment {
	public abstract void fetchData();
}
