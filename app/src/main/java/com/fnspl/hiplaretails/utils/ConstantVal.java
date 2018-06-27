package com.fnspl.hiplaretails.utils;

import android.content.Context;
import android.location.LocationManager;



import java.util.ArrayList;

public class ConstantVal {


    public static int child_position_analysis = 0;
    public static int child_position_incentive = 0;
    public static int child_position_promise = 0;

    public static int tab_position = 0;
    public static int child_position = 0;

    public static String s_date = "";
    public static String current_address = "";

    public static String drop_address = "";

    public static double current_lat=0.0;
    public static double current_long=0.0;
    public static double drop_lat=0.0;
    public static double drop_long=0.0;

    public static String accountSid = "ACd5c00ddb3435dabd02dc228c8619705a";
    public static String authToken = "05d49e3abb086caf9420feb3f153f1d3";

    public static String payStack_secretKey = "sk_test_265d425e5b1812b2438570c40a58b34685d02805";
    public static String payStack_publicKey = "pk_test_9f529ccc673f6ab8fd3bab6929081b4b91974f3d";



    public static String scheduleId="-1";


    public static boolean isLocationEnabled(Context context) {
        boolean isLocationEnabled;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        boolean isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isNetworkEnabled && !isGPSEnabled)
            isLocationEnabled = false;
        else
            isLocationEnabled = true;

        return isLocationEnabled;
    }





    //  String s_date, e_date;
}
