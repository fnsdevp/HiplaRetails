package com.fnspl.hiplaretails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fnspl.hiplaretails.R;

public class ProductSliderAdapter extends PagerAdapter {
    Context context;

    public ProductSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.list_product_details,container,false);

        /*ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        Glide.with(getContext())
                .load(sliderList.get(position).getImagepath())
                .into(imageView);*/
        container.addView(v,0);
        return v;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
