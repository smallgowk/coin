package com.phanduy.model.request;

import android.graphics.Bitmap.CompressFormat;

import com.phanduy.GlobalInfo;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;

import java.io.ByteArrayOutputStream;

public class UploadAvatarRequest extends BasePostMultipartRequest {

	@Override
	public String genUrl() {
		return GlobalInfo.ServerConfig.RESOURCE_SITE + "api/files";
	}

	@Override
	public MultipartEntity genMultipartEntity() {

		if (bitmap == null) {
			return null;
		}

		MultipartEntity reqEntity = new MultipartEntity();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 10, bos);
		byte[] data = bos.toByteArray();
		ByteArrayBody bab = new ByteArrayBody(data, System.currentTimeMillis() + ".jpg");
		reqEntity.addPart("avatar", bab);

		return reqEntity;
	}
}
