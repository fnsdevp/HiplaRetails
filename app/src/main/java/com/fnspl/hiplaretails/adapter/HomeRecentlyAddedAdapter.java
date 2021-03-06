package com.fnspl.hiplaretails.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.model.apiResponse.ListFavoutite;
import com.fnspl.hiplaretails.model.apiResponse.ListRecentlyBrowsed;
import com.fnspl.hiplaretails.model.apiResponse.ListRecommended;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeRecentlyAddedAdapter extends RecyclerView.Adapter<HomeRecentlyAddedAdapter.Holder> {

    Context context;
    List<ListRecentlyBrowsed> list = new ArrayList<>();
    public HomeRecentlyAddedAdapter(FragmentActivity activity,List<ListRecentlyBrowsed> list) {
        context = activity;
        this.list = list;
    }
    public void setData(List<ListRecentlyBrowsed> list){
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recently_added,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       // ListRecentlyBrowsed recentlyBrowsed = list.get(position);
        Random rnd = new Random();
        int clr[]={context.getResources().getColor(R.color.rnd3),context.getResources().getColor(R.color.rnd1),context.getResources().getColor(R.color.rnd2)};
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

       // holder.tv_category_name.setTextColor(new Random().nextInt(clr.length));
        holder.tv_category_name.setTextColor(clr[new Random().nextInt(clr.length)]);

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv_category_name,tv_category_desc;
        public Holder(View itemView) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.tv_category_name);
            tv_category_desc = itemView.findViewById(R.id.tv_category_desc);

        }
    }
}
