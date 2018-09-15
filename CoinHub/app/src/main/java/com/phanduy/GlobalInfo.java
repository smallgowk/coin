package com.phanduy;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import java.text.SimpleDateFormat;

public class GlobalInfo extends Application{
	
	public static interface SharePreference {
		public static final String APP_PREFERENCE = "APP_NAME";
	}
//	public static Location mLastLocation;

	public static String currentLat = "", currentLng = "";

	public static final String DB_NAME = "ShopS_DB";
	public static int TIME_RELOAD_MAP = 10000;

	public static final String AZ_APP_API = "4b62ad307bef12aab8756df81012e208";
	public static final String AZ_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA36AeRyRF0uOLCpkX2G9JbOOSrO9TiqwwUeKL28KPPUxsCg2sSxOcG/5kiS5uXKo7XhuOlNuZb6wEbc5xprHa8e78jnjRQpZ2psdOwKOsTlGUzwrji3HgyuPFG6EHGUTE3VV6YHyb+4fiXYQNCngbQIULiFp//+alYCpC5HE5IMR04pIqEXP2yC+PKrC55b3tU+OXq+Igh0nu+RJKUfZe6js4mnu54IBqlPd2s6C496DjVFbcVLvDInvsbRY/cr0oPB9w273NfIK6BDheFfhREjCMZKMozoamTaGTi627yCifIW50v6Qdt9lfEyMyXS9SfxCHAhfJqmNXn+pMp6OULwIDAQAB";

	public static interface ServerConfig {
		//Release
		public static final String OAUTH_SITE = "https://api.coinbase.com/oauth/";
		public static final String RESOURCE_SITE = "http://171.244.9.8:8080/mpbx/";
		public static final String BITCOINT_DOMAIN = "https://api.coindesk.com/v1/bpi/";
		public static final String CM_DOMAIN = "https://api.coinmarketcap.com/v1/";
		public static final String ETH_DOMAIN = "https://poloniex.com/public";

		public static final String RESOURCE_CONTEXT = "api/shop/";
		public static final String STATIC_RESOURCE_CONTEXT = "api/resources/";

		//Release
//		public static final String OAUTH_SITE = "http://id.ships.vn/";
//		public static final String RESOURCE_SITE = "http://api.ships.vn/";

		public static final String CLIENT_ID = "GNNExpress-MobileApp";
		public static final String POLICY_URL = "http://nhietkethongminh.com/dieukhoan.html";
		public static final String QA_URL = "http://nhietkethongminh.com/cauhoithuonggap.html";
		public static final String NEWS_URL = "http://nhietkethongminh.com/tintuc.html";



		public static final String DATE_FORMAT_INBOX = "yyyy-MM-dd'T'HH:mm:ss";
//		public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
//		public static final String DATE_FORMAT = "yyyyMMddHHmmss";
		public static final String DATE_FORMAT = "yyyyMMddhhmmss";
	}

	public static interface BroadcastAction {
		public static final String REFRESH_DATA = "REFRESH_DATA";
	}

	public static interface CoinmarkCalendar {

		public static final String DOMAIN = "https://api.coinmarketcal.com";
		public static final String AUTH_CONTEXT = "/oauth/v2/token";
		public static final String RESOUCE_CONTEXT = "/v1/";
		public static final String BASE_RESOURCE_URL = DOMAIN + RESOUCE_CONTEXT;

		public static final String CLIENT_ID = "350_2161q7z084m8c0ccgosk8oowgwsksowsso8swsskcw0s4cok0k";
		public static final String CLIENT_SECRET = "5y80a1htqfwg0w4wks08ko4g8s8880s8ss40wk8o0sog08ko44";
		public static final String AUTHEN_URL = DOMAIN + AUTH_CONTEXT;

	}

	public static interface AppConfig {
		public static boolean isTestAccount = true;
//		public static boolean isTestAccount = false;
	}

	public static final String DEVICE_SERVICE_UDID = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
	public static final String DEVICE_SERVICE_UDID_1 = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E";

	public static final String BUNDLE_KEY_CM_PRICE_DATA = "BUNDLE_KEY_CM_PRICE_DATA";
	public static final String BUNDLE_KEY_GLOBAL_DATA = "BUNDLE_KEY_GLOBAL_DATA";
	public static final String BUNDLE_KEY_ORDER_ID = "ORDER_ID";
	public static final String BUNDLE_KEY_FROM_NOTIFY = "FROM_NOTIFY";
	public static final String BUNDLE_KEY_IS_COFIRM = "IS_CONFIRM";
	public static final String BUNDLE_KEY_IS_UPDATE = "IS_UPDATE";

	public static final String BUNDLE_KEY_CONVERSATION = "CONVERSATION";
	public static final String BUNDLE_KEY_MODE_CREATE_CUSTOMER = "CREATE_CUSTOMER";
	public static final String BUNDLE_KEY_CUSTOMER_ID = "CUSTOMER_ID";
	public static final String BUNDLE_KEY_COLLEAGUE_ID = "COLLEAGUE_ID";
	public static final String BUNDLE_KEY_GROUP_ID = "GROUP_ID";
	public static final String BUNDLE_KEY_GROUP_NAME = "GROUP_NAME";
	public static final String BUNDLE_KEY_COLLEAGUE_ACTION = "COLLEAGUE_ACTION";
	public static final String BUNDLE_KEY_LIST_TYPE = "LIST_TYPE";
	public static final String BUNDLE_KEY_SUCCESS = "SUCCESS";
	public static final String BUNDLE_KEY_LIST_MEMBER = "LIST_MEMBER";
	public static final String BUNDLE_KEY_CONTACT = "CONTACT";
	public static final String BUNDLE_KEY_LIST_CONTACT = "LIST_CONTACT";
	public static final String BUNDLE_KEY_MODE = "MODE";

	public static final int REQUEST_CODE_DETAIL = 9669;
	public static final int REQUEST_CODE_CREATE = 6996;
	public static final int REQUEST_CODE_ACCOUNT = 6699;

	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat simpleDateFormatDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String dateFormateSendServer = "dd-MM-yyyy HH:mm:ss";
	public static final SimpleDateFormat simpleDateSendServer = new SimpleDateFormat(dateFormateSendServer);

	public static String[] dayDisplay, dayToServer;

	private static GlobalInfo instance = null;
	Context appContext;// application context
	Context activityContext;// activity context
	
	public static LayoutInflater globalInflater = null;
	public static Activity globalActivity = null;
	
	public static void setActivity(Activity activity) {
		if(globalActivity == null) {
			globalActivity = activity;
		}
	}
	
	public static void setLayoutInflater(LayoutInflater layoutInflater) {
		if(globalInflater == null) {
			globalInflater = layoutInflater;
		}
	}

	public static GlobalInfo getInstance() {
		if (instance == null) {
			instance = new GlobalInfo();
		}
		return instance;
	}

	public void setAppContext(Context context) {
		this.appContext = context;

	}

	public Context getAppContext() {
		if (appContext == null) {
			appContext = new GlobalInfo();
		}
		return appContext;
	}

	public void setActivityContext(Context context) {
		this.activityContext = context;
	}

	public Context getActivityContext() {
		return activityContext;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
//		DeployGate.install(this);
	}


}
