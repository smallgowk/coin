package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;
import com.phanduy.model.ColleagueModel;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreateColleagueGroupRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public String groupName;
		ArrayList<ColleagueModel> listMember;
	}

	public void setData(ArrayList<ColleagueModel> list,String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.groupName = data[2];
		bodyObject.listMember = list;
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "colleagueController/apiCreateGroup";
	}

	@Override
	public HttpEntity genHttpEntity() {
		ByteArrayEntity baEntity = null;
		Gson gson = new Gson();

		try {
			String json = gson.toJson(bodyObject, BodyObject.class);
			Log.e("BodyParam", json);
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
