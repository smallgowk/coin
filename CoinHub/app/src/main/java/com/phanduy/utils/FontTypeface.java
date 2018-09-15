package com.phanduy.utils;

import android.app.Activity;
import android.graphics.Typeface;

public class FontTypeface {

	public static final String FONT_ROBOTO_BLACK = "Roboto-Black.ttf";
	public static final String FONT_ROBOTO_BLACK_ITALIC = "Roboto-BlackItalic.ttf";
	public static final String FONT_ROBOTO_BOLD = "Roboto-Bold.ttf";
	public static final String FONT_ROBOTO_BOLD_CONDENSED = "Roboto-BoldCondensed.ttf";
	public static final String FONT_ROBOTO_BOLD_CONDENSED_ITALIC = "Roboto-BoldCondensedItalic.ttf";
	public static final String FONT_ROBOTO_BOLD_ITALIC = "Roboto-BoldItalic.ttf";
	public static final String FONT_ROBOTO_CONDENSED = "Roboto-Condensed.ttf";
	public static final String FONT_ROBOTO_CONDENSED_ITALIC = "Roboto-CondensedItalic.ttf";
	public static final String FONT_ROBOTO_ITALIC = "Roboto-Italic.ttf";
	public static final String FONT_ROBOTO_LIGHT = "Roboto-Light.ttf";
	public static final String FONT_ROBOTO_LIGHT_ITALIC = "Roboto-LightItalic.ttf";
	public static final String FONT_ROBOTO_MEDIUM = "Roboto-Medium.ttf";
	public static final String FONT_ROBOTO_MEDIUM_ITALIC = "Roboto-MediumItalic.ttf";
	public static final String FONT_ROBOTO_REGULAR = "Roboto-Regular.ttf";
	public static final String FONT_ROBOTO_THIN = "Roboto-Thin.ttf";
	public static final String FONT_ROBOTO_THIN_ITALIC = "Roboto-ThinItalic.ttf";
	public static final String FONT_UTM_HANZEL = "UTM-Hanzel.ttf";
	public static final String FONT_CHIPPER = "Chipper.ttf";

//	public static final String FONT_PROXIMA_BLACK_ITALIC = "Proxima Nova Lg - Black Italic_0.ttf";
//	public static final String FONT_PROXIMA_BLACK = "Proxima Nova Lg - Black_0.ttf";
//	public static final String FONT_PROXIMA_BOLD_ITALIC = "Proxima Nova Lg - Bold Italic_0.ttf";
//	public static final String FONT_PROXIMA_BOLD = "Proxima Nova Lg - Bold_0.ttf";
//	public static final String FONT_PROXIMA_LIGHT_ITALIC = "Proxima Nova Lg - Light Italic_0.ttf";
//	public static final String FONT_PROXIMA_LIGHT = "Proxima Nova Lg - Light_0.ttf";
//	public static final String FONT_PROXIMA_REGULAR_ITALIC = "Proxima Nova Lg - Regular Italic_0.ttf";
//	public static final String FONT_PROXIMA_REGULAR = "Proxima Nova Lg - Regular_0.ttf";
//	public static final String FONT_PROXIMA_SEMIBOLD_ITALIC = "Proxima Nova Lg - Semibold Italic_0.ttf";
//	public static final String FONT_PROXIMA_SEMIBOLD = "Proxima Nova Lg - Semibold_0.ttf";
//	public static final String FONT_PROXIMA_THIN_ITALIC = "Proxima Nova Lg - Thin Italic_0.ttf";
//	public static final String FONT_PROXIMA_THIN = "Proxima Nova Lg - Thin_0.ttf";

	public static final String FONT_ASSETS_PATH = "fonts/";

	private static Typeface typefaceChipper, typefaceBlack, typefaceBlackItalic, typefaceBold, typefaceBoldCondensed, typefaceBoldCondensedItalic,
			typefaceBoldItalic, typefaceCondensed, typefaceCondensedItalic, typefaceItalic, typefaceLight, typefaceLightItalic, typefaceMedium,
			typefaceMediumItalic, typefaceRegular, typefaceThin, typefaceThinItalic, typefaceUTMHanzel, typefaceProBlackItalic, typefaceProBlack,
			typefaceProBoldItalic, typefaceProBold, typefaceProLightItalic, typefaceProLight, typefaceProRegularItalic, typefaceProRegular,
			typefaceProSemiboldItalic, typefaceProSemibold, typefaceProThinItalic, typefaceProThin;

//	public static void initFonts(Activity activity) {
//		typefaceProThin = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_THIN);
////		typefaceProThinItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_THIN_ITALIC);
//		typefaceProSemibold = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_SEMIBOLD);
////		typefaceProSemiboldItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_SEMIBOLD_ITALIC);
//		typefaceProRegular = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_REGULAR);
////		typefaceProRegularItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_REGULAR_ITALIC);
//		typefaceProLight = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_LIGHT);
////		typefaceProLightItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_LIGHT_ITALIC);
//		typefaceProBold = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_BOLD);
////		typefaceProBoldItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_BOLD_ITALIC);
////		typefaceProBlack = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_BLACK);
////		typefaceProBlackItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + FONT_PROXIMA_BLACK_ITALIC);
//	}

