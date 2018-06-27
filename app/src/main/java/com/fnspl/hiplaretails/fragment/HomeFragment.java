package com.fnspl.hiplaretails.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.activity.CategoryActivity;
import com.fnspl.hiplaretails.adapter.HomeCategoryAdapter;
import com.fnspl.hiplaretails.adapter.HomeFavouriteAdapter;
import com.fnspl.hiplaretails.adapter.HomeRecentlyAddedAdapter;
import com.fnspl.hiplaretails.adapter.HomeRecommendedAdapter;
import com.fnspl.hiplaretails.application.ApiClient;
import com.fnspl.hiplaretails.application.Apis;
import com.fnspl.hiplaretails.databinding.FragmentHomeBinding;
import com.fnspl.hiplaretails.interFace.ClickCallBack;
import com.fnspl.hiplaretails.model.apiResponse.Favourites;
import com.fnspl.hiplaretails.model.apiResponse.HomeCategory;
import com.fnspl.hiplaretails.model.apiResponse.HomeSliderOffer;
import com.fnspl.hiplaretails.model.apiResponse.ListFavoutite;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeCategory;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeSlider;
import com.fnspl.hiplaretails.model.apiResponse.ListRecentlyBrowsed;
import com.fnspl.hiplaretails.model.apiResponse.ListRecommended;
import com.fnspl.hiplaretails.model.apiResponse.ListRes;
import com.fnspl.hiplaretails.model.apiResponse.RecommendedDetails;
import com.fnspl.hiplaretails.networkApi.NetworkConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;
    ViewPager viewPager;
    MyViewPager myViewPager;
    HomeCategoryAdapter categoryAdapter;
    HomeRecentlyAddedAdapter recentlyAddedAdapter;
    HomeRecommendedAdapter recommendedAdapter;
    HomeFavouriteAdapter favouriteAdapter;
    RecyclerView recycler_category,recycler_recommended,recycler_favourite,recycler_recently_added;
    NetworkConnection connection;
    List<HomeSliderOffer> sliderList = new ArrayList<>();
    List<ListHomeCategory> categoryList = new ArrayList<>();
    List<ListFavoutite> favoutiteList = new ArrayList<>();
    List<ListRecommended> recommendedList = new ArrayList<>();
    List<ListRecentlyBrowsed> recentlyBrowsedList = new ArrayList<>();
    public HomeFragment() { }
    FragmentHomeBinding binding;

    Timer timer;
    Apis mApis;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        connection = new NetworkConnection(getActivity());
        mApis = ApiClient.getClient().getApis();

        categoryAdapter = new HomeCategoryAdapter(getActivity(),categoryList, new ClickCallBack() {
            @Override
            public void onClick(int a) {

                //Bundle bundle = new Bundle();

                startActivity(new Intent(getActivity(), CategoryActivity.class));

               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,new CategoryFragment()).commit();
            }
        });
        recentlyAddedAdapter = new HomeRecentlyAddedAdapter(getActivity(),recentlyBrowsedList);
        recommendedAdapter = new HomeRecommendedAdapter(getActivity(),recommendedList);
        favouriteAdapter = new HomeFavouriteAdapter(getActivity(),favoutiteList);


        InitView(view);
        //setViewPagerScroll();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    private void InitView(View view){
        recycler_category = view.findViewById(R.id.recycler_shop_by_store);
        recycler_recommended = view.findViewById(R.id.recycler_recommended);
        recycler_favourite = view.findViewById(R.id.recycler_favourite);
        recycler_recently_added = view.findViewById(R.id.recycler_recently_added);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycler_category.setHasFixedSize(true);
        recycler_category.setLayoutManager(linearLayoutManager);
        recycler_category.setAdapter(categoryAdapter);
        recycler_category.setNestedScrollingEnabled(false);


        LinearLayoutManager linearRecommended = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycler_recommended.setHasFixedSize(true);
        recycler_recommended.setLayoutManager(linearRecommended);
        recycler_recommended.setAdapter(recommendedAdapter);
        recycler_recommended.setNestedScrollingEnabled(false);

        LinearLayoutManager linearFavourite = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycler_favourite.setHasFixedSize(true);
        recycler_favourite.setLayoutManager(linearFavourite);
        recycler_favourite.setAdapter(favouriteAdapter);
        recycler_favourite.setNestedScrollingEnabled(false);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recycler_recently_added.setHasFixedSize(true);
        recycler_recently_added.setLayoutManager(gridLayoutManager);
        recycler_recently_added.setAdapter(recentlyAddedAdapter);
        recycler_recently_added.setNestedScrollingEnabled(false);


        myViewPager = new MyViewPager();
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(myViewPager);

        TabLayout tab = view.findViewById(R.id.tab_layout);
        tab.setupWithViewPager(viewPager, true);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
                //page.setRotationY(position * -30);
            }
        });


        if (connection.isActive()) {
            sliderApi();
            categoryApi();
            favouriteApi();
            recommendedApi();
        }

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private class MyViewPager extends PagerAdapter {
        LayoutInflater inflate;
        @Override
        public int getCount() {
            return sliderList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View v = getLayoutInflater().inflate(R.layout.tutorial_image,container,false);

            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            Glide.with(getContext())
                    .load(sliderList.get(position).getImagepath())
                    .into(imageView);
           // imageLoader.displayImage(list.get(position).getImagepath(),imageView,option());

            container.addView(v,0);

            return v;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    private void setViewPagerScroll(final int count){
        if (count>0){
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    viewPager.post(new Runnable(){

                        @Override
                        public void run() {
                            viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%count);
                        }
                    });
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 6000, 3000);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }

    }

    private void sliderApi() {
        showProgressBar();

        Call<ListHomeSlider> call = mApis.sliderOffer();
        call.enqueue(new Callback<ListHomeSlider>() {
            @Override
            public void onResponse(Call<ListHomeSlider> call, Response<ListHomeSlider> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        sliderList = response.body().getOfferslist();
                        myViewPager.notifyDataSetChanged();
                        setViewPagerScroll(sliderList.size());
                    }
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ListHomeSlider> call, Throwable t) {

                hideProgressBar();
            }
        });


    }
    private void categoryApi() {

        Call<HomeCategory> call = mApis.homeCategory();
        call.enqueue(new Callback<HomeCategory>() {
            @Override
            public void onResponse(Call<HomeCategory> call, Response<HomeCategory> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")) {
                        categoryList = response.body().getCatlist();
                        categoryAdapter.setData(categoryList);
                    }else {

                    }
                }

            }

            @Override
            public void onFailure(Call<HomeCategory> call, Throwable t) {

            }
        });



    }
    private void favouriteApi() {
        Map<String,String> map = new HashMap<>();
       // map.put("user_id",app.getAppSettings().__uId);
        map.put("user_id","2");

        Call<Favourites> call = mApis.favourite(map);
        call.enqueue(new Callback<Favourites>() {
            @Override
            public void onResponse(Call<Favourites> call, Response<Favourites> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        favoutiteList = response.body().getProductlist();

                        favouriteAdapter.setData(favoutiteList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Favourites> call, Throwable t) {


            }
        });




    }

    private void recommendedApi() {
        Map<String,String> map = new HashMap<>();
        // map.put("user_id",app.getAppSettings().__uId);
        map.put("user_id","2");

        Call<RecommendedDetails> call = mApis.recommended(map);
        call.enqueue(new Callback<RecommendedDetails>() {
            @Override
            public void onResponse(Call<RecommendedDetails> call, @NonNull Response<RecommendedDetails> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        recommendedList = response.body().getProductlist();

                        recommendedAdapter.setData(recommendedList);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecommendedDetails> call, Throwable t) {


            }
        });




    }

    private void recentlyBrowsed() {
        Map<String,String> map = new HashMap<>();
        // map.put("user_id",app.getAppSettings().__uId);
        map.put("user_id","2");

        Call<RecommendedDetails> call = mApis.recently_browsed(map);
        call.enqueue(new Callback<RecommendedDetails>() {
            @Override
            public void onResponse(Call<RecommendedDetails> call, Response<RecommendedDetails> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        recommendedList = response.body().getProductlist();

                        recommendedAdapter.setData(recommendedList);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecommendedDetails> call, Throwable t) {


            }
        });




    }
}
