package com.fnspl.hiplaretails.application;

import android.content.Context;
import android.content.SharedPreferences;

import com.fnspl.hiplaretails.model.apiResponse.Profile;
import com.fnspl.hiplaretails.model.apiResponse.User;

public class AppSettings {
    private SharedPreferences sharedPreferences = null;
    public String __uId, __uName, __fName,__lName,__uImage="", __uniCode,__uEmail, __fcmToken="0",__zip_code,__reg_date,__cSymbol,__countryCode="NG",__templatesData,__lat,__lng,__password,__mobile,__address,__rating,__userType,__uArea,__Chil = "0",_lastSyncDate,__totalAnalysis;
    public boolean __isLoggedIn = false;
    public boolean __isRemember = false;
    public boolean __firstTime = false;


    public AppSettings(Context context) {
        sharedPreferences = context.getSharedPreferences(Constant.APP_SETTING_KEY.APP_SETTING.name(), Context.MODE_PRIVATE);

        __uName = sharedPreferences.getString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        __isLoggedIn = sharedPreferences.getBoolean(Constant.APP_SETTING_KEY.IS_USER_LOGIN.name(), __isLoggedIn);
        __uEmail = sharedPreferences.getString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        __uImage = sharedPreferences.getString(Constant.APP_SETTING_KEY.USER_IMAGE.name(), __uImage);
        __fcmToken = sharedPreferences.getString(Constant.APP_SETTING_KEY.FCM_TOKEN.name(), __fcmToken);
        __uId = sharedPreferences.getString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
        __fName = sharedPreferences.getString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        __lName = sharedPreferences.getString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        __mobile = sharedPreferences.getString(Constant.APP_SETTING_KEY.MOBILE.name(),__mobile);
        __address = sharedPreferences.getString(Constant.APP_SETTING_KEY.ADDRESS.name(),__address);
        __rating = sharedPreferences.getString(Constant.APP_SETTING_KEY.RATING.name(),__rating);
        __zip_code = sharedPreferences.getString(Constant.APP_SETTING_KEY.USER_ZIP.name(),__zip_code);
        __uArea = sharedPreferences.getString(Constant.APP_SETTING_KEY.USER_AREA.name(),__uArea);
        __reg_date = sharedPreferences.getString(Constant.APP_SETTING_KEY.REG_DATE.name(),__reg_date);

        __templatesData = sharedPreferences.getString(Constant.APP_SETTING_KEY.TEMPLATES_DATA.name(), __templatesData);
        __lat = sharedPreferences.getString(Constant.APP_SETTING_KEY.LAT.name(), __lat);
        __lng = sharedPreferences.getString(Constant.APP_SETTING_KEY.LONG.name(), __lng);
        __userType = sharedPreferences.getString(Constant.APP_SETTING_KEY.USER_TYPE.name(), __userType);
        __isRemember = sharedPreferences.getBoolean(Constant.APP_SETTING_KEY.IS_REMEMBER_LOGIN.name(), __isRemember);
        __password = sharedPreferences.getString(Constant.APP_SETTING_KEY.APP_USER_PASSWORD.name(), __password);
        __Chil = sharedPreferences.getString(Constant.APP_SETTING_KEY.CHILD.name(), __Chil);
        _lastSyncDate = sharedPreferences.getString(Constant.APP_SETTING_KEY.LAST_SYNC_DATE.name(), _lastSyncDate);
        __totalAnalysis = sharedPreferences.getString(Constant.APP_SETTING_KEY.TOTAL_ANALYSIS.name(), __totalAnalysis);

        __firstTime = sharedPreferences.getBoolean(Constant.APP_SETTING_KEY.IS_FIRST_TIME.name(), __firstTime);
        __countryCode = sharedPreferences.getString(Constant.APP_SETTING_KEY.COUNTRY_CODE.name(), __countryCode);
        __uniCode = sharedPreferences.getString(Constant.APP_SETTING_KEY.CURRENCY_CODE.name(), __uniCode);
        __cSymbol = sharedPreferences.getString(Constant.APP_SETTING_KEY.C_SYMBOL.name(), __cSymbol);

    }

