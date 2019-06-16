package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.R;
import com.squareup.picasso.Picasso;

public class SliderAdapter  extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    Product product;

    public SliderAdapter(Context context, Product product) {
        this.context = context;
        this.product = product;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view= layoutInflater.inflate(R.layout.slide_layut,container,false);
        ImageView imageView = view.findViewById(R.id.image_slide);
        container.addView(view);
        if(position==0){
            Picasso.get().load(product.getmImage1()).into(imageView);
        }else if (position ==1){
            Picasso.get().load(product.getmImage2()).into(imageView);
        }else if(position == 2){
            Picasso.get().load(product.getmImage3()).into(imageView);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object );
    }
}
