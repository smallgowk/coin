package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class SignInRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject {
		public String grant_type = "authorization_code";
		public String username;
		public String password;
		public String code;
		public String client_id = "1144800ea220ca350ec826f35507fbf1aae4624e92f46bea53074e72fef3c5a1";
		public String client_secret = "3f8db6a6118c1597e615bb8aa22724fad8dc1162341535d4146a86a8c34f46de";
		public String redirect_uri = "https://www.coinbase.com";

	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.username = data[0];
		bodyObject.password = data[1];
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "token";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();

		try {
			String json = gson.toJson(bodyObject, BodyObject.class);
			Log.e("BodyParams", json);
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
