package com.fnspl.hiplaretails.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.databinding.ActivityAfterSplashBinding;

public class LandingActivity extends BaseActivity {

    private ActivityAfterSplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_after_splash);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_after_splash);
        binding.setActivity(this);

        if (app.getAppSettings().__isLoggedIn){
            startActivity(new Intent(LandingActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    public void signUp(){
        startActivity(new Intent(LandingActivity.this,SignUpActivity.class));
    }

    public void signIn(){
        startActivity(new Intent(LandingActivity.this,LoginActivity.class));
    }
}
