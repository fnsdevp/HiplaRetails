package com.fnspl.hiplaretails.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.fragment.CardFragment;
import com.fnspl.hiplaretails.fragment.HomeFragment;

public class BaseHomeActivity extends BaseActivity {

    ImageView img_bottom_home,img_bottom_tag,img_bottom_coupon,img_bottom_card,img_bottom_map,img_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_home);
        InitView();


    }

    protected void addContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ((FrameLayout) findViewById(R.id.content_home)).addView(inflater.inflate(layoutResId, null),
                new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

    }


    public void InitView(){
        img_bottom_tag = findViewById(R.id.img_bottom_tag);
        img_bottom_card = findViewById(R.id.img_bottom_card);
        img_bottom_coupon = findViewById(R.id.img_bottom_coupon);
        img_bottom_map = findViewById(R.id.img_bottom_map);
        img_bottom_home = findViewById(R.id.img_bottom_home);
        img_home = findViewById(R.id.img_home);


    }


    public void homeButton(View view) {
        img_bottom_home.setImageDrawable(getResources().getDrawable(R.drawable.nav_home_selected));
        img_bottom_tag.setImageDrawable(getResources().getDrawable(R.drawable.nav_tag));
        img_bottom_card.setImageDrawable(getResources().getDrawable(R.drawable.nav_card));
        img_bottom_coupon.setImageDrawable(getResources().getDrawable(R.drawable.nav_coupon));
        img_bottom_map.setImageDrawable(getResources().getDrawable(R.drawable.nav_map));

        getSupportFragmentManager().beginTransaction().replace(R.id.content,new HomeFragment()).commit();

    }

    public void homeTag(View view) {
        img_bottom_home.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
        img_bottom_tag.setImageDrawable(getResources().getDrawable(R.drawable.nav_tag_selected));
        img_bottom_card.setImageDrawable(getResources().getDrawable(R.drawable.nav_card));
        img_bottom_coupon.setImageDrawable(getResources().getDrawable(R.drawable.nav_coupon));
        img_bottom_map.setImageDrawable(getResources().getDrawable(R.drawable.nav_map));
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new CardFragment()).commit();
    }

    public void homeCard(View view) {
        img_bottom_home.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
        img_bottom_tag.setImageDrawable(getResources().getDrawable(R.drawable.nav_tag));
        img_bottom_card.setImageDrawable(getResources().getDrawable(R.drawable.nav_card_selected));
        img_bottom_coupon.setImageDrawable(getResources().getDrawable(R.drawable.nav_coupon));
        img_bottom_map.setImageDrawable(getResources().getDrawable(R.drawable.nav_map));

        getSupportFragmentManager().beginTransaction().replace(R.id.content,new CardFragment()).commit();
    }

    public void homeCoupon(View view) {
        img_bottom_home.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
        img_bottom_tag.setImageDrawable(getResources().getDrawable(R.drawable.nav_tag));
        img_bottom_card.setImageDrawable(getResources().getDrawable(R.drawable.nav_card));
        img_bottom_coupon.setImageDrawable(getResources().getDrawable(R.drawable.nav_coupon_selected));
        img_bottom_map.setImageDrawable(getResources().getDrawable(R.drawable.nav_map));
    }

    public void homeMap(View view) {
        img_bottom_home.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
        img_bottom_tag.setImageDrawable(getResources().getDrawable(R.drawable.nav_tag));
        img_bottom_card.setImageDrawable(getResources().getDrawable(R.drawable.nav_card));
        img_bottom_coupon.setImageDrawable(getResources().getDrawable(R.drawable.nav_coupon));
        img_bottom_map.setImageDrawable(getResources().getDrawable(R.drawable.nav_map_selected));
    }


}
