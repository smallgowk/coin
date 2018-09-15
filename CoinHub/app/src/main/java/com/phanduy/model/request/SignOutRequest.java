package com.phanduy.model.request;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class SignOutRequest extends BasePostRequestEntity {

	ApiObject bodyObject;

	public void setData(String... data) {
		bodyObject = new ApiObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "loginController/apiLogOut";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();

		try {
			String json = gson.toJson(bodyObject, ApiObject.class);
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