	public static Typeface getTypecace(Activity activity, String font) {
//		if (font.equals(FONT_PROXIMA_THIN)) {
//			if (typefaceProThin == null)
//				typefaceProThin = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProThin;
//		}
//		if (font.equals(FONT_PROXIMA_THIN_ITALIC)) {
//			if (typefaceProThinItalic == null)
//				typefaceProThinItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProThinItalic;
//		}
//		if (font.equals(FONT_PROXIMA_SEMIBOLD)) {
//			if (typefaceProSemibold == null)
//				typefaceProSemibold = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProSemibold;
//		}
//		if (font.equals(FONT_PROXIMA_SEMIBOLD_ITALIC)) {
//			if (typefaceProSemiboldItalic == null)
//				typefaceProSemiboldItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProSemiboldItalic;
//		}
//		if (font.equals(FONT_PROXIMA_REGULAR)) {
//			if (typefaceProRegular == null)
//				typefaceProRegular = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProRegular;
//		}
//		if (font.equals(FONT_PROXIMA_REGULAR_ITALIC)) {
//			if (typefaceProRegularItalic == null)
//				typefaceProRegularItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProRegularItalic;
//		}
//		if (font.equals(FONT_PROXIMA_LIGHT)) {
//			if (typefaceProLight == null)
//				typefaceProLight = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProLight;
//		}
//		if (font.equals(FONT_PROXIMA_LIGHT_ITALIC)) {
//			if (typefaceProLightItalic == null)
//				typefaceProLightItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProLightItalic;
//		}
//		if (font.equals(FONT_PROXIMA_BOLD)) {
//			if (typefaceProBold == null)
//				typefaceProBold = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProBold;
//		}
//		if (font.equals(FONT_PROXIMA_BOLD_ITALIC)) {
//			if (typefaceProBoldItalic == null)
//				typefaceProBoldItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProBoldItalic;
//		}
//		if (font.equals(FONT_PROXIMA_BLACK)) {
//			if (typefaceProBlack == null)
//				typefaceProBlack = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProBlack;
//		}
//		if (font.equals(FONT_PROXIMA_BLACK_ITALIC)) {
//			if (typefaceProBlackItalic == null)
//				typefaceProBlackItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
//			return typefaceProBlackItalic;
//		}
		if (font.equals(FONT_CHIPPER)) {
			if (typefaceChipper == null)
				typefaceChipper = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceChipper;
		}
		if (font.equals(FONT_ROBOTO_BLACK)) {
			if (typefaceBlack == null)
				typefaceBlack = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBlack;
		}
		if (font.equals(FONT_ROBOTO_BLACK_ITALIC)) {
			if (typefaceBlackItalic == null)
				typefaceBlackItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBlackItalic;
		}
		if (font.equals(FONT_ROBOTO_BOLD)) {
			if (typefaceBold == null)
				typefaceBold = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBold;
		}
		if (font.equals(FONT_ROBOTO_BOLD_CONDENSED)) {
			if (typefaceBoldCondensed == null)
				typefaceBoldCondensed = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBoldCondensed;
		}
		if (font.equals(FONT_ROBOTO_BOLD_CONDENSED_ITALIC)) {
			if (typefaceBoldCondensedItalic == null)
				typefaceBoldCondensedItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBoldCondensedItalic;
		}
		if (font.equals(FONT_ROBOTO_BOLD_ITALIC)) {
			if (typefaceBoldItalic == null)
				typefaceBoldItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceBoldItalic;
		}
		if (font.equals(FONT_ROBOTO_CONDENSED)) {
			if (typefaceCondensed == null)
				typefaceCondensed = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceCondensed;
		}
		if (font.equals(FONT_ROBOTO_CONDENSED_ITALIC)) {
			if (typefaceCondensedItalic == null)
				typefaceCondensedItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceCondensedItalic;
		}
		if (font.equals(FONT_ROBOTO_ITALIC)) {
			if (typefaceItalic == null)
				typefaceItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceItalic;
		}
		if (font.equals(FONT_ROBOTO_LIGHT)) {
			if (typefaceLight == null)
				typefaceLight = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceLight;
		}
		if (font.equals(FONT_ROBOTO_LIGHT_ITALIC)) {
			if (typefaceLightItalic == null)
				typefaceLightItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceLightItalic;
		}
		if (font.equals(FONT_ROBOTO_MEDIUM)) {
			if (typefaceMedium == null)
				typefaceMedium = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceMedium;
		}
		if (font.equals(FONT_ROBOTO_MEDIUM_ITALIC)) {
			if (typefaceMediumItalic == null)
				typefaceMediumItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceMediumItalic;
		}
		if (font.equals(FONT_ROBOTO_REGULAR)) {
			if (typefaceRegular == null)
				typefaceRegular = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceRegular;
		}
		if (font.equals(FONT_ROBOTO_THIN)) {
			if (typefaceThin == null)
				typefaceThin = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceThin;
		}
		if (font.equals(FONT_ROBOTO_THIN_ITALIC)) {
			if (typefaceThinItalic == null)
				typefaceThinItalic = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceThinItalic;
		}
		if (font.equals(FONT_UTM_HANZEL)) {
			if (typefaceUTMHanzel == null)
				typefaceUTMHanzel = Typeface.createFromAsset(activity.getAssets(), FONT_ASSETS_PATH + font);
			return typefaceUTMHanzel;
		}

		return null;
	}

}
