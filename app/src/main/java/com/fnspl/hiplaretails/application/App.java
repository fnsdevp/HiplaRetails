package com.fnspl.hiplaretails.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;




public class App extends Application implements Application.ActivityLifecycleCallbacks{

    private AppSettings mAppSetting = null;

    public void setAppSettings(AppSettings obj) {
        mAppSetting = obj;
    }

    public AppSettings getAppSettings() {
        return mAppSetting;
    }

    public static final String TAG = App.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static App mInstance;

    static final String APP_ID = "59156";
    static final String AUTH_KEY = "9uzCV7gKWWM8zwf";
    static final String AUTH_SECRET = "CW7bMS4TYfCsLSE";
    static final String ACCOUNT_KEY = "59156";


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }




    @Override
    public void onActivityStarted(Activity activity) {
        Log.i("Activity Started", activity.getLocalClassName());

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.i("SaveInstanceState", activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i("Activity Resumed", activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i("Activity Paused", activity.getLocalClassName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(" Activity Created", activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(" Activity Destroyed", activity.getLocalClassName());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i(" Activity Created", activity.getLocalClassName());
    }


}
