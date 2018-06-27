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
import com.fnspl.hiplaretails.interFace.ClickCallBack;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.Holder> {

    Context context;
    List<ListHomeCategory> list = new ArrayList<>();
    ClickCallBack clickCallBack;

    public HomeCategoryAdapter(FragmentActivity activity,List<ListHomeCategory> list,ClickCallBack clickCallBack) {
        context = activity;
        this.list = list;
        this.clickCallBack = clickCallBack;
    }

    public void setData(List<ListHomeCategory> list){
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.tv_category_name.setTextColor(color);
        Glide.with(context)
                .load(list.get(position).getThumb())
                .into(holder.img_category);
        holder.tv_category_name.setText(list.get(position).getName());
        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv_category_name,tv_category_desc;
        ImageView img_category;
        public Holder(View itemView) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.tv_category_name);
            img_category = itemView.findViewById(R.id.img_category);


        }
    }
}
