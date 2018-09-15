package com.phanduy.model.request;

import android.graphics.Bitmap;

import org.apache.http.entity.mime.MultipartEntity;

import java.util.ArrayList;

public abstract class BasePostMultipartRequest {
	
    protected ArrayList<String> requestParams;
    protected Bitmap bitmap;
	
	public abstract String genUrl();
	public abstract MultipartEntity genMultipartEntity();
    
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setRequsetParams(String... params) {
		this.requestParams = new ArrayList<>();
		for(String p : params) {
			this.requestParams.add(p);
		}
	}
    
}
