package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class GetConversationListRequest extends BasePostRequestEntity {

	ApiObject bodyObject;


	public void setData(String... data) {
		bodyObject = new ApiObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "voiceConferenceController/apiGetListMeeting";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();



		try {
			String json = gson.toJson(bodyObject, ApiObject.class);
			Log.e("BodyParam","" + json);
			baEntity = new ByteArrayEntity(json.getBytes("UTF8"));
            baEntity.setContentEncoding("application/json");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return baEntity;
	}

}
