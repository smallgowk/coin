package com.phanduy.view.activity.authen;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.rengwuxian.edittext.validation.METValidator;
import com.phanduy.coinhub.R;
import com.phanduy.view.base.BABaseActivity;
import com.phanduy.view.custom.EdittextWithFont;

public class ForgotPassActivity extends BABaseActivity {

	EdittextWithFont edtEmail;
	
	private RelativeLayout layoutLoading;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_activity_forgot_password);
		initView();
	}


	public void initView() {


		edtEmail = (EdittextWithFont) findViewById(R.id.edtEmail);
		edtEmail.addValidator(new METValidator(getResources().getString(R.string.errorEmptyEmail)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
				return !isEmpty;
			}
		});

		layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);

	}

	public void onClickBack(View v) {
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

	public void showLoading() {
		layoutLoading.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		layoutLoading.setVisibility(View.GONE);
	}
	
	public void onClickGetPass(View view) {
//		String email = edtEmail.getEditableText().toString().trim();
//
//		if (email.length() == 0) {
//			edtEmail.validate();
//			showKeyBoard(edtEmail);
//			return;
//		}
//
//		showLoading();
//
//		ForgotPasswordObject forgotPasswordObject = new ForgotPasswordObject();
//		forgotPasswordObject.setEmail(email);
//
//		PostEntityResourceRequest postEntityResourceRequest = new PostEntityResourceRequest();
//		postEntityResourceRequest.setData(forgotPasswordObject, ForgotPasswordObject.class);
//
//		ApiController.doPostRequest(this, postEntityResourceRequest, new ResponseListener() {
//			@Override
//			public void processResponse(String response) {
//				hideLoading();
//
//				Gson gson = new Gson();
//				ResponseObject responseObject = gson.fromJson(response, ResponseObject.class);
//
//				if(responseObject.getHttpStatus() == 200) {
//					new MaterialDialog.Builder(ForgotPassActivity.this)
//							.title(getResources().getString(R.string.alertGetPasswordSuccess))
//							.positiveText(getResources().getString(R.string.labelClose))
//							.show();
//				} else {
//					Toast.makeText(ForgotPassActivity.this, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
//				}
//			}
//
//			@Override
//			public void processResponse(int error, String content) {
//				hideLoading();
//				Gson gson = new Gson();
//				try {
//					ErrorResponse errorResponse = gson.fromJson(content, ErrorResponse.class);
//
//					String message = "";
//
//					if (errorResponse != null && !StringUtility.isEmpty(errorResponse.getMessage())) {
//						message = errorResponse.getMessage();
//					} else {
//						message = getResources().getString(R.string.alertAcctionUnsucess);
//					}
//					Toast.makeText(ForgotPassActivity.this, message, Toast.LENGTH_SHORT).show();
//				} catch (JsonSyntaxException e) {
//					Toast.makeText(ForgotPassActivity.this, R.string.alertAcctionUnsucess, Toast.LENGTH_SHORT).show();
//				}
//
//			}
//		});

	}

	@Override
	public void onKeyBack() {
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

}
