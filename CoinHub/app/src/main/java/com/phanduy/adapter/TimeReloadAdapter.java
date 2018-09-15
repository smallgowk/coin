package com.phanduy.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.FontTypeface;


public class TimeReloadAdapter extends ArrayAdapter<CharSequence> {

	private Context context;

	private boolean isShowContent;

	public TimeReloadAdapter(Context context, int resource) {
		super(context, resource);
		this.context  = context;
	}


	public boolean isShowContent() {
		return isShowContent;
	}

	public void setShowContent(boolean isShowContent) {
		this.isShowContent = isShowContent;
	}


	@Override
	public CharSequence getItem(int position) {
		// TODO Auto-generated method stub
		return GlobalValue.listTimeLoadData != null ? GlobalValue.listTimeLoadData.get(position).getName(): "";
//		return "+" + CountryCode.COUNTRY_CODE[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);
		if(isShowContent) {
			textView.setText(GlobalValue.listTimeLoadData.get(position).getName());
			textView.setTextColor(context.getResources().getColor(R.color.black));
			textView.setTypeface(FontTypeface.getTypecace((Activity)context, FontTypeface.FONT_ROBOTO_REGULAR));
			textView.setGravity(Gravity.LEFT);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		}

		return textView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return GlobalValue.listTimeLoadData  != null ? GlobalValue.listTimeLoadData.size() : 0;
	}

}