    public void setSession(String __uId, String __uEmail, boolean __isLoggedIn, String __userType, String __Chil, String _lastSyncDate) {
        this.__uId = __uId;
        this.__uEmail = __uEmail;
        this.__isLoggedIn = __isLoggedIn;
        this.__userType = __userType;
        this.__Chil = __Chil;
        this._lastSyncDate = _lastSyncDate;



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
       // editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_USER_LOGIN.name(), __isLoggedIn);
        editor.putString(Constant.APP_SETTING_KEY.USER_TYPE.name(), __userType);
        editor.putString(Constant.APP_SETTING_KEY.CHILD.name(), __Chil);
        editor.putString(Constant.APP_SETTING_KEY.LAST_SYNC_DATE.name(), _lastSyncDate);
        editor.commit();
    }

    public void setSession(boolean __isLoggedIn,User user) {
        this.__uId = user.getId();
        this.__uName = user.getFname() + " "+ user.getLname();
        this.__fName = user.getFname();
        this.__lName = user.getLname();
        this.__uEmail = user.getEmail();
        this.__isLoggedIn = __isLoggedIn;
        this.__mobile = user.getPhone();
        this.__address = user.getAddress();
        this.__zip_code = user.getPincode();
        this.__reg_date = user.getRegistrationdate();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_USER_LOGIN.name(), __isLoggedIn);
        editor.putString(Constant.APP_SETTING_KEY.MOBILE.name(), __mobile);
        editor.putString(Constant.APP_SETTING_KEY.ADDRESS.name(), __address);
        editor.putString(Constant.APP_SETTING_KEY.USER_ZIP.name(), __zip_code);
        editor.putString(Constant.APP_SETTING_KEY.REG_DATE.name(), __reg_date);
        editor.apply();

    }

    public void setSession(Profile user) {
        this.__uId = String.valueOf(user.getId());
        this.__uName = user.getFname() + " "+ user.getLname();
        this.__fName = user.getFname();
        this.__lName = user.getLname();
        this.__uEmail = user.getEmail();
        this.__mobile = user.getPhone();
        this.__address = user.getAddress();
        this.__zip_code = user.getPincode();
        this.__reg_date = user.getRegistrationdate();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putString(Constant.APP_SETTING_KEY.MOBILE.name(), __mobile);
        editor.putString(Constant.APP_SETTING_KEY.ADDRESS.name(), __address);
        editor.putString(Constant.APP_SETTING_KEY.USER_ZIP.name(), __zip_code);
        editor.putString(Constant.APP_SETTING_KEY.REG_DATE.name(), __reg_date);
        editor.putString(Constant.APP_SETTING_KEY.USER_IMAGE.name(), __uImage);
        editor.apply();

    }

    public void setSession(String __uId, String __uName, String __fName, String __lName, String __uEmail, boolean __isLoggedIn, String __mobile, String __address,  String __zip_code) {
        this.__uId = __uId;
        this.__uName = __uName;
        this.__fName = __fName;
        this.__lName = __lName;
        this.__uEmail = __uEmail;
        this.__isLoggedIn = __isLoggedIn;
        this.__mobile = __mobile;
        this.__address = __address;
        this.__zip_code = __zip_code;


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_USER_LOGIN.name(), __isLoggedIn);
        editor.putString(Constant.APP_SETTING_KEY.MOBILE.name(), __mobile);
        editor.putString(Constant.APP_SETTING_KEY.ADDRESS.name(), __address);
        editor.putString(Constant.APP_SETTING_KEY.USER_ZIP.name(), __zip_code);


        editor.commit();

    }

    public void setSession(String __uId, String __uName, String __fName, String __lName, String __uEmail, String __uImage, boolean __isLoggedIn, String __mobile, String __address, String __rating, String __zip_code, String __uArea, String __uniCode) {
        this.__uId = __uId;
        this.__uName = __uName;
        this.__fName = __fName;
        this.__lName = __lName;
        this.__uEmail = __uEmail;
        this.__uImage = __uImage;
        this.__isLoggedIn = __isLoggedIn;
        this.__mobile = __mobile;
        this.__address = __address;
        this.__rating = __rating;
        this.__zip_code = __zip_code;
        this.__uArea = __uArea;
        this.__uniCode =  __uniCode;



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UID.name(), __uId);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_USER_IMAGE.name(), __uImage);
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_USER_LOGIN.name(), __isLoggedIn);
        editor.putString(Constant.APP_SETTING_KEY.MOBILE.name(), __mobile);
        editor.putString(Constant.APP_SETTING_KEY.ADDRESS.name(), __address);
        editor.putString(Constant.APP_SETTING_KEY.RATING.name(), __rating);
        editor.putString(Constant.APP_SETTING_KEY.USER_ZIP.name(), __zip_code);
        editor.putString(Constant.APP_SETTING_KEY.USER_AREA.name(), __uArea);
        editor.putString(Constant.APP_SETTING_KEY.CURRENCY_CODE.name(), __uniCode);

        editor.commit();

    }
    public void setSession(String __countryCode, int id, String __cSymbol) {

        this.__countryCode = __countryCode;
        this.__cSymbol = __cSymbol;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.COUNTRY_CODE.name(), __countryCode);
        editor.putString(Constant.APP_SETTING_KEY.C_SYMBOL.name(), __cSymbol);

        editor.commit();
    }

    public void setSession(int __notification) {
        //this.__noti = String.valueOf(__notification) ;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.NOTIFICATIONS.name(), String.valueOf(__notification));

        editor.commit();
    }

    public void setSession(int __notification,String _lastSyncDate) {
        //this.__noti = String.valueOf(__notification) ;
        this._lastSyncDate = _lastSyncDate;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.LAST_SYNC_DATE.name(), String.valueOf(__notification));

        editor.commit();
    }

    public void setSession(int __notification, String __totalAnalysis, int no) {

        this.__totalAnalysis = __totalAnalysis;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.TOTAL_ANALYSIS.name(),__totalAnalysis);

        editor.commit();
    }




    public void setSession(String __fName, String __lName, String __uEmail, String __uName, int name) {

        this.__fName = __fName;
        this.__lName = __lName;
        this.__uEmail = __uEmail;
        this.__uName = __uName;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_FNAME.name(), __fName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_LNAME.name(), __lName);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_LOGIN_UNAME.name(), __uName);
        editor.commit();
    }


    public void setSession(String __address, String __uArea, String __zip_code, String __mobile) {
        this.__address = __address;
        this.__uArea = __uArea;
        this.__mobile = __mobile;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.ADDRESS.name(), __address);
        editor.putString(Constant.APP_SETTING_KEY.USER_AREA.name(), __uArea);
        editor.putString(Constant.APP_SETTING_KEY.MOBILE.name(), __mobile);


        editor.commit();
    }


    public void setSession(boolean __isRemember, String __uEmail, String __password) {
        this.__isRemember = __isRemember;
        this.__uEmail = __uEmail;
        this.__password = __password;

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constant.APP_SETTING_KEY.PREFERENCE_EMAIL.name(), __uEmail);
        editor.putString(Constant.APP_SETTING_KEY.APP_USER_PASSWORD.name(), __password);
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_REMEMBER_LOGIN.name(), __isRemember);
        editor.commit();
    }

    public void setSession(String __fcmToken) {
        this.__fcmToken = __fcmToken;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.FCM_TOKEN.name(), __fcmToken);
        editor.commit();
    }
    public void setSession(boolean __firstTime,String __data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constant.APP_SETTING_KEY.IS_FIRST_TIME.name(), __firstTime);
        editor.putString(Constant.APP_SETTING_KEY.TEMPLATES_DATA.name(), __data);
        editor.commit();
    }

    public void setSession(String __lat, String __lng, int lng) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.APP_SETTING_KEY.LAT.name(), __lat);
        editor.commit();
    }

    public void clearPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

}
