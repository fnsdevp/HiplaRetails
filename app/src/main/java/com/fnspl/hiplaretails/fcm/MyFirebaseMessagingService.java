package com.fnspl.hiplaretails.fcm;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import android.util.Log;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.activity.MainActivity;
import com.fnspl.hiplaretails.application.App;
import com.fnspl.hiplaretails.application.AppSettings;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by AndroidDev on 28-02-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebase";
    App app;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       // super.onMessageReceived(remoteMessage);
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]
        app = (App)getApplication();

        app.setAppSettings(new AppSettings(this));

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        /*if (remoteMessage.getData().size() > 0) {

            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            sendNotification(remoteMessage.getNotification().getBody());
            //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }*/

        if (remoteMessage.getData() != null ) {


            if (isAppIsInBackground(this)){

                Log.e(TAG, "Message data payload0: " + remoteMessage.getData());
                sendNotification(remoteMessage.getData().toString(),remoteMessage.getData().toString());
            }else {
                Log.e(TAG, "Message data payload1: " + remoteMessage.getData());
                try {
                    JSONObject jsonO = new JSONObject(remoteMessage.getData().toString()).getJSONObject("notification");
                    JSONObject jsonObject = jsonO.getJSONObject("data");
                    Log.e("jsonObject",jsonObject.toString());
                    String type = jsonObject.getString("type");

                    if (type.equals("PenaltyCompleted")) {
                        Intent pushNotification = new Intent("Completed");
                        pushNotification.putExtra("type", "1");
                        pushNotification.putExtra("Penalty", jsonObject.toString());
                        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                    }else if (type.equals("schedule")) {
                        Intent pushNotification = new Intent("Completed");
                        pushNotification.putExtra("type", "2");
                        pushNotification.putExtra("schedule", jsonObject.toString());
                        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }




            //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }




        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody,String body) {
        PendingIntent pendingIntent = null;
        String massage= "";
        try {
            JSONObject jsonO = new JSONObject(body).getJSONObject("notification");
            JSONObject jsonObject = jsonO.getJSONObject("data");

            Log.e("jsonObject1",jsonObject.toString());
            massage = jsonO.getString("title");
            String type = jsonObject.getString("type");
            if (type.equals("PenaltyCompleted")){
                Intent intent = new Intent(this, MainActivity.class).putExtra("type","1" ).putExtra("Penalty", jsonObject.toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

            }
            if (type.equals("schedule")){
                Intent intent = new Intent(this, MainActivity.class).putExtra("type","2" ).putExtra("schedule", jsonObject.toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


       /* Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_ONE_SHOT);*/
        //int color = 0x008000;
        String cap = massage.substring(0, 1).toUpperCase() + massage.substring(1);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setColor(getColor(R.color.colorAccent))
                .setContentTitle("PosPromoter")
                .setContentText(cap)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }



    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        try{

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            } else {
                if (am != null){
                    List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                    ComponentName componentInfo = taskInfo.get(0).topActivity;
                    if (componentInfo.getPackageName().equals(context.getPackageName())) {
                        isInBackground = false;
                    }
                }

            }

        }catch (Exception e){e.printStackTrace();}


        return isInBackground;
    }


}