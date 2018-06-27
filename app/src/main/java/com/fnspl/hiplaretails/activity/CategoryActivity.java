package com.fnspl.hiplaretails.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.adapter.CategoryRecentlyAddedAdapter;
import com.fnspl.hiplaretails.fragment.CardFragment;
import com.fnspl.hiplaretails.fragment.HomeFragment;
import com.fnspl.hiplaretails.model.apiResponse.ListRecentlyBrowsed;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends BaseHomeActivity {

    RecyclerView recycler_recently_added, recycler_latest_arrival;
    CategoryRecentlyAddedAdapter recentlyAddedAdapter;
    List<ListRecentlyBrowsed> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_category);
        recycler_recently_added = findViewById(R.id.recycler_recently_added);
        recycler_latest_arrival = findViewById(R.id.recycler_latest_arrival);

        recentlyAddedAdapter  = new CategoryRecentlyAddedAdapter(this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CategoryActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recycler_recently_added.setHasFixedSize(true);
        recycler_recently_added.setLayoutManager(linearLayoutManager);
        recycler_recently_added.setAdapter(recentlyAddedAdapter);
        recycler_recently_added.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(CategoryActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recycler_latest_arrival.setHasFixedSize(true);
        recycler_latest_arrival.setLayoutManager(linearLayoutManager1);
        recycler_latest_arrival.setAdapter(recentlyAddedAdapter);
        recycler_latest_arrival.setNestedScrollingEnabled(false);


        img_home.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_left));


        //setContentView(R.layout.activity_category);
    }

    public void backHome(View view){
        finish();
    }


}
