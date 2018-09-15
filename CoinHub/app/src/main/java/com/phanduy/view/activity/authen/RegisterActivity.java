package com.phanduy.view.activity.authen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.phanduy.view.activity.MainHomeActivity;
import com.rengwuxian.edittext.validation.METValidator;
import com.rey.material.widget.CheckBox;
import com.phanduy.GlobalInfo;
import com.phanduy.coinhub.R;
import com.phanduy.store.AppSharePreference;
import com.phanduy.utils.DialogUtility;
import com.phanduy.utils.FontTypeface;
import com.phanduy.view.base.BABaseActivity;
import com.phanduy.view.custom.EdittextWithFont;
import com.phanduy.view.custom.TextViewWithFont;

public class RegisterActivity extends BABaseActivity {


//	private LinearLayout layoutRegister;

	private RelativeLayout layoutLoading;

	private LinearLayout btnAgreePolicy;
	private TextViewWithFont txtAgreementTitle;
	private CheckBox checkBox;
	
	private Button buttonRegister;

	EdittextWithFont edtAccount, edtEmail, edtPhone, edtPassword, edtConfirmPassword;

	public AppSharePreference appSharePreference;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appSharePreference = AppSharePreference.getInstance(this);

//		ConfigUtility.getConfig(this);

		setContentView(R.layout.layout_activity_register);

		initView();
	}

	private void initView() {

		buttonRegister = (Button) findViewById(R.id.buttonRegister);
		edtAccount = (EdittextWithFont) findViewById(R.id.edtAccount);
		edtEmail = (EdittextWithFont) findViewById(R.id.edtEmail);
		edtPhone = (EdittextWithFont) findViewById(R.id.edtPhone);
		edtPassword = (EdittextWithFont) findViewById(R.id.edtPassword);
		edtConfirmPassword = (EdittextWithFont) findViewById(R.id.edtConfirmPassword);

		edtAccount.setTypeface(FontTypeface.getTypecace(this, FontTypeface.FONT_ROBOTO_REGULAR));
		edtPhone.setTypeface(FontTypeface.getTypecace(this, FontTypeface.FONT_ROBOTO_REGULAR));
		edtPassword.setTypeface(FontTypeface.getTypecace(this, FontTypeface.FONT_ROBOTO_REGULAR));
		edtConfirmPassword.setTypeface(FontTypeface.getTypecace(this, FontTypeface.FONT_ROBOTO_REGULAR));

		edtAccount.addValidator(new METValidator(getResources().getString(R.string.errorEmptyAccount)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
				return !isEmpty;
			}
		});
//		edtAccount.setAutoValidate(true);
		edtEmail.addValidator(new METValidator(getResources().getString(R.string.errorEmptyEmail)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
				return !isEmpty;
			}
		});
//		edtEmail.setAutoValidate(true);
		edtPhone.addValidator(new METValidator(getResources().getString(R.string.errorEmptyPhone)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
				return !isEmpty;
			}
		});
//		edtPhone.setAutoValidate(true);
		edtPassword.addValidator(new METValidator(getResources().getString(R.string.errorEmptyPassword)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
				return !isEmpty;
			}
		});
//		edtPassword.setAutoValidate(true);
		edtConfirmPassword.addValidator(new METValidator(getResources().getString(R.string.errorEmptyConfirmPassword)) {

			@Override
			public boolean isValid(CharSequence text, boolean isEmpty) {
//				if(isEmpty)
				if(isEmpty) {
					setErrorMessage(getResources().getString(R.string.errorEmptyConfirmPassword));
					return false;
				}
				if(!text.toString().equals(edtPassword.getText().toString())) {
					setErrorMessage(getResources().getString(R.string.errorPasswordNotMatch));
					return false;
				}
				return true;
			}
		});
//		edtConfirmPassword.setAutoValidate(true);

