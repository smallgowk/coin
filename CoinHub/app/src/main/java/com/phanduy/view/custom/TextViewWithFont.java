package com.phanduy.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.utils.FontTypeface;

public class TextViewWithFont extends TextView {

	public TextViewWithFont(Context context, AttributeSet attrs) {
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
		// Load attributes
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.font, defStyle, 0);
//		int fontType = a.getInteger(R.styleable.font_type, 0);
		int size = a.getInteger(R.styleable.font_size, 0);
		String fontName = a.getString(R.styleable.font_name);
		a.recycle();
//		Typeface robotoFont = FontTypeface.getTypecace((Activity)context, FontRules.fontNames[fontType]);
		if(fontName != null) {
			Typeface robotoFont = FontTypeface.getTypecace((Activity) context, fontName);
			if (robotoFont != null) {
				setTypeface(robotoFont);
			}
		}

//		setTextColor(context.getResources().getColor(FontRules.colors[fontType]));
//		setAlpha(FontRules.alphas[fontType]);
//		setTextSize(TypedValue.COMPLEX_UNIT_SP, FontRules.textSizes[fontType]);
//		setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

	}

}
