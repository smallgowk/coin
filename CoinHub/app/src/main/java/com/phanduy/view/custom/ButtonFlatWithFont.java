package com.phanduy.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.gc.materialdesign.views.ButtonFlat;
import com.phanduy.coinhub.R;
import com.phanduy.utils.FontTypeface;

public class ButtonFlatWithFont extends ButtonFlat {
//	private int defaultDimension = 0;
//	private int fontName;
//	private Context mContext;

	int[] attrsArray = new int[] {
			android.R.attr.textColor, // 0
			android.R.attr.background, // 1
			android.R.attr.layout_width, // 2
			android.R.attr.layout_height // 3
	};

	// public ButtonFlatWithFont(Context context) {
	// super(context);
	// mContext = context;
	// init(null, 0);
	// }

	public ButtonFlatWithFont(Context context, AttributeSet attrs) {
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
//		final TypedArray a = context.obtainStyledAttributes(attrs,
//				R.styleable.font, defStyle, 0);
//		String fontName = a.getString(R.styleable.font_name);
//		a.recycle();
//
//		if(StringUtility.isEmpty(fontName)) {
//			return;
//		}
//
//		Typeface robotoFont = FontTypeface.getTypecace((Activity)context, fontName);
////		switch (fontName) {
////		case Constants.FONT_BOLD:
////			robotoFont = Typeface.createFromAsset(mContext.getAssets(),
////					"fonts/Roboto-Bold.ttf");
////			break;
////		case Constants.FONT_MEDIUM:
////			robotoFont = Typeface.createFromAsset(mContext.getAssets(),
////					"fonts/Roboto-Medium.ttf");
////			break;
////		case Constants.FONT_REGULAR:
////			robotoFont = Typeface.createFromAsset(mContext.getAssets(),
////					"fonts/Roboto-Regular.ttf");
////			break;
////		default:
////			robotoFont = Typeface.createFromAsset(mContext.getAssets(),
////					"fonts/Roboto-Medium.ttf");
////			break;
////		}
//		if(robotoFont != null) {
//			setFontType(robotoFont);
//		}
//
//		TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
//
//		int colorId = ta.getResourceId(0,R.color.white);
//		ta.recycle();
//		getTextView().setTextColor(context.getResources().getColor(colorId));

		final TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.font, defStyle, 0);
		int fontType = a.getInteger(R.styleable.font_type, 0);
		a.recycle();
		Typeface robotoFont = FontTypeface.getTypecace((Activity)context, FontRules.fontNames[fontType]);
		if(robotoFont != null) {
			setFontType(robotoFont);
		}

		getTextView().setTextColor(context.getResources().getColor(FontRules.colors[fontType]));
//		setAlpha(FontRules.alphas[fontType]);
		getTextView().setTextSize(TypedValue.COMPLEX_UNIT_SP, FontRules.textSizes[fontType]);
		
	}

	private void setFontType(Typeface font) {
		ButtonFlatWithFont.this.getTextView().setTypeface(font);
	}
}
