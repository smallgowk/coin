package com.phanduy.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.phanduy.coinhub.R;
import com.phanduy.utils.FontTypeface;
import com.rengwuxian.edittext.MaterialBetterSpinner;

public class SpinnerWithFont extends MaterialBetterSpinner {
//	private int defaultDimension = 0;
//	private int fontName;
//	private Context mContext;

	// public ButtonFlatWithFont(Context context) {
	// super(context);
	// mContext = context;
	// init(null, 0);
	// }

	public SpinnerWithFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	// public ButtonFlatWithFont(Context context, AttributeSet attrs, int
	// defStyle) {
	// super(context, attrs, defStyle);
	// mContext = context;
	// init(attrs, defStyle);
	// }

	private void init(Context context, AttributeSet attrs, int defStyle) {


		final TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.font, defStyle, 0);
		int fontType = a.getInteger(R.styleable.font_type, 0);
		a.recycle();
		Typeface robotoFont = FontTypeface.getTypecace((Activity)context, FontRules.fontNames[fontType]);
		if(robotoFont != null) {
			setTypeface(robotoFont);
		}

		setTextColor(context.getResources().getColor(FontRules.colors[fontType]));
//		setAlpha(FontRules.alphas[fontType]);
		setTextSize(TypedValue.COMPLEX_UNIT_SP, FontRules.textSizes[fontType]);
		
	}
}
