package com.phanduy.model.request;

import com.phanduy.api.http.RequestParams;

import java.util.ArrayList;

public abstract class BasePutRequestParams {
	
    protected ArrayList<String> urlParams;
    protected ArrayList<String> requestParams;
    
	public abstract String genUrl();
	public abstract RequestParams genRequestParams();
    
	public void setUrlParams(String... params) {
		this.urlParams = new ArrayList<>();
		for(String p : params) {
			this.urlParams.add(p);
		}
	}
	public void setRequsetParams(String... params) {
		if(this.requestParams == null) {
			this.requestParams = new ArrayList<>();
		}
		for(String p : params) {
			this.requestParams.add(p);
		}
	}
    
}
