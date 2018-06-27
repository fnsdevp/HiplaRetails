package com.fnspl.hiplaretails.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.application.ApiClient;
import com.fnspl.hiplaretails.application.Apis;
import com.fnspl.hiplaretails.application.App;
import com.fnspl.hiplaretails.application.AppSettings;
import com.fnspl.hiplaretails.databinding.ActivitySignUpBinding;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeSlider;
import com.fnspl.hiplaretails.model.apiResponse.User;
import com.fnspl.hiplaretails.model.apiResponse.UserDetails;
import com.fnspl.hiplaretails.networkApi.NetworkConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;
    String email,first_name,last_name,password,address,phone,pinCode;
    String Tag = SignUpActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        binding.setActivity(this);


    }

    public void signUp(){
        if (connection.isActive()){
            if (validate()){
                signUpApi();
            }
        }
        //startActivity(new Intent(SignUpActivity.this,MainActivity.class));
    }


    public boolean validate() {
        email = binding.etEmail.getText().toString().trim();
        first_name = binding.etFName.getText().toString().trim();
        last_name = binding.etLName.getText().toString().trim();
        phone = binding.etPhone.getText().toString().trim();
        address = binding.etAddress.getText().toString().trim();
        pinCode = binding.etPin.getText().toString().trim();
        password = binding.etPassword.getText().toString().trim();

        if (email.length() == 0) {
            binding.etEmail.setError("Put Your Email");
            binding.etEmail.setFocusable(true);
            return false;
        }

        if (!isValidEmail(email)) {
            binding.etEmail.setError("Put Your Valid Email");
            binding.etEmail.setFocusable(true);
            return false;
        }
        if (phone.length() == 0) {
            binding.etPhone.setError("Put Your Phone No.");
            binding.etPhone.setFocusable(true);
            return false;
        }
        if (phone.length() != 10) {
            binding.etPhone.setError("Put Your Valid Phone No.");
            binding.etPhone.setFocusable(true);
            return false;
        }
        if (password.length() == 0) {
            binding.etPassword.setError("Put Your Password");
            binding.etPassword.setFocusable(true);

            return false;
        }
        if (first_name.length() == 0) {
            binding.etFName.setError("Put Your First Name");
            binding.etLName.setFocusable(true);
            return false;
        }
        if (last_name.length() == 0) {
            binding.etLName.setError("Put Your Last Name");
            binding.etLName.setFocusable(true);
            return false;
        }
        if (address.length() == 0) {
            binding.etAddress.setError("Put Your Address");
            binding.etAddress.setFocusable(true);
            return false;
        }
        if (pinCode.length() == 0) {
            binding.etPin.setError("Put Your Pincode");
            binding.etPin.setFocusable(true);
            return false;
        }



        return true;
    }

    private void signUpApi() {
        showProgressBar();
        Map<String, RequestBody> map = new HashMap<>();
        map.put("email",toRequestBody(email));
        map.put("phone",toRequestBody(phone));
        map.put("password",toRequestBody(password));
        map.put("fname",toRequestBody(first_name));
        map.put("lname",toRequestBody(last_name));
        //map.put("deviceId",app.getAppSettings().__fcmToken);
        //map.put("dtype","android");
        map.put("address",toRequestBody(address));
        map.put("pincode",toRequestBody(pinCode));

        Log(Tag,map.toString()+"");

        Call<UserDetails> call = mApis.registration(map);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        User user = response.body().getUser().get(0);
                        app.getAppSettings().setSession(true,user);
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                    }


                }
                Toast(response.body().getMessage());
                hideProgressBar();

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                hideProgressBar();
            }
        });




    }
}
