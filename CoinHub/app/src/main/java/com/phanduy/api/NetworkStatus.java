/*
 * Name: $RCSfile: AsyncHttpBase.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 4:21:50 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.phanduy.api;



/**
 * AsyncHttpBase is base class for AsyncHttpGet and AsyncHttpPost class
 * 
 * @author Lemon
 */
public class NetworkStatus {
	public static final int NETWORK_STATUS_OK = 0;
	public static final int NETWORK_STATUS_OFF = 1;
	public static final int NETWORK_STATUS_ERROR = 2;
	public static final int NETWOTK_STATUS_TIME_OUT = 3;
	public static final int NETWORK_STATUS_CREATE_ERROR = 4;
	public static final int NO_DATA = 5;
	public static final int CONNECT_ERROR = 6;
	public static final int DATA_ERROR = 7;
	public static final int INVALID_SESSION = 8;

}
