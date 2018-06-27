package com.fnspl.hiplaretails.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.application.ApiClient;
import com.fnspl.hiplaretails.application.Apis;
import com.fnspl.hiplaretails.databinding.ActivityLoginBinding;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeSlider;
import com.fnspl.hiplaretails.model.apiResponse.User;
import com.fnspl.hiplaretails.model.apiResponse.UserDetails;
import com.fnspl.hiplaretails.networkApi.NetworkConnection;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;
    String email,password;
    boolean isRemember = false;
    String Tag = LoginActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setActivity(this);


        binding.switchRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isRemember = b;


            }
        });
        if (app.getAppSettings().__isRemember){
            binding.etEmail.setText(app.getAppSettings().__uEmail);
            binding.etPassword.setText(app.getAppSettings().__password);
            binding.switchRemember.setChecked(app.getAppSettings().__isRemember);
        }


    }


    public void signIn(){
        if (connection.isActive()){
            if (validate()){
                signInApi();
            }
        }
        //

    }
    public void signUp(){
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }

    public boolean validate() {
        email = binding.etEmail.getText().toString().trim();
        password = binding.etPassword.getText().toString().trim();

        if (email.length() == 0) {
            binding.etEmail.setError("Put Your Email");
            return false;
        }

        if (!isValidEmail(email)) {
            binding.etEmail.setError("Put Your Valid Email");
            return false;
        }

        if (password.length() == 0) {
            binding.etPassword.setError("Put Your Password");
            return false;
        }

        return true;
    }



    private void signInApi() {
        showProgressBar();

        Map<String, RequestBody> map = new HashMap<>();
        map.put("password",toRequestBody(password));
        map.put("username",toRequestBody(email));
        map.put("deviceId",toRequestBody(app.getAppSettings().__fcmToken));
        map.put("dtype",toRequestBody("android"));

        Log(Tag,map.toString()+"");

        Call<UserDetails> call = mApis.login(map);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        User user = response.body().getUser().get(0);
                        app.getAppSettings().setSession(true,user);
                        app.getAppSettings().setSession(isRemember,email,password);
                        Log(Tag,isRemember+"");
                        startActivity(new Intent(LoginActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                    }


                }
                assert response.body() != null;
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
