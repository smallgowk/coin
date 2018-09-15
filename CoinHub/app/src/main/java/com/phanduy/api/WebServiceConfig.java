/*
 * Name: $RCSfile: WebServiceConfig.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 2:37:15 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.phanduy.api;

/**
 * WebServiceConfig class contains web service settings
 * 
 * @author Lemon
 */
public final class WebServiceConfig {
	// Network tempCreatedDate out: 15s
	public static int NETWORK_TIME_OUT = 15000;

	// result code for activity result
	public static int RESULT_OK = 1;

	// ===================== DOMAIN =====================
	public static String PROTOCOL_HTTP = "http:";

//	public static String PROTOCOL_HTTPS = "https:";
	
	public static String language = "vi";
//	public static String language = "en";
//	
//	public static String DOMAIN = "https://smartmotor.viettel.vn/";
//	public static String DOMAIN = "http://smartmotor.viettel.vn/";
	
//	public static String DOMAIN = "http://203.113.138.115/";
//	public static String DOMAIN = "http://192.168.153.1:9800/";
//	public static String DOMAIN = "http://103.1.210.161:9000/";
//	public static String DOMAIN = "http://103.1.210.161:9600/";
//	public static String DOMAIN = "http://103.1.210.161:9400/";
//	public static String DOMAIN = "http://103.1.210.161:9800/";
	public static String DOMAIN = "http://103.1.210.135:9115/";
//	public static String DOMAIN = "http://10.61.75.58:8080/";
//	public static String DOMAIN = "http://localhost:8080/";
//	public static String DOMAIN = "http://smartmotor.vn:8080/";
//	public static String DOMAIN = "http://103.1.210.161:9000/";
//	public static String DOMAIN = "//10.0.2.2:8080/";
	
	public static String CONTEXT_ROOT = "mtapi";
	
	public static String DOMAIN_CHARGING = DOMAIN + CONTEXT_ROOT;
//	public static String CONTEXT_ROOT = "mtapiformobile";

//	public static String APP_DOMAIN = PROTOCOL_HTTPS + DOMAIN + CONTEXT_ROOT  + "/rest/mobile/";
	public static String APP_DOMAIN = DOMAIN + CONTEXT_ROOT  + "/rest/mobile/";
	public static String CAPCHA_URL = DOMAIN_CHARGING  + "/jcaptcha.jpg";
	
	public static String URL_AUTHEN_HTTPS = DOMAIN + CONTEXT_ROOT + "/rest/auth/login/";
//	public static String URL_AUTHEN_HTTPS = DOMAIN + CONTEXT_ROOT + "/rest/auth/newlogin/";
	public static String URL_CHANGE_PASS = DOMAIN + CONTEXT_ROOT + "/rest/auth/";
	public static String URL_LOGOUT_HTTPS = DOMAIN + CONTEXT_ROOT + "/rest/auth/logout/";
	
	public static String WSDL_URL = DOMAIN + "/mgw/mobilegw?WSDL";
//	public static String WSDL_URL = "http:" + DOMAIN + "/mgwcam/mobilegw?WSDL";
	
	public static String WSDL_NAME_SPACE = "http://mobilegateway.viettel.com/";
	public static String WSDL_METHOD_NAME = "doCommand";
	
//	public static String URL_AUTHEN_HTTPS = "http://103.1.210.157:9000/mtapi/rest/auth/login/";
//	public static String URL_AUTHEN_LOCAL = "http://10.0.2.2:8080/mtapi/rest/auth";
//	public static String URL_AUTHEN_LOCAL = "http://10.0.2.2:8080/mtapi/login";
	
//	public static String TCP_SERVER = "103.1.210.135";
//	public static int TCP_PORT = 9116;
	
	public static String TCP_SERVER = "125.235.40.29";
	public static int TCP_PORT = 9009;
	public static int TIME_OUT = 20;
	
	public static String[] RESPONSE_EMAILS = new String[]{"cskh@viettel.com.vn"};
	
	// ===================== PARAMETER ================
	public static String PARAM_USER = "username";
	public static String PARAM_PASSWORD = "password";

	// ====================== RESPONSE ================

	public static String RESPONSE_LOGIN_FAIL = "false";
	public static String RESPONSE_REGISTER_EXIST_EMAIL = "User is avaiable";
	public static String RESPONSE_REGISTER_SUCCESS = "success";
	public static String RESPONSE_REGISTER_FAIL = "";
	
}
