package com.phanduy.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.phanduy.coinhub.R;

public abstract class BaseFragment extends Fragment {
	public abstract boolean onKeyBack();
	public static final int NEXT = 0;
	public static final int BACK = 1;

	public Fragment findSubFragment() {
		return getChildFragmentManager().findFragmentById(R.id.subFragment);
	}

	public void removeSubFragment() {

		FragmentManager fragmentManager = getChildFragmentManager();

		Fragment fragment = fragmentManager.findFragmentById(R.id.subFragment);

		if (fragment != null) {
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
			ft.remove(fragment).commit();
		}
	}

	public void addSubFragment(Fragment fragment) {
		FragmentManager fm = getChildFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
		ft.replace(R.id.subFragment, fragment).commit();
	}
}
