package com.phanduy.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSharePreference {

	//Authen
	public static final String SHIPS_LOGIN_STATUS = "SHIPS_LOGIN_STATUS";
	public static final String SHIPS_ACCESS_TOKEN = "SHIPS_ACCESS_TOKEN";
	public static final String SHIPS_REFRESH_TOKEN = "SHIPS_REFRESH_TOKEN";
	public static final String SHIPS_TYPE_TOKEN = "SHIPS_TYPE_TOKEN";
	public static final String SHIPS_EXPIRED_IN = "SHIPS_EXPIRED_IN";
	public static final String SHIPS_EXPIRED_AT = "SHIPS_EXPIRED_AT";
	public static final String SHIPS_ACCOUNT_TYPE = "SHIPS_ACCOUNT_TYPE";
	public static final String SHIPS_PHONE_NUMBER_FROM = "SHIPS_PHONE_NUMBER_FROM";

	
	public static final String SHIPS_LAST_LAT = "SHIPS_LAST_LAT";
	public static final String SHIPS_LAST_LNG = "SHIPS_LAST_LNG";

	public static final String SHIPS_FROM_PLACE = "SHIPS_FROM_PLACE";
	public static final String SHIPS_TO_PLACE = "SHIPS_TO_PLACE";

	public static final String ALERT_TEMPLE = "ALERT_TEMPLE";
	public static final String IS_ALERT = "IS_ALERT";
	public static final String IS_VIBRATE = "IS_VIBRATE";
	public static final String ALERT_REPEAT_TIME = "ALERT_REPEAT_TIME";
	public static final String ALERT_TONE = "ALERT_TONE";
	public static final String TIME_RELOAD = "TIME_RELOAD";

	// Account
	public static final String NUM_OF_COMPANY = "NUM_OF_COMPANY";
//	public static final String SHIPS_USER_ID = "SHIPS_USER_ID";
	public static final String SHIPS_CLIENT_ID = "SHIPS_CLIENT_ID";
	public static final String FB_ACCESS_TOKEN = "FB_ACCESS_TOKEN";
	public static final String BABY_ID = "BABY_ID";
	public static final String SHIPS_USERNAME = "SHIPS_USERNAME";
	public static final String EMAIl = "EMAIl";
	public static final String SHIPS_FULLNAME = "SHIPS_FULLNAME";
	public static final String CALL_BACK = "CALL_BACK";

	public static final String FIRST_RUNNED = "FIRST_RUNNED";

	public static final String SO_GROUP_ROOT = "SO_GROUP_ROOT";
	public static final String SO_ROLE_ID = "SO_ROLE_ID";

	private static final String REGISTRATION_ID = "DOO_REGISTRATION_ID";
	public static final String LIST_COIN = "LIST_COIN";
	// GioiNH -- Luu them Setting

	private static AppSharePreference instance;
	private Context context;

	public static AppSharePreference getInstance(Context context) {
		if (instance == null) {
			instance = new AppSharePreference();
			instance.context = context;
		}
		return instance;
	}

	// ======================== CORE FUNCTIONS ========================

	/**
	 * Save a long integer to SharedPreferences
	 * 
	 * @param key
	 * @param n
	 */
	public void putLongValue(String key, long n) {
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, n);
		editor.commit();
	}

	/**
	 * Read a long integer to SharedPreferences
	 *
	 * @param key
	 * @return
	 */
	public long getLongValue(String key) {
		// SmartLog.log(TAG, "Get long integer value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getLong(key, 0);
	}

	/**
	 * Save an integer to SharedPreferences
	 *
	 * @param key
	 * @param n
	 */
	public void putIntValue(String key, int n) {
		// SmartLog.log(TAG, "Set integer value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, n);
		editor.commit();
	}

	/**
	 * Read an integer to SharedPreferences
	 *
	 * @param key
	 * @return
	 */
	public int getIntValue(String key) {
		// SmartLog.log(TAG, "Get integer value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getInt(key, -1);
	}

	/**
	 * Save an string to SharedPreferences
	 *
	 * @param key
	 * @param s
	 */
	public void putStringValue(String key, String s) {
		// SmartLog.log(TAG, "Set string value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(key, s);
		editor.commit();
	}

	/**
	 * Read an string to SharedPreferences
	 *
	 * @param key
	 * @return
	 */
	public String getStringValue(String key) {
		// SmartLog.log(TAG, "Get string value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getString(key, "");
	}

	/**
	 * Read an string to SharedPreferences
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getStringValue(String key, String defaultValue) {
		// SmartLog.log(TAG, "Get string value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getString(key, defaultValue);
	}

	/**
	 * Save an boolean to SharedPreferences
	 *
	 * @param key
	 * @param s
	 */
	public void putBooleanValue(String key, Boolean b) {
		// SmartLog.log(TAG, "Set boolean value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, b);
		editor.commit();
	}

	/**
	 * Read an boolean to SharedPreferences
	 *
	 * @param key
	 * @return
	 */
	public boolean getBooleanValue(String key) {
		// SmartLog.log(TAG, "Get boolean value");
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getBoolean(key, false);
	}

	/**
	 * Save an float to SharedPreferences
	 *
	 * @param key
	 * @param s
	 */
	public void putFloatValue(String key, float f) {
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putFloat(key, f);
		editor.commit();
	}

	/**
	 * Read an float to SharedPreferences
	 *
	 * @param key
	 * @return
	 */
	public float getFloatValue(String key, float defaultValue) {
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		return pref.getFloat(key, defaultValue);
	}

	public void clear() {
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);

		Editor prefsPrivateEditorA = pref.edit();
		// prefsPrivateEditor.remove(SMAS_USER_NAME);
		prefsPrivateEditorA.clear();

		prefsPrivateEditorA.commit();
	}

	// ====================== Bussiness Funtions =======================

	/**
	 * Quan ly trang thai login
	 *
	 * @param status
	 */
	public void putLoginStatus(boolean status) {
		putBooleanValue(SHIPS_LOGIN_STATUS, status);
	}

	public boolean getLoginStatus() {
		return getBooleanValue(SHIPS_LOGIN_STATUS);
	}

	/**
	 * Quan ly Request Token
	 *
	 * @param requestTOken
	 */
	public void putRequestToken(String requestTOken) {
		putStringValue(SHIPS_ACCESS_TOKEN, requestTOken);
	}

	public String getRequestToken() {
		return getStringValue(SHIPS_ACCESS_TOKEN, null);
	}

	public String getAccessToken() {
		return getStringValue(SHIPS_ACCESS_TOKEN, null);
	}

	/**
	 * Quan ly User Id
	 *
	 * @param userId
	 */
