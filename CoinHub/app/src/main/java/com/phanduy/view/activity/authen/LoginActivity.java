package com.phanduy.view.activity.authen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phanduy.GlobalInfo;
import com.phanduy.api.ApiController;
import com.phanduy.interfaces.ResponseListener;
import com.phanduy.model.request.GetUserInfoRequest;
import com.phanduy.model.request.SignInRequest;
import com.phanduy.model.response.GetUserInfoResponse;
import com.phanduy.model.response.SignInResponse;
import com.phanduy.coinhub.R;
import com.phanduy.store.AppSharePreference;
import com.phanduy.utils.ConfigUtility;
import com.phanduy.utils.DialogUtility;
import com.phanduy.view.activity.MainHomeActivity;
import com.phanduy.view.base.BABaseActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginActivity extends BABaseActivity {

    EditText edtCompanyPhone, edtPassword;

    private RelativeLayout layoutLoading;
    private RelativeLayout layoutError;

    private LinearLayout layoutControl;

    public AppSharePreference appSharePreference;

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private final static int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 9669;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console.
     */
//	String SENDER_ID = "675040219206";
    String SENDER_ID = "663716351897";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCMDemo";

    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;

    String regid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.

                for (int i = 0, length = grantResults.length; i < length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        finish();
                        return;
                    }
                }
                init();
//				Intent refresh = new Intent(this, MainHomeActivity.class);
//				refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//				startActivity(refresh);// Start the same Activity
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void init() {

        ConfigUtility.getConfigs(this);

        appSharePreference = AppSharePreference.getInstance(this);
        boolean loginStatus = appSharePreference.getLoginStatus();

        setContentView(R.layout.layout_activity_login);

        initView();
        if (loginStatus) {
            showLoading(false);
            goToMain();
//            getUserInfo();
        }

        // initGoogle();

//        final View activityRootView = findViewById(R.id.root);
//        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                activityRootView.getWindowVisibleDisplayFrame(r);
//
//                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
//                if (heightDiff > 100) { // if more than 100 pixels, its
//                    textViewCopyRight.setVisibility(View.GONE);
//                } else {
//                    textViewCopyRight.setVisibility(View.VISIBLE);
//                }
//            }
//        });

//        boolean isFirstRunned = appSharePreference.getBooleanValue(AppSharePreference.FIRST_RUNNED);
//
//        if(!isFirstRunned) {
//            startActivity(new Intent(this, FirstIntroActivity.class));
//        }

    }

    private int type;

    private void initView() {
        layoutError = (RelativeLayout) findViewById(R.id.layoutError);
        layoutError.setVisibility(View.GONE);

        layoutControl = (LinearLayout) findViewById(R.id.layoutControl);
        layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);
        layoutLoading.setVisibility(View.GONE);


        edtCompanyPhone = (EditText) findViewById(R.id.edtCompanyPhone);
