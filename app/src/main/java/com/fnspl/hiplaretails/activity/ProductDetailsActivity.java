package com.fnspl.hiplaretails.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fnspl.hiplaretails.R;
import com.fnspl.hiplaretails.adapter.HomeRecentlyAddedAdapter;
import com.fnspl.hiplaretails.adapter.ProductSliderAdapter;
import com.fnspl.hiplaretails.model.apiResponse.ListRecentlyBrowsed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    String product_id="";
    ProductSliderAdapter productSliderAdapter;
    ViewPager view_pager;
    TextView tv_price;
    ImageView img_home,img_barcode,img_navigation,img_more;

    SizeAdapter sizeAdapter;
    ColorAdapter colorAdapter;
    RecyclerView recycler_view_color,recycler_view_size;
    BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_n);
        initView();
        product_id  = getIntent().getStringExtra("product_id");




    }

    public void initView(){
        img_home = findViewById(R.id.img_home);
        img_barcode = findViewById(R.id.img_barcode);
        img_navigation = findViewById(R.id.img_navigation);
        img_more = findViewById(R.id.img_more);

        productSliderAdapter = new ProductSliderAdapter(this);
        view_pager = findViewById(R.id.view_pager);
        view_pager.setAdapter(productSliderAdapter);
        tv_price = findViewById(R.id.tv_price);
        tv_price.setPaintFlags(tv_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        sizeAdapter = new SizeAdapter();
        colorAdapter = new ColorAdapter();

        img_home.setOnClickListener(this);
        img_barcode.setOnClickListener(this);
        img_navigation.setOnClickListener(this);
        img_more.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_home:
                if (dialog!=null && dialog.isShowing()){
                    Log.e("dialog","dialog");
                    dialog.dismiss();
                }
                finish();
                break;
            case R.id.img_barcode:

                break;

            case R.id.img_navigation:

                break;
            case R.id.img_more:
                showMoreOptionDialog();
                break;


        }

    }


    private void showMoreOptionDialog(){

        dialog = new BottomSheetDialog(this, R.style.BottomSheetStyle);
        View view = getLayoutInflater().inflate(R.layout.buttom_sheet_dialog,null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        final TextView tv_size = view.findViewById(R.id.tv_size);
        final TextView tv_color = view.findViewById(R.id.tv_color);

        recycler_view_size = view.findViewById(R.id.recycler_view_size);
        recycler_view_color = view.findViewById(R.id.recycler_view_color);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailsActivity.this,LinearLayoutManager.VERTICAL,false);
        recycler_view_size.setHasFixedSize(true);
        recycler_view_size.setLayoutManager(linearLayoutManager);
        recycler_view_size.setAdapter(sizeAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetailsActivity.this,3);
        recycler_view_color.setHasFixedSize(true);
        recycler_view_color.setLayoutManager(gridLayoutManager);
        recycler_view_color.setAdapter(colorAdapter);
        recycler_view_color.setNestedScrollingEnabled(false);


        tv_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_color.setTextColor(getResources().getColor(R.color.tab_selected_color_dialog));
                tv_size.setTextColor(getResources().getColor(R.color.white));
                tv_size.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_left_3corner5dp));
                tv_color.setBackground(getResources().getDrawable(R.drawable.rectangle_white_right_3corner5dp));

                recycler_view_size.setVisibility(View.VISIBLE);
                recycler_view_color.setVisibility(View.GONE);
            }
        });

        tv_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_size.setTextColor(getResources().getColor(R.color.tab_selected_color_dialog));
                tv_color.setTextColor(getResources().getColor(R.color.white));
                tv_size.setBackground(getResources().getDrawable(R.drawable.rectangle_white_left_3corner5dp));
                tv_color.setBackground(getResources().getDrawable(R.drawable.rectangle_grey_right_3corner5dp));

                recycler_view_size.setVisibility(View.GONE);
                recycler_view_color.setVisibility(View.VISIBLE);
            }
        });


        dialog.show();

    }

    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Holder> {

        int pos = 0;

        int color[] = {R.color.black, R.color.red, R.color.green, R.color.blue, R.color.brown, R.color.yellow};
        String color_name[] = {"BLACK", "RED", "GREEN", "BLUE", "BROWN", "YELLOW"};

        public ColorAdapter() {

        }


        @NonNull
        @Override
        public ColorAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_color_item, parent, false);

            return new ColorAdapter.Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ColorAdapter.Holder holder, final int position) {

            holder.tv_title.setText(color_name[position]);
            //img_view.setImageDrawable(getDrawableBackground(R.color.red));
            holder.img_view.setColorFilter(ContextCompat.getColor(ProductDetailsActivity.this, color[position]), android.graphics.PorterDuff.Mode.MULTIPLY);

            if (position!=2) {
                holder.tv_out_of_store.setVisibility(View.INVISIBLE);

            }
            if (pos == position) {
                holder.img_check.setVisibility(View.VISIBLE);
            } else {
                holder.img_check.setVisibility(View.GONE);
            }

            holder.img_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pos = position;
                    notifyDataSetChanged();

                }
            });

        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class Holder extends RecyclerView.ViewHolder {
            ImageView img_view, img_check;

            TextView tv_title, tv_out_of_store;

            public Holder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_out_of_store = itemView.findViewById(R.id.tv_out_of_store);
                img_view = itemView.findViewById(R.id.img_view);
                img_check = itemView.findViewById(R.id.img_check);

            }
        }

        public Drawable getDrawableBackground(int color) {
            Drawable mDrawable = getResources().getDrawable(R.drawable.circular_image);
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
            return mDrawable;
        }
    }

    public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.Holder> {

        int pos = 0;

        String name[] = {"S", "M", "L", "XL", "XXL"};


        @NonNull
        @Override
        public SizeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_size_item, parent, false);

            return new SizeAdapter.Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SizeAdapter.Holder holder, final int position) {

            holder.tv_title.setText(name[position]);

            if (position!=3) {
                holder.tv_out_of_store.setVisibility(View.INVISIBLE);

            }
            if (pos == position) {
                holder.rd_check.setChecked(true);
                holder.tv_title.setTextColor(getResources().getColor(R.color.text_select));
                Log.e("position",pos+ " *****");
            } else {
                holder.rd_check.setChecked(false);
                holder.tv_title.setTextColor(getResources().getColor(R.color.text_un_select));
                Log.e("position",pos+ "---- ");
            }

            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pos = position;
                    Log.e("position",pos+ " ");
                    notifyDataSetChanged();

                }
            });
            holder.rd_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos = position;
                    Log.e("position",pos+ " ");
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class Holder extends RecyclerView.ViewHolder {
            LinearLayout ll_main;
            RadioButton rd_check;

            TextView tv_title, tv_out_of_store;

            public Holder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_out_of_store = itemView.findViewById(R.id.tv_out_of_store);
                rd_check = itemView.findViewById(R.id.rd_check);
                ll_main = itemView.findViewById(R.id.ll_main);

            }
        }

        /*public Drawable getDrawableBackground(int color) {
            Drawable mDrawable = getResources().getDrawable(R.drawable.circular_image);
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
            return mDrawable;
        }*/
    }
}
