package com.phanduy.view.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phanduy.GlobalInfo;
import com.phanduy.model.response.ResponseObject;
import com.phanduy.coinhub.R;
import com.phanduy.store.AppSharePreference;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.ConfigUtility;
import com.phanduy.view.activity.authen.LoginActivity;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class BABaseActivity extends AppCompatActivity {
    public Bundle bundle;
    public ProgressDialog progressDialog;

    public static final int TIME_OUT = 70000;

    public int dem;
    public BABaseActivity self;
    // public Button buttonLeft, buttonRight;
    // public TextView textViewTittle;
    // public ImageView imageLogo;
    public boolean isStartLoadData;

    public AppSharePreference sharePreference;

    public static final int REQUEST_CODE_CHOSE_MEMBER = 1111;


    // ======================= INTENT MANAGER =======================

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharePreference = AppSharePreference.getInstance(this);
//		ConfigUtility.getConfig(this);

        configVersion();
        GlobalInfo.getInstance().setActivityContext(this);
        GlobalInfo.getInstance().setAppContext(this);

//		Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this,
//				LoginActivity.class));
    }

    public class MyExceptionHandler implements
            java.lang.Thread.UncaughtExceptionHandler {
        private final Context myContext;
        private final Class<?> myActivityClass;

        public MyExceptionHandler(Context context, Class<?> c) {

            myContext = context;
            myActivityClass = c;
        }

        public void uncaughtException(Thread thread, Throwable exception) {

            StringWriter stackTrace = new StringWriter();
            exception.printStackTrace(new PrintWriter(stackTrace));
            System.err.println(stackTrace);// You can use LogCat too
            Intent intent = new Intent(myContext, myActivityClass);
            String s = stackTrace.toString();
            //you can use this String to know what caused the exception and in which Activity
            intent.putExtra("uncaughtException",
                    "Exception is: " + stackTrace.toString());
            intent.putExtra("stacktrace", s);
            myContext.startActivity(intent);
            //for restarting the Activity
//            Process.killProcess(Process.myPid());
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public void reLoadActivity(Activity c) {

        Intent intent = c.getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @SuppressLint({"NewApi", "NewApi", "NewApi", "NewApi"})
    public void configVersion() {
        if (ConfigUtility.version > 10) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * Go to other activity
     *
     * @param context
     * @param cla
     */
    public void gotoActivity(Context context, Class<?> cla) {
        Intent intent = new Intent(context, cla);
        startActivity(intent);
    }

    public void gotoActivity(Context context, Class<?> cla, String key, String value) {

        Intent intent = new Intent(context, cla);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void gotoActivity(Context context, Class<?> cla, int flag) {
        Intent intent = new Intent(context, cla);
        intent.setFlags(flag);
        startActivity(intent);
    }

    /**
     * Go to other activity
     *
     * @param context
     * @param cla
     */
    public void gotoActivityForResult(Context context, Class<?> cla, int requestCode) {
        Intent intent = new Intent(context, cla);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Goto activity with bundle
     *
     * @param context
     * @param cla
     * @param bundle
     */
    public void gotoActivity(Context context, Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(context, cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Goto activity with bundle
     *
     * @param context
     * @param cla
     * @param bundle
     * @param requestCode
     */
    public void gotoActivityForResult(Context context, Class<?> cla, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, cla);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void quitApp() {
        finish();
        // Actions here
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onKeyBack();
                break;
            case KeyEvent.KEYCODE_MENU:
                // Menu menu = new Menu(this);
                onKeyMenu();
                break;
        }
        return true;
    }

    // Tạo Option Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createOptionMenu(menu);
        return true;
    }

    public void createOptionMenu(Menu menu) {

    }

    public void onKeyMenu() {

    }

    public void doFilter(int topic, int genre, int region) {

    }

    public abstract void onKeyBack();

    public void showKeyBoard(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
            inputMethodManager.showSoftInput(editText, 1);
        }
    }

    public void hideKeyBoard(EditText editText) {
        if (editText != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public void hideAllKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    // ======================= Dialog utilities =======================
    public void showProgressDialog(String text) {
        if (!this.isFinishing() && progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getResources().getString(R.string.APP_NAME), text, true);
            progressDialog.setCancelable(false);
        }
    }

    public void showProgressDialog(Activity activity, String text) {
        if (!this.isFinishing() && progressDialog == null) {
            progressDialog = ProgressDialog.show(activity, getResources().getString(R.string.APP_NAME), text, true);
            progressDialog.setCancelable(false);
        }
    }

    public void closeProgressDialog() {
        if (!this.isFinishing() && progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    public void goToLogin() {

        AppSharePreference.getInstance(this).clear();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void hideAllKeyBoard() {
//		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

//		if(imm.getCurrentInputMethodSubtype().)
//		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    // Show/hide Progress Dialog
    public void showProgressDialog() {
        if (!this.isFinishing() && progressDialog == null) {
            progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.TEXT_LOADING), true);
            progressDialog.setCancelable(false);
        }
    }

    public void clearCache() {
        AppSharePreference appSharePreference = com.phanduy.store.AppSharePreference.getInstance(this);

//        boolean isFirstRunned = appSharePreference.getBooleanValue(com.viettel.store.AppSharePreference.FIRST_RUNNED);
        appSharePreference.clear();

//        appSharePreference.putBooleanValue(com.viettel.store.AppSharePreference.FIRST_RUNNED, isFirstRunned);

        GlobalValue.clearData();
    }

    public void showLoading() {
        RelativeLayout layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);
        RelativeLayout layoutError = (RelativeLayout) findViewById(R.id.layoutError);
        if (layoutLoading != null && layoutLoading.getVisibility() != View.VISIBLE) {
            layoutLoading.setVisibility(View.VISIBLE);
        }

        if (layoutError != null && layoutError.getVisibility() == View.VISIBLE) {
            layoutError.setVisibility(View.GONE);
        }

    }

    public void closeLoading() {
        RelativeLayout layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);
        RelativeLayout layoutError = (RelativeLayout) findViewById(R.id.layoutError);
        if (layoutLoading != null && layoutLoading.getVisibility() == View.VISIBLE) {
            layoutLoading.setVisibility(View.GONE);
        }

        if (layoutError != null && layoutError.getVisibility() == View.VISIBLE) {
            layoutError.setVisibility(View.GONE);
        }
    }

    public void showError(ResponseObject responseObject, boolean isError) {

        String message = null;

        if(responseObject == null ) {
            message = getResources().getString(R.string.text_no_network);
        } else {

            if(responseObject.getIsLogin() == -1) {
                Toast.makeText(this, "Phiên làm việc đã hết hạn hoặc không hợp lệ!", Toast.LENGTH_SHORT).show();
                goToLogin();
                return;
            }

            message = responseObject.getMessage() != null && !responseObject.getMessage().isEmpty()
                    ? responseObject.getMessage() : getResources().getString(R.string.text_no_network);
        }

        showError(message, isError);
    }

    public void showError(String message, boolean isError) {
        RelativeLayout layoutLoading = (RelativeLayout) findViewById(R.id.layoutLoading);
        RelativeLayout layoutError = (RelativeLayout) findViewById(R.id.layoutError);
        if (layoutLoading != null)
            layoutLoading.setVisibility(View.GONE);
        if (layoutError != null) {
            layoutError.setVisibility(View.VISIBLE);
            TextView textViewAlert = (TextView) layoutError.findViewById(R.id.textViewAlert);
            textViewAlert.setText(message);

            ImageView iconDisconnect = (ImageView) layoutError.findViewById(R.id.iconDisconnect);
            Button btnRefresh = (Button) layoutError.findViewById(R.id.btnRefresh);

            if(message.equals(getResources().getString(R.string.text_no_network))) {
                iconDisconnect.setVisibility(View.VISIBLE);
            } else {
                iconDisconnect.setVisibility(View.GONE);
            }

            if(isError) {
                btnRefresh.setVisibility(View.VISIBLE);
            } else {
                btnRefresh.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRefresh(View v) {

    }

    // ======================= Bundle Manager =======================

    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    public void setData(String key, String value) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(key, value);
    }

    public void setData(String key, byte value) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putByte(key, value);
    }

    public void setData(String key, boolean value) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(key, value);
    }

    public void setData(String key, int value) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(key, value);
    }

    public String getStringData(String key) {
        if (bundle == null) {
            bundle = getBundle();
        }
        return bundle.getString(key);
    }

    public byte getByteData(String key) {
        if (bundle == null) {
            bundle = getBundle();
        }

        return bundle.getByte(key);
    }

    public boolean getBooleanData(String key) {
        if (bundle == null) {
            bundle = getBundle();
        }
        return bundle.getBoolean(key);
    }

    public int getIntData(String key) {
        if (bundle == null) {
            bundle = getBundle();
        }
        return bundle.getInt(key);
    }

    public void setAlphaAnimation(View view, boolean visible) {
        AnimationSet animSet = new AnimationSet(true);
        if (visible) {
            AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
            alpha.setDuration(200);
            animSet.addAnimation(alpha);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(1f, 0f);
            animSet.addAnimation(alpha);
        }
        animSet.setFillEnabled(true);
        animSet.setFillAfter(true);
        view.clearAnimation();
        view.startAnimation(animSet);
    }

}
