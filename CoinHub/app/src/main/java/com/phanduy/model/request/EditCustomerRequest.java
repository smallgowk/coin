package com.phanduy.model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

public class EditCustomerRequest extends BasePostRequestEntity {

	BodyObject bodyObject;

	class BodyObject extends ApiObject{
		public String customerName;
		public String numberOfExt;
		public String companyName;
		public String position;
		public String phoneNumber;
		public String phoneNumber2;
		public String phoneNumber3;
		public String email;
		public int customerId;
	}

	public void setData(String... data) {
		bodyObject = new BodyObject();
		bodyObject.token = data[0];
		bodyObject.numberOfCompany = data[1];
		bodyObject.customerName = data[2];
		bodyObject.numberOfExt = data[3];
		bodyObject.companyName = data[4];
		bodyObject.position = data[5];
		bodyObject.phoneNumber = data[6];
		bodyObject.phoneNumber2 = data[7];
		bodyObject.phoneNumber3 = data[8];
		bodyObject.email = data[9];
		bodyObject.customerId = Integer.parseInt(data[10]);
	}

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.OAUTH_SITE + "customerController/apiUpdateCustomer";
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
