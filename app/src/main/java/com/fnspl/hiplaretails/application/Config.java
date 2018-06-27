package com.fnspl.hiplaretails.application;

public class Config {

    // flag to identify whether to show single line
    // or multi line text in push notification tray
    public static boolean appendNotificationMessages = true;

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
   // public static final String public_key = "pk_test_9f529ccc673f6ab8fd3bab6929081b4b91974f3d"; //test
    public static final String public_key = "pk_live_e5878ef3fb23a8ff62b7d8065a2d42d4b749e0f6"; // live


    // type of push messages
    public static final int PUSH_TYPE_CHATROOM = 1;
    public static final int PUSH_TYPE_USER = 2;

    // id to handle the notification in the notification try
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
}
