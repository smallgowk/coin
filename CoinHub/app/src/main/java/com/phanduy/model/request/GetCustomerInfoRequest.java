package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class GetCustomerInfoRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public int customerId;
	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.customerId = Integer.parseInt(data[2]);
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "customerController/apiGetCusInfo";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();



		try {
			String json = gson.toJson(bodyObject, BodyObject.class);
			Log.e("BodyParam","" + json);
			baEntity = new ByteArrayEntity(json.getBytes("UTF8"));
            baEntity.setContentEncoding("application/json");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return baEntity;
	}

}
