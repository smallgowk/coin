package com.phanduy.model.request;

import java.util.ArrayList;

public abstract class BaseGetRequest {
	
    protected ArrayList<String> params;
	
	public abstract String genUrl();
	
	public void setParams(String... params) {
		this.params = new ArrayList<>();
		for(String p : params) {
			this.params.add(p);
		}
	}
    
}
