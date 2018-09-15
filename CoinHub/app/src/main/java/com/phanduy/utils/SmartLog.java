package com.phanduy.utils;

import android.util.Log;

public class SmartLog {

	private static boolean DEBUG = true;
//	private static boolean DEBUG = false;

	public static void logInfo(String tag, String message) {
		if (DEBUG) {
			Log.i(tag, message);
		}
	}

	public static void log(String tag, String message) {
		if (DEBUG) {
			Log.e(tag, message);
		}
	}

	public static void forceLog(String tag, String message) {
		Log.e(tag, message);
	}

	public static void log(String tag, String message, Throwable tr) {
		if (DEBUG) {
			Log.e(tag, message, tr);
		}
	}
}