//        edtPrivatePhone = (EditText) findViewById(R.id.edtPrivatePhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        GlobalInfo.setLayoutInflater(getLayoutInflater());

        if(GlobalInfo.AppConfig.isTestAccount) {
            edtCompanyPhone.setText("phanduy.itbka@gmail.com");
//            edtPrivatePhone.setText("123");
            edtPassword.setText("tlsvdhbkhn");
        }
    }

    public void onClickBtnLogin(View v) {
        onClickLogin();
    }

    public void onClickSignUp(View v) {
        goToRegister();
    }

    public void onClickForgotPassword(View v) {
        goToForgotPass();
    }

    public void initGoogle() {
//		showProgressDialog("Đang tải dữ liệu...");

        if(true) {
            goToMain();
        }

        showLoading(false);

        if (checkPlayServices()) {
            // regid = appSharePreference.getDooRegistrationId();

            // if (regid.isEmpty()) {
            registerInBackground();
            // }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
//			goToMain();
//			closeProgressDialog();
            closeLoading(false);
            new MaterialDialog.Builder(LoginActivity.this)
                    .title("No valid Google Play Services APK found.")
                    .positiveText(getResources().getString(R.string.labelAccept))
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            finish();
                        }
                    })
                    .show();

        }
    }

    public void showLoading(boolean isAuto) {
        if (layoutLoading.getVisibility() != View.VISIBLE) {
            layoutLoading.setVisibility(View.VISIBLE);
        }

        if (layoutError.getVisibility() == View.VISIBLE) {
            layoutError.setVisibility(View.GONE);
        }
        if (isAuto)
            layoutControl.setVisibility(View.INVISIBLE);

    }

    public void closeLoading(boolean isError) {
        if (layoutLoading.getVisibility() == View.VISIBLE) {
            layoutLoading.setVisibility(View.GONE);
        }

        if (layoutError.getVisibility() == View.VISIBLE) {
            layoutError.setVisibility(View.GONE);
        }

        if (isError)
            layoutControl.setVisibility(View.INVISIBLE);

    }

    // ==================== GCM business ================ //
    private boolean checkPlayServices() {
//        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
//            } else {
//                Log.e(TAG, "This device is not supported.");
//                finish();
//            }
//            return false;
//        }
        return true;
    }

    /**
     * Registers the application with GCM connection servers asynchronously.
     * <p/>
     * Stores the registration ID and app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {

        AsyncTask<String, String, String> asyn = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);

                if (regid == null || regid.isEmpty()) {
//					clo
                    goToMain();
//					closeProgressDialog();

                } else {
                    sendRegistrationIdToBackend();
                }
            }
        };
        asyn.execute();
    }

    private void sendRegistrationIdToBackend() {

//        String language = Locale.getDefault().getLanguage();
//        SendSubcriptionRequest sendSubcriptionRequest = new SendSubcriptionRequest();
//        sendSubcriptionRequest.setRequsetParams("" + ConfigUtility.deviceId, regid, language);
//
//        ApiController.doPostRequest(this, sendSubcriptionRequest, new ResponseListener() {
//
//            @Override
//            public void processResponse(int error, String content) {
////				Log.e("sendRegistrationIdToBackend", "Error");
////				layoutLoading.setVisibility(View.GONE);
//                goToMain();
////				closeProgressDialog();
//            }
//
//            @Override
//            public void processResponse(String response) {
////				Log.e("sendRegistrationIdToBackend", "thành công");
////				layoutLoading.setVisibility(View.GONE);
//                goToMain();
////				closeProgressDialog();
//            }
//        });
    }

    /**
     * Stores the registration ID and app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId   registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        appSharePreference.putDooRegistrationId(regId);
        Log.e(TAG, "Saving regId on app version " + ConfigUtility.version);
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
                            onClickLogin();
                        }
                        break;
                }
            }
            return false;
        }
    };

    private void onClickLogin() {

//		if(true) {
//            hideAllKeyBoard();
//			doAuthen("01677524660", "123", "aund1992");
//			return;
//		}

        String companyPhone = edtCompanyPhone.getEditableText().toString().trim();
//        String privatePhone = edtPrivatePhone.getEditableText().toString().trim();
        String password = edtPassword.getEditableText().toString();

        if (companyPhone.length() == 0) {
			DialogUtility.showDialogAlert(this, "", getResources().getString(R.string.alertEmptyCompanyPhone),
					LoginActivity.this.getResources().getString(R.string.close_button), null);
            showKeyBoard(edtCompanyPhone);
            return;
        }
//        if (privatePhone.length() == 0) {
//			DialogUtility.showDialogAlert(this, "", getResources().getString(R.string.alertEmptyPrivatePhone),
//					LoginActivity.this.getResources().getString(R.string.close_button), null);
//            showKeyBoard(edtPrivatePhone);
//            return;
//        }

        if (password.length() == 0) {
			DialogUtility.showDialogAlert(this, "", getResources().getString(R.string.alertEmptyPassword),
					getResources().getString(R.string.close_button), null);
            showKeyBoard(edtPassword);
            return;
        }

        if (password.length() < 6) {
            DialogUtility.showDialogAlert(this, "", getResources().getString(R.string.alertInvalidPassword),
                    getResources().getString(R.string.close_button), null);
            showKeyBoard(edtPassword);
            return;
        }

        hideAllKeyBoard();

        doAuthen(companyPhone, password);
    }

    public void onLoginError(String text) {
        layoutLoading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void doAuthen(final String companyPhone, final String password) {
//		showProgressDialog("Đang xác thực...");
        showLoading(false);

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setData(companyPhone, password);

        ApiController.doPostRequest(this, signInRequest, new ResponseListener() {
            @Override
            public void processResponse(String response) {

                Log.e("Response", "" + response);
                if (response != null) {

                    Gson gson = new GsonBuilder().setDateFormat(GlobalInfo.ServerConfig.DATE_FORMAT).create();
                    SignInResponse signInResponse = gson.fromJson(response, SignInResponse.class);

                    if (signInResponse.getIsSuccess() == 1) {
                        if(signInResponse.getIsLogin() == 1) {
                            sharePreference.putStringValue(AppSharePreference.SHIPS_ACCESS_TOKEN, signInResponse.getToken());
                            sharePreference.putStringValue(AppSharePreference.NUM_OF_COMPANY, companyPhone);
                            sharePreference.putFullName(signInResponse.getUserName());
                            sharePreference.putCallback(signInResponse.getCallBack());
                            sharePreference.putLoginStatus(true);
                            goToMain();
                        } else {
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.alertLoginFail), Toast.LENGTH_SHORT).show();
                            closeLoading(false);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.alertConnectFail), Toast.LENGTH_SHORT).show();
                        closeLoading(false);
                    }
                    // goToMain();
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    closeLoading(false);
                }
            }

            @Override
            public void processResponse(int error, String content) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.alertConnectFail), Toast.LENGTH_SHORT).show();
                closeLoading(false);
            }
        });
    }

    public void getUserInfo() {
        GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest();
        getUserInfoRequest.setData(appSharePreference.getAccessToken(), "" + appSharePreference.getUserId());

        ApiController.doPostRequest(this, getUserInfoRequest, new ResponseListener() {
            @Override
            public void processResponse(String response) {

                Log.e("UserInfo", "" + response);

                if (response != null) {

                    Gson gson = new GsonBuilder().setDateFormat(GlobalInfo.ServerConfig.DATE_FORMAT).create();
                    GetUserInfoResponse signInResponse = gson.fromJson(response, GetUserInfoResponse.class);

                    String message = signInResponse.getMessage() != null && !signInResponse.getMessage().isEmpty()
                            ? signInResponse.getMessage() : null;

                    if (signInResponse.getIsSuccess() == 1) {
                        if(signInResponse.getIsLogin() == 1) {
                            sharePreference.putFullName(signInResponse.getUserName());
                            sharePreference.putCallback(signInResponse.getCallBack());
                            goToMain();
                        } else {
                            Toast.makeText(LoginActivity.this,message != null ? message : getResources().getString(R.string.alertLoginFail), Toast.LENGTH_SHORT).show();
                            closeLoading(false);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this,message != null ? message : getResources().getString(R.string.alertConnectFail), Toast.LENGTH_SHORT).show();
                        closeLoading(false);
                    }
                    // goToMain();
                } else {
                    clearCache();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    closeLoading(false);
                }
            }

            @Override
            public void processResponse(int error, String content) {
                clearCache();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.alertConnectFail), Toast.LENGTH_SHORT).show();
                closeLoading(false);
            }
        });
    }

    public void goToMain() {
        if (!LoginActivity.this.isFinishing()) {
            finish();
            startActivity(new Intent(LoginActivity.this.getBaseContext(), MainHomeActivity.class));
//            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    public void goToRegister() {
        if (!LoginActivity.this.isFinishing()) {
            finish();
            startActivity(new Intent(LoginActivity.this.getBaseContext(), RegisterActivity.class));
//			overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    public void goToForgotPass() {
        if (!LoginActivity.this.isFinishing()) {
            startActivity(new Intent(LoginActivity.this.getBaseContext(), ForgotPassActivity.class));
//			overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        FacebookManager.getInstance(this).getCallbackManager().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
    }

//	@Override
//	public void onRequestPermissionsResult(int requestCode,
//										   String permissions[], int[] grantResults) {
//		switch (requestCode) {
//			case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//				// If request is cancelled, the result arrays are empty.
//				if (grantResults.length > 0
//						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					init();
//					// permission was granted, yay! Do the
//					// contacts-related task you need to do.
//
//				} else {
//
//					// permission denied, boo! Disable the
//					// functionality that depends on this permission.
//					finish();
//				}
//				return;
//			}
//
//			// other 'case' lines to check for other
//			// permissions this app might request
//		}
//	}
}
