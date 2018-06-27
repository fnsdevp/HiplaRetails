package com.fnspl.hiplaretails.enums;

public enum URL {

    LOGIN("users/app_signin"),
    SIGN_UP("users/app_signup"),
    SOCIAL_LIGIN("users/app_social_signup"),
    MOBILE_UPDATE("users/app_change_mobile"),
    FORGOT_PASSWORD("users/app_forgotpass"),
    LOG_OUT("logout_service"),
    EDIT_CHILD("editchildApi"),
    CONFIRM_BOOKING("users/appuser_add_booking"),
    GET_BOOKING_DETAILS("users/appdriver_booking_detail"),
    BOOKING_CONFIRM_SUBMIT("users/app_user_estimation"),
    EDIT_PROFILE("users/app_user_edit_account"),
    GET_PROFILE("drivers/app_driver_details_byid"),
    USER_BOOKED_LIST("users/app_user_booking_list"),
    USER_All_BOOKED_LIST("users/app_user_allbooking"), // for all seat booked list
    CHANGE_PASS("users/app_change_password"),
    PROMOCODE("users/app_all_promocode"),
    USER_RATING("users/app_add_rating"),
    GET_PROMOCODE("users/app_eligible_promo"),
    PAYMENT_API("app_userBookingPayment"),
    DRIVER_LIST("drivers/app_list_driver");



    public String mURL;

  //  public String BaseUrl = "http://111.93.169.90/team2/ridewithme/webservice/";
    public String BaseUrl = "http://www.ridewithmeng.com/webservice/";



    URL(String mURL) {
        this.mURL = mURL;
        //this.mURL = this.BaseUrl + mURL;
    }

    public String getURL() {
        return mURL;
    }

}
