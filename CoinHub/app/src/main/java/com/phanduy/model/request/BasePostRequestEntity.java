package com.phanduy.model.request;

import org.apache.http.HttpEntity;

public abstract class BasePostRequestEntity {

	public abstract String genUrl();
	public abstract HttpEntity genHttpEntity();

    
}
