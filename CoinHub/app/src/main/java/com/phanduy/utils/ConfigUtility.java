package com.phanduy.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class ConfigUtility {
	public static int scrWidth, scrHeight;
	public static final int RESULT_CLOSE_ALL = 2;
	public static final int ID_NOTIFICATION = 1;
	public static final int DENSITY_DEFAULT = 160;
	public static float scale = 0.0f;
	public static float density;
	public static boolean isTabblet;

	public static final int REQUESTCODE_UPDATE_APK = 2014;
	public static final int REQUESTCODE_CHANGE_SETTING = 6996;
	public static final int REQUESTCODE_SETUP_APK = 1989;

	public static final int ANDROID_2 = 0;

	public static String REGISTRATION_ID;
	public static int version;

	public static final long TIME_VALID = 86400;

	public static String phoneModel;
	public static String androidVersion;
	public static String androidVersionName;
	public static String deviceId;
	public static int androidVersionCode;

	public static String keyEncrypt;
	public static String token;

	public static String deviceName;
	public static String packageId;

	public static String phonePackageName;

	public static String GA_ID = "UA-57223951-3";
	
	public static String deviceIp = "";
	
	public static int maxHeightGrapth;

	public static int statusBarHeight;

//	public static String language = "en";

	// private String TAG = getClass().getSimpleName();

	public static void getConfigs(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		scrHeight = metrics.heightPixels;
		scrWidth = metrics.widthPixels;
		version = Build.VERSION.SDK_INT;
		// Log.e("Version ", " " + version);
		 Log.e("Width " + scrWidth, "Height " + scrHeight);
		// Log.e("Version", "" + version);
		density = activity.getApplicationContext().getResources().getDisplayMetrics().density;
		scale = activity.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
		isTabblet = isTablet(activity);

		Log.e("density ", " " + density);
		Log.e("scale ", " " + scale);

		phoneModel = Build.MODEL;
		androidVersion = Build.VERSION.RELEASE;

		int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
		}

		if(scrHeight == 2560) {
			maxHeightGrapth = 1400;
		} else {
			maxHeightGrapth = (scrHeight * 1400) / 2560;
		}

		packageId = activity.getPackageName();

		PackageInfo pInfo;
		try {
			pInfo = activity.getPackageManager().getPackageInfo(packageId, 0);
			androidVersionName = pInfo.versionName;
			androidVersionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

//		try {
//			ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
//			Bundle bundle = ai.metaData;
//			language = bundle.getString("language");
//		} catch (NameNotFoundException e) {
//		} catch (NullPointerException e) {
//		}

//		TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
//		deviceId = telephonyManager.getDeviceId();
//
//		if (deviceId == null || deviceId.length() == 0) {
//			deviceId = "" + 1989;
//		}

		// Log.e("Density", "" + density);
		// Log.e("Scale", "" + density);
		// Log.e("PhoneModel", "" + phoneModel);
		// Log.e("AndroidVersion", "" + androidVersion);
//		 Log.e("DeviceId", "" + deviceId);

		// if(deviceId.equals("000000000000000")) {
		// WebServiceConfig.APP_DOMAIN = WebServiceConfig.APP_DOMAIN_LOCAL;
		// WebServiceConfig.URL_AUTHEN = WebServiceConfig.URL_AUTHEN_LOCAL;
		// }

		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			deviceName = capitalize(model);
		} else {
			deviceName = capitalize(manufacturer) + " " + model;
		}


		String captchaFileName = "/jcaptcha.jpg";

//		int r = new Random().nextInt(4);
//		r = 1;
//		switch (r) {
//		case 0:
//			WebServiceConfig.DOMAIN_CHARGING = "http://203.113.138.114/" + WebServiceConfig.CONTEXT_ROOT  ;
//			break;
//		case 1:
//			WebServiceConfig.DOMAIN_CHARGING = "http://203.113.138.115/" + WebServiceConfig.CONTEXT_ROOT ;
//			break;
//		case 2:
//			WebServiceConfig.DOMAIN_CHARGING = "http://203.113.138.116/" + WebServiceConfig.CONTEXT_ROOT ;
//			break;
//		case 3:
//			WebServiceConfig.DOMAIN_CHARGING = "http://203.113.138.117/" + WebServiceConfig.CONTEXT_ROOT ;
//			break;
//		default:
//			WebServiceConfig.DOMAIN_CHARGING = "http://203.113.138.114/" + WebServiceConfig.CONTEXT_ROOT ;
//			break;
//		}

//		WebServiceConfig.DOMAIN_CHARGING = "http://smartmotor.viettel.vn/" + WebServiceConfig.CONTEXT_ROOT ;
//		WebServiceConfig.DOMAIN_CHARGING = "http://103.1.210.161:9800/" + WebServiceConfig.CONTEXT_ROOT ;
//
//		WebServiceConfig.CAPCHA_URL = WebServiceConfig.DOMAIN_CHARGING   + captchaFileName;

//		FontTypeface.initFonts(activity);

	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		//Find the currently focused view, so we can grab the correct window token from it.
		View view = activity.getCurrentFocus();
		//If no view currently has focus, create a new one, just so we can grab a window token from it
		if (view == null) {
			view = new View(activity);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}


	public static void getIpAddress() {
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();

			String ipAddress = "";
			String loopBackAddress = "";
			String siteLocalAddress = "";

			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();



				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();

					if (inetAddress.isLoopbackAddress()) {
//						ipAddress = "LoopbackAddress: ";
						loopBackAddress = inetAddress.getHostAddress();
					} else if (inetAddress.isSiteLocalAddress()) {
//						ipAddress = "SiteLocalAddress: ";
						siteLocalAddress = inetAddress.getHostAddress();
					} else if (inetAddress.isLinkLocalAddress()) {
//						ipAddress = "LinkLocalAddress: ";

					} else if (inetAddress.isMulticastAddress()) {
//						ipAddress = "MulticastAddress: ";
					} else {
						if(inetAddress.getHostAddress() != null)
						ipAddress = inetAddress.getHostAddress();
					}
//					ip += ipAddress + inetAddress.getHostAddress() + "\n";
					Log.e("ipAddress: " + ipAddress, "IP: " + inetAddress.getHostAddress());
				}
			}

			if(ipAddress != null && !ipAddress.isEmpty()) {
				deviceIp = ipAddress;
			} else {
				if(siteLocalAddress != null && !siteLocalAddress.isEmpty()) {
					deviceIp = siteLocalAddress;
				} else if(loopBackAddress != null && !loopBackAddress.isEmpty()){
					deviceIp = loopBackAddress;
				} else {
					deviceIp = "localhost1";
				}
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			deviceIp = "localhost2";
		}
	}

	public static String getPhonePackage(Context context, Intent i) {
		if (ConfigUtility.phonePackageName == null) {
			PackageManager pm = context.getPackageManager();

			List<ResolveInfo> list = pm.queryIntentActivities(i, 0);
			for (ResolveInfo info : list) {
				String pkgnam = info.activityInfo.packageName;
				if (pkgnam.contains("com.android")) {
					ConfigUtility.phonePackageName = pkgnam;
					break;
				}
			}
		}
		return phonePackageName;
	}

	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	public static void turnOffNotification(Context context) {
		NotificationManager notiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notiManager.cancel(ID_NOTIFICATION);
	}

	@SuppressWarnings("deprecation")
	public static long getAvailableStorage() {
		long result = 10000;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
			result = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
		} else {
			StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
			result = (long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize();
		}
		return result;
	}

	static final int ERROR = -1;

	static public boolean externalMemoryAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	@SuppressWarnings("deprecation")
	static public long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	@SuppressWarnings("deprecation")
	static public long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	@SuppressWarnings("deprecation")
	static public long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return ERROR;
		}
	}

	@SuppressWarnings("deprecation")
	static public long getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return ERROR;
		}
	}

	static public String formatSize(long size) {
		String suffix = null;

		if (size >= 1024) {
			suffix = "KiB";
			size /= 1024;
			if (size >= 1024) {
				suffix = "MiB";
				size /= 1024;
			}
		}

		StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

		int commaOffset = resultBuffer.length() - 3;
		while (commaOffset > 0) {
			resultBuffer.insert(commaOffset, ',');
			commaOffset -= 3;
		}

		if (suffix != null)
			resultBuffer.append(suffix);
		return resultBuffer.toString();
	}

	public static boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
		boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}
	
	public static int dpToPixel(int dp) {
//		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//		float px = dp * (metrics.densityDpi / 160f);
//		return (int) px;
		return (int)(dp * scale);
	}

	public static int dpToPx(float dp, Resources resources){
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
		return (int) px;
	}
}
