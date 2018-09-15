package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class GetColleagueInfoRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public int groupId;
		public int colleagueId;
	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.groupId = Integer.parseInt(data[2]);
		bodyObject.colleagueId = Integer.parseInt(data[3]);
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "colleagueController/apiGetColleague";
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
