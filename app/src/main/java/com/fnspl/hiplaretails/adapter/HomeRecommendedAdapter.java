package com.fnspl.hiplaretails.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.model.apiResponse.ListRecommended;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeRecommendedAdapter extends RecyclerView.Adapter<HomeRecommendedAdapter.Holder> {

    Context context;
    List<ListRecommended> list = new ArrayList<>();

    public HomeRecommendedAdapter(FragmentActivity activity,List<ListRecommended> list) {
        context = activity;
        this.list = list;
    }

    public void setData(List<ListRecommended> list){
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recommended,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ListRecommended recommended = list.get(position);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.tv_category_name.setTextColor(color);
        holder.tv_category_name.setText(recommended.getCategoryName());
        Glide.with(context)
                .load(recommended.getUrl()+recommended.getProductImage())
                .into(holder.img_recommended);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv_category_name,tv_category_desc;
        ImageView img_recommended;
        public Holder(View itemView) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.tv_category_name);
            img_recommended = itemView.findViewById(R.id.img_recommended);



        }
    }
}
