package com.phanduy.model.request;

import com.phanduy.GlobalInfo;
import com.phanduy.api.http.RequestParams;

import java.util.ArrayList;

public abstract class BasePostRequestParams {

	protected String apiCode;

    protected ArrayList<String> urlParams;
    protected ArrayList<String> requestParams;
    
	public String genUrl(){
		return GlobalInfo.ServerConfig.RESOURCE_SITE + apiCode;
	}
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

	public void setRequsetParams(ArrayList<String> list) {
		if(this.requestParams == null) {
			this.requestParams = new ArrayList<>();
		}
		this.requestParams.addAll(list);
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
}
