package com.phanduy.interfaces;


public interface ResponseListener {
	public void processResponse(String response);
	public void processResponse(int error, String content);
}
