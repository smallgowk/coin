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


public class LanguageAdapter extends ArrayAdapter<CharSequence> {

	private Context context;

	private boolean isShowContent;

	public LanguageAdapter(Context context, int resource) {
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
		return GlobalValue.listLanguage != null ? GlobalValue.listLanguage.get(position).getName() : "";
//		return "+" + CountryCode.COUNTRY_CODE[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);
		if(isShowContent) {
//			String name = CountryCode.COUNTRY_NAME != null ? (CountryCode.COUNTRY_NAME[position] + " (+" + CountryCode.COUNTRY_CODE[position]+ ")") : "";
			textView.setText(GlobalValue.listLanguage.get(position).getName());
			textView.setTextColor(context.getResources().getColor(R.color.black87));
			textView.setTypeface(FontTypeface.getTypecace((Activity)context, FontTypeface.FONT_ROBOTO_REGULAR));
			textView.setGravity(Gravity.LEFT);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		}

		return textView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return GlobalValue.listLanguage  != null ? GlobalValue.listLanguage.size() : 0;
	}

}
