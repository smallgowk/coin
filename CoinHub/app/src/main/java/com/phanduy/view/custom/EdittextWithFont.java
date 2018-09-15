package com.phanduy.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.phanduy.coinhub.R;
import com.phanduy.utils.FontTypeface;
import com.rengwuxian.edittext.MaterialEditText;

public class EdittextWithFont extends MaterialEditText {
//	private int defaultDimension = 0;
//	private int fontName;
//	private Context mContext;

	// public ButtonFlatWithFont(Context context) {
	// super(context);
	// mContext = context;
	// init(null, 0);
	// }

	public EdittextWithFont(Context context, AttributeSet attrs) {
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
//			setTypeface(robotoFont);
//		}

//		final TypedArray a = context.obtainStyledAttributes(attrs,
//				R.styleable.font, defStyle, 0);
//		int fontType = a.getInteger(R.styleable.font_type, 0);
//		a.recycle();
//		Typeface robotoFont = FontTypeface.getTypecace((Activity)context, FontRules.fontNames[fontType]);
//		if(robotoFont != null) {
//			setTypeface(robotoFont);
//		}
//
//		setTextColor(context.getResources().getColor(FontRules.colors[fontType]));
////		setAlpha(FontRules.alphas[fontType]);
//		setTextSize(TypedValue.COMPLEX_UNIT_SP, FontRules.textSizes[fontType]);

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
		
	}
}
