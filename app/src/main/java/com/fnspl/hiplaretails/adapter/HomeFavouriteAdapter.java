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
import com.fnspl.hiplaretails.model.apiResponse.Favourites;
import com.fnspl.hiplaretails.model.apiResponse.ListFavoutite;
import com.fnspl.hiplaretails.model.apiResponse.ListHomeCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFavouriteAdapter extends RecyclerView.Adapter<HomeFavouriteAdapter.Holder> {

    Context context;
    List<ListFavoutite> list = new ArrayList<>();

    public HomeFavouriteAdapter(FragmentActivity activity,List<ListFavoutite> list) {
        context = activity;
        this.list = list;
    }

    public void setData(List<ListFavoutite> list){
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favourite,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Random rnd = new Random();
        ListFavoutite favoutite = list.get(position);
        int clr[]={context.getResources().getColor(R.color.rnd3),context.getResources().getColor(R.color.rnd1),context.getResources().getColor(R.color.rnd2)};

        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.tv_category_name.setTextColor(clr[new Random().nextInt(clr.length)]);
        holder.tv_category_name.setText(favoutite.getCategoryName());
        Glide.with(context)
                .load(favoutite.getUrl()+favoutite.getProductImage())
                .into(holder.img_product);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv_category_name,tv_category_desc;
        ImageView img_product;
        public Holder(View itemView) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.tv_category_name);
            img_product = itemView.findViewById(R.id.img_product);


        }
    }
}
