package com.fnspl.hiplaretails.fcm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.fnspl.hiplaretails.application.App;
import com.fnspl.hiplaretails.application.AppSettings;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;



/**
 * Created by AndroidDev on 28-02-2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    SharedPreferences.Editor sharedPreferences;

    App app;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Refreshed token: " ,refreshedToken+ "");
        app = (App) getApplication();
        app.setAppSettings(new AppSettings(this));

        app.getAppSettings().setSession(refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
        broadcastToken(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    private void broadcastToken(String token) {
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent("REGISTRATION_COMPLETE");
        registrationComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
