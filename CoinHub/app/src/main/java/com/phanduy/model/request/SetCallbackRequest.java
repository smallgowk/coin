package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class SetCallbackRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public int isCallBack;
	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.isCallBack = Integer.parseInt(data[2]);
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "loginController/apiCallBack";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();

		Log.e("Token","" + bodyObject.token);

		try {
			String json = gson.toJson(bodyObject, BodyObject.class);
			baEntity = new ByteArrayEntity(json.getBytes("UTF8"));
//             baEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
//             "application/json"));
//			baEntity.setContentEncoding("application/x-www-form-urlencoded");
            baEntity.setContentEncoding("application/json");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return baEntity;
	}

}
