package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class AddDirectMemberRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public int voiceConferenceId;
		public String phoneNumber;
		public String memberName;
	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.voiceConferenceId = Integer.parseInt(data[2]);
		bodyObject.phoneNumber = data[3];
		bodyObject.memberName = data[4];
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "voiceConferenceController/apiAddMember";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();

		try {
			String json = gson.toJson(bodyObject, BodyObject.class);

			Log.e("BodyParam: ", json);
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