//	public void putUserId(int userId) {
//		putIntValue(SHIPS_USER_ID, userId);
//	}
//
//	public int getUserId() {
//		return getIntValue(SHIPS_USER_ID);
//	}
//	public void putUserId(int userId) {
//		putIntValue(SHIPS_USER_ID, userId);
//	}

	public String getUserId() {
		return getStringValue(NUM_OF_COMPANY);
	}

	/**
	 * Quan ly User name
	 *
	 * @param userName
	 */
	public void putUserName(String userName) {
		putStringValue(SHIPS_USERNAME, userName);
	}

	public String getUserName() {
		return getStringValue(SHIPS_USERNAME);
	}

	public void putFullName(String fullName) {
		putStringValue(SHIPS_FULLNAME, fullName);
	}

	public String getFullName() {
		return getStringValue(SHIPS_FULLNAME);
	}
	public void putCallback(String callback) {
		putStringValue(CALL_BACK, callback);
	}

	public String getCallback() {
		return getStringValue(CALL_BACK);
	}

	public void putGroupRoot(int groupRoot) {
		putIntValue(SO_GROUP_ROOT, groupRoot);
	}

	public int getGroupRoot() {
		return getIntValue(SO_GROUP_ROOT);
	}

	public void putRoleId(int roleId) {
		putIntValue(SO_ROLE_ID, roleId);
	}

	public int getRoleId() {
		return getIntValue(SO_ROLE_ID);
	}

	public void putDooRegistrationId(String registrationId) {
		putStringValue(REGISTRATION_ID, registrationId);
	}

	public String getDooRegistrationId() {
		return getStringValue(REGISTRATION_ID);
	}

	public int getBabyId() {
		return getIntValue(BABY_ID);
	}

	public void logoutUser() {
		SharedPreferences pref = context.getSharedPreferences(
				GlobalValue.APP_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();

		editor.remove(SHIPS_LOGIN_STATUS);
		editor.remove(SHIPS_ACCESS_TOKEN);
		editor.remove(NUM_OF_COMPANY);
		editor.remove(SHIPS_USERNAME);
		
		editor.clear();

		editor.commit();

	}
}
