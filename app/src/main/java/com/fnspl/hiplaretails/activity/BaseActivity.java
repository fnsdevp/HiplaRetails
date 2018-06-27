package com.fnspl.hiplaretails.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.application.ApiClient;
import com.fnspl.hiplaretails.application.Apis;
import com.fnspl.hiplaretails.application.App;
import com.fnspl.hiplaretails.application.AppSettings;
import com.fnspl.hiplaretails.networkApi.NetworkConnection;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog mDialog;

    public DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher)
            .cacheInMemory(true).cacheOnDisk(true)
            .build();
    ImageLoader imageLoader;
    Apis mApis;
    App app;
    NetworkConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApis = ApiClient.getClient().getApis();
        app = (App)getApplication();
        app.setAppSettings(new AppSettings(this));
        connection = new NetworkConnection(this);


        mDialog = new ProgressDialog(BaseActivity.this);
        mDialog.setMessage("Please wait...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);

        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
            if (!imageLoader.isInited()) {
                imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            }
        }


    }

    public void showProgressBar(){
        if (mDialog!=null){
            mDialog.show();
        }
    }
    public void hideProgressBar(){
        if (mDialog!=null){
            mDialog.dismiss();
        }
    }
    public DisplayImageOptions option(){
       return options;
    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void Log(String calssName,String massage){
        Log.e(calssName,massage);
    }
    public void Toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(BaseActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
