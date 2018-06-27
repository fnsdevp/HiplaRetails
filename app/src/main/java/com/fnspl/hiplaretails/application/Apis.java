package com.fnspl.hiplaretails.application;


import com.fnspl.hiplaretails.enums.URL;
import com.fnspl.hiplaretails.model.apiResponse.Favourites;
import com.fnspl.hiplaretails.model.apiResponse.HomeCategory;
import com.fnspl.hiplaretails.model.apiResponse.HomeSliderOffer;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeSlider;
import com.fnspl.hiplaretails.model.apiResponse.ListRes;
import com.fnspl.hiplaretails.model.apiResponse.ObjectRes;
import com.fnspl.hiplaretails.model.apiResponse.RecommendedDetails;
import com.fnspl.hiplaretails.model.apiResponse.UserDetails;
import com.fnspl.hiplaretails.model.apiResponse.UserUploadDetails;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;


public interface Apis {

    @POST(ApiConstants.SLIDER_OFFER)
    Call<ListHomeSlider> sliderOffer();

    @POST(ApiConstants.HOME_CATEGORY)
    Call<HomeCategory> homeCategory();

    @FormUrlEncoded
    @POST(ApiConstants.FAVOURITE_ITEMS)
    Call<Favourites> favourite(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST(ApiConstants.RECOMMENDED_ITEMS)
    Call<RecommendedDetails> recommended(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST(ApiConstants.RECENTLY_BROWED_ITEMS)
    Call<RecommendedDetails> recently_browsed(@FieldMap Map<String,String> params);

    /*@POST(ApiConstants.LOGIN)
    Call<UserDetails> login(@Body Map req);*/

    @Multipart
    @POST(ApiConstants.LOGIN)
    Call<UserDetails> login(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.SIGN_UP)
    Call<UserDetails> registration(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE)
    Call<UserUploadDetails> updateProfileImage(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE)
    Call<UserUploadDetails> updateProfile(@PartMap Map<String, RequestBody> map);

/*
    @POST(ApiConstants.REGISTRATION)
    Call<ObjectRes<LoginRes>> registration(@Body Map req);

    @POST(ApiConstants.WALKER_LIST)
    Call<ListRes<WalkerList>> walker_list(@Body Map req);

    @POST(ApiConstants.PET_LIST)
    Call<ListRes<PetDetails>> getPetListOwn(@Body Map req);

    @POST(ApiConstants.ADD_BOOKING)
    Call<ObjectRes> petBooking(@Body Map jsonObject);

    @POST(ApiConstants.GET_BOOKING_DETAILS)
    Call<ObjectRes<BookingDetaildAll>> getPetBooking(@Body Map jsonObject);

    @POST(ApiConstants.GET_BOOKING_DETAILS)
    Call<ResponseBody> getPetBooking1(@Body Map jsonObject);

    @POST(ApiConstants.GET_TRACKING_PROVIDER_DETAILS)
    Call<ObjectRes<TrackingDetails>> getTrackingDetails(@Body Map jsonObject);

    @POST(ApiConstants.CANCEL_BOOKING)
    Call<ResponseBody> cancelBooking(@Body Map jsonObject);


    @POST(ApiConstants.GET_ALL_PET_LIST)
    Call<ResponseBody> getPetListAll();

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE)
    Call<ObjectRes> updateProfile(@Part MultipartBody.Part file, @Part("user_id") String id, @Part("fname") String fname, @Part("lname") String lname, @Part("address") String address, @Part("phone") String phone);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE)
    Call<ObjectRes> updateProfile(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.REGISTRATION)
    Call<ObjectRes<LoginRes>>  signUpWithPic(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.ADD_PET)
    Call<ListRes<PetDetailsAll>> addPet(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.ADD_LOST_PET)
    Call<ObjectRes> addLostPet(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants. ADOPTION_PET)
    Call<ObjectRes> addAdoptionPet(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST(ApiConstants.UPDATE_PET)
    Call<ListRes<PetDetailsAll>> editPet(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @POST(ApiConstants.GET_PET_LIST)
    Call<ListRes<PetDetailsAll>> getPetList(@Body Map jsonObject);

    @POST(ApiConstants.GET_ADAPTION_LIST)
    Call<ListRes<PetDetails>> getAdaptionList(@Body Map jsonObject);

    @POST(ApiConstants.GET_MY_ADAPTION_LIST)
    Call<ListRes<PetDetails>> getMyPost(@Body Map jsonObject);

    @POST(ApiConstants.ADAPTION_PET_DELETE)
    Call<ListRes<PetDetails>> deleteAdaptionPet(@Body Map jsonObject);


    @POST(ApiConstants.GET_ADAPTION_PET_DETAILS)
    Call<ObjectRes<OwnerPetDetails>> getAdaptionPetDetails(@Body Map jsonObject);

    @POST(ApiConstants.GET_OWNER_PET_DETAILS)
    Call<ObjectRes<OwnerPetDetails>> getOwnerPetDetails(@Body Map jsonObject);

    @POST(ApiConstants.GET_L_AND_F_PET_DETAILS)
    Call<ObjectRes<OwnerPetDetails>> getLostFoundPetDetails(@Body Map jsonObject);

    @POST(ApiConstants.GET_MY_LOST_AND_FOUND)
    Call<ListRes<PetDetails>> getLostMyFoundList(@Body Map jsonObject);

    @POST(ApiConstants.GET_LOST_AND_FOUND)
    Call<LostAndFoundRes> getLostFound(@Body Map jsonObject);

    @POST(ApiConstants.GET_ALL_LIST_DAYS_MONTHS)
    Call<ResponseBody> getAllListDayMonths(@Body Map jsonObject);

    @POST(ApiConstants.CHANGE_PASSWORD)
    Call<ResponseBody> changePassword(@Body Map jsonObject);

    @POST(ApiConstants.GET_USER_PROFILE)
    Call<ResponseBody> userProfile(@Body Map jsonObject);

    @POST("accepted_notification")
    Call<ResponseBody> confirmBooking(@Body Map requestData);

    @POST("{walk_type}")
    Call<FitnessDetails> getWalkDetails(@Path("walk_type") String walkType, @Body Map requestParams);

    @POST(ApiConstants.GET_CHART_RESPONSE)
    Call<ChartResponse> getChartResponse(@Body Map jsonObject);

    @POST(ApiConstants.ACCEPTED_BOOKING_LIST)
    Call<ListRes<AcceptedBookingList>> acceptedBookingList(@Body Map req);*/




}

