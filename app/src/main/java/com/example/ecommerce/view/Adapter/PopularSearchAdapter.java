package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PopularSearchAdapter extends RecyclerView.Adapter<PopularSearchAdapter.viewHolder>  {
   Context context;
   boolean isShimmering= false;
   List<Product> productList ;
   ClickListner clickListner;

    public PopularSearchAdapter(@NonNull Context context, ClickListner clickListner, List<Product> products) {
        this.context = context;
        productList = new ArrayList<>();
        this.clickListner = clickListner;
        this.productList = products;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_search,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Product product = productList.get(i);
        Picasso.get().load(product.getmImage1()).into(viewHolder.search_img);
        viewHolder.product_name.setText(product.getmItemName());
        viewHolder.discount_textview.setText(product.getmDiscount()+"%");
        viewHolder.upvoteCount.setText(product.getUpvote()+" UPVOTES");
        if(product.getIsupvoted().equals("1")){
          //   viewHolder.upvote_button.setImageResource(R.drawable.);
        }

    }




    @Override
    public int getItemCount() {
          return   productList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView discount_textview, product_name,price, upvoteCount;
        ImageView search_img,upvote_button ;
        ImageView featured;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            discount_textview = itemView.findViewById(R.id.discount_textview);
            search_img = itemView.findViewById(R.id.search_img);
            featured = itemView.findViewById(R.id.featured);
            product_name=itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            upvoteCount = itemView.findViewById(R.id.upvoteCount);
            upvote_button = itemView.findViewById(R.id.upvote_button);


        }

        @Override
        public void onClick(View v) {
            if(!isShimmering)
            {

            }
        }
    }
}