//		layoutRegister = (LinearLayout) findViewById(R.id.layoutRegister);
//		layoutRegister.setVisibility(View.VISIBLE);

		layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);
		layoutLoading.setVisibility(View.GONE);

		txtAgreementTitle = (TextViewWithFont) findViewById(R.id.textAgreementTitle);

		String txtPolicy1 = getResources().getString(R.string.txtRegitserPolicyPart1);
		String txtPolicy2 = getResources().getString(R.string.txtRegitserPolicyPart2);
		int index = txtPolicy1.indexOf(txtPolicy2);
		SpannableString content = new SpannableString(txtPolicy1);
		content.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.secondary)), index,index + txtPolicy2.length(), 0);
		txtAgreementTitle.setText(content);

		checkBox = (CheckBox) findViewById(R.id.checkBox);

		checkBox.setChecked(true);

		btnAgreePolicy = (LinearLayout) findViewById(R.id.btnAgreePolicy);
		btnAgreePolicy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalInfo.ServerConfig.POLICY_URL));
				startActivity(browserIntent);
			}
		});
	}


	public void onClickBtnRegister(View v) {
		onClickRegister();
	}

	private View.OnKeyListener onKeyListner = new View.OnKeyListener() {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {

			int action = event.getAction();

			if (action == KeyEvent.ACTION_UP) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_ENTER:
					if (v instanceof EditText) {
						hideKeyBoard((EditText) v);
						onClickRegister();
					}
					break;
				}
			}
			return false;
		}
	};

	private void onClickRegister() {

		String username = edtAccount.getEditableText().toString().trim();
		String phoneNumber = edtPhone.getEditableText().toString().trim();
		String email = edtEmail.getEditableText().toString().trim();
		String password = edtPassword.getEditableText().toString();
		String confirmPassword = edtConfirmPassword.getEditableText().toString();

		if (username.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", RegisterActivity.this.getResources().getString(R.string.text_empty_account), RegisterActivity.this.getResources()
//					.getString(R.string.close_button), null);
			edtAccount.validate();
			showKeyBoard(edtAccount);

			return;
		}
		if (email.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", "Vui lòng nhập email!", RegisterActivity.this.getResources().getString(R.string.close_button), null);
			edtEmail.validate();
			showKeyBoard(edtEmail);
			return;
		}
		if (phoneNumber.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", "Vui lòng nhập số điện thoại!", RegisterActivity.this.getResources().getString(R.string.close_button), null);
			edtPhone.validate();
			showKeyBoard(edtPhone);
			return;
		}

		if (password.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", "Vui lòng nhập mật khẩu!", RegisterActivity.this.getResources().getString(R.string.close_button), null);
			edtPassword.validate();
			showKeyBoard(edtPassword);
			return;
		}
//		if (password.length() < 6 || password.length() > 16) {
//			DialogUtility.showDialogAlert(this, "", "Mật khẩu phải có độ dài từ 6-16 kí tự", RegisterActivity.this.getResources().getString(R.string.close_button),
//					null);
//			showKeyBoard(editTextPass);
//			return;
//		}
		if (confirmPassword.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", "Vui lòng xác nhận mật khẩu!", RegisterActivity.this.getResources().getString(R.string.close_button), null);
			edtConfirmPassword.validate();
			showKeyBoard(edtConfirmPassword);
			return;
		}

		if (!password.equals(confirmPassword)) {
//			DialogUtility.showDialogAlert(this, "", "Mật khẩu và xác nhận mật khẩu không khớp!", RegisterActivity.this.getResources()
//					.getString(R.string.close_button), null);
			edtConfirmPassword.validate();
			showKeyBoard(edtConfirmPassword);
			return;
		}
//
		if(!checkBox.isChecked()) {
			DialogUtility.showDialogAlert(this, "", getResources().getString(R.string.alertNotAgreePolyci), RegisterActivity.this.getResources()
					.getString(R.string.close_button), null);
			return;
		}
//
		doRegister(username, password, confirmPassword, email, phoneNumber);

	}

	public void onLoginError(String text) {
//		layoutRegister.clearAnimation();
//		layoutRegister.setVisibility(View.VISIBLE);
		layoutLoading.setVisibility(View.GONE);
//		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		DialogUtility.showDialogAlert(this, "", text, "", null);
	}

	public void doRegister(final String username, final String password, String confirmPassword, String email, String phoneNumber) {
//		layoutRegister.clearAnimation();
//		layoutRegister.setVisibility(View.GONE);
//		layoutError.setVisibility(View.GONE);
//		layoutLoading.setVisibility(View.VISIBLE);
//
//		// if (GlobalInfo.TYPE_APP.equals(GlobalInfo.TYPE_APP_SHOP)) {
//
//		SignUpRequest registerRequest = new SignUpRequest();
//
//		SignUpObject registerObject = new SignUpObject();
//		registerObject.setParams(username, email, phoneNumber, password, confirmPassword);
//		registerRequest.setRegisterObject(registerObject);
//
//		ApiController.doPostRequest(this, registerRequest, new ResponseListener() {
//			@Override
//			public void processResponse(String response) {
////				layoutLoading.setVisibility(View.GONE);
//
//				Gson gson = new Gson();
//				ResponseObject responseObject = gson.fromJson(response, ResponseObject.class);
//
//				if(responseObject.getHttpStatus() == 200) {
//
//
//
////					new MaterialDialog.Builder(RegisterActivity.this)
////							.title(getResources().getString(R.string.txtRegitserSuccessLabel))
////							.positiveText(getResources().getString(R.string.labelAccept))
////							.onPositive(new MaterialDialog.SingleButtonCallback() {
////								@Override
////								public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////									if (!RegisterActivity.this.isFinishing()) {
////										finish();
////										startActivity(new Intent(RegisterActivity.this.getBaseContext(), LoginActivity.class));
////										overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
////									}
////								}
////							})
////							.show();
//
//					doAuthen(username, password);
//
//				} else {
//					layoutLoading.setVisibility(View.GONE);
//					Toast.makeText(RegisterActivity.this, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
//				}
//
////				DialogUtility.showDialogAlert(RegisterActivity.this, getResources().getString(R.string.txtRegitserSuccessLabel), getResources().getString(R.string.txtRegitserSuccessMessage), "", new DialogListener() {
////
////					@Override
////					public void after() {
////						if (!RegisterActivity.this.isFinishing()) {
////							finish();
////							startActivity(new Intent(RegisterActivity.this.getBaseContext(), LoginActivity.class));
////							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
////						}
////
////					}
////				});
//			}
//
//			@Override
//			public void processResponse(int error, String content) {
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
//					Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//				} catch (JsonSyntaxException e) {
//					Toast.makeText(RegisterActivity.this, R.string.alertAcctionUnsucess, Toast.LENGTH_SHORT).show();
//				}
//				layoutLoading.setVisibility(View.GONE);
//			}
//		});


	}

	public void doAuthen(final String username, final String password) {
//		showProgressDialog("Đang xác thực...");
//		showLoading(false);

//		final SignInObject signInObject = new SignInObject();
//		signInObject.setParams(username, password);
//		SignInRequest signInRequest = new SignInRequest();
//		signInRequest.setSignInObject(signInObject);
//
//		ApiController.doPostRequest(this, signInRequest, new ResponseListener() {
//			@Override
//			public void processResponse(String response) {
//				if (response != null) {
//					AppSharePreference sharePreference = AppSharePreference.getInstance(RegisterActivity.this);
////					sharePreference.putStringValue(AppSharePreference.SHIPS_ACCESS_TOKEN, result.getAccessToken());
//
//					Gson gson = new GsonBuilder().setDateFormat(GlobalInfo.ServerConfig.DATE_FORMAT).create();
//					SignInResponse signInResponse = gson.fromJson(response, SignInResponse.class);
//
//					if (signInResponse.getUser() != null) {
//
////					sharePreference.putStringValue(AppSharePreference.SHIPS_ACCESS_TOKEN, result.getAccessToken());
//						sharePreference.putStringValue(AppSharePreference.SHIPS_ACCESS_TOKEN, signInResponse.getUser().getToken());
//						sharePreference.putStringValue(AppSharePreference.SHIPS_USERNAME, signInResponse.getUser().getUsername());
//						sharePreference.putStringValue(AppSharePreference.SHIPS_FULLNAME, signInResponse.getUser().getFullname());
//						sharePreference.putIntValue(AppSharePreference.SHIPS_USER_ID, signInResponse.getUser().getUserId());
//						sharePreference.putStringValue(AppSharePreference.SHIPS_ACCOUNT_TYPE, GlobalValue.ACCOUNT_IDOCTOR);
//						if (signInResponse.getBaby() != null && !signInResponse.getBaby().isEmpty()) {
//							GlobalValue.currentBaby = signInResponse.getBaby().get(0);
//							sharePreference.putIntValue(AppSharePreference.BABY_ID, GlobalValue.currentBaby.getId());
//						}
//						sharePreference.putLoginStatus(true);
//						goToMain();
//					} else {
//						Toast.makeText(RegisterActivity.this, getString(R.string.txtRegitserSuccessLabel), Toast.LENGTH_SHORT).show();
//						layoutLoading.setVisibility(View.GONE);
//						onKeyBack();
//					}
//					// goToMain();
//				} else {
//					Toast.makeText(RegisterActivity.this, getString(R.string.txtRegitserSuccessLabel), Toast.LENGTH_SHORT).show();
//					layoutLoading.setVisibility(View.GONE);
//					onKeyBack();
//				}
//			}
//
//			@Override
//			public void processResponse(int error, String content) {
//				Toast.makeText(RegisterActivity.this, getString(R.string.txtRegitserSuccessLabel), Toast.LENGTH_SHORT).show();
//				layoutLoading.setVisibility(View.GONE);
//				onKeyBack();
//			}
//		});
	}

	public void goToMain() {
		if (!RegisterActivity.this.isFinishing()) {
			finish();
//			startActivity(new Intent(LoginActivity.this.getBaseContext(), MainActivity.class));
			startActivity(new Intent(RegisterActivity.this.getBaseContext(), MainHomeActivity.class));
			overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
//			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		}
	}


	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onKeyBack() {
		finish();
		startActivity(new Intent(RegisterActivity.this.getBaseContext(), LoginActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
		// overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
	}

	public void onClickBack(View v) {
		finish();
		startActivity(new Intent(RegisterActivity.this.getBaseContext(), LoginActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

}
