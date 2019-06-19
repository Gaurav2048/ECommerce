package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestedAdapter extends RecyclerView.Adapter<SuggestedAdapter.viewHolder>  {
   Context context;
   boolean isShimmering= false;
   List<Product> productList ;
    List<Product> wishList ;
   ClickListner clickListner;

    public SuggestedAdapter(@NonNull Context context, ClickListner clickListner, List<Product> products) {
        this.context = context;
        productList = new ArrayList<>();
        this.clickListner = clickListner;
        wishList = Utility.getWishList(new AppPreferences(context).getWishList());
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

        viewHolder.isAddedToWishList(viewHolder.upvote_button);


    }




    @Override
    public int getItemCount() {
          return   productList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView discount_textview, product_name,price, upvoteCount;
        ImageView search_img ;
        ImageView featured;
        CheckBox upvote_button;

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
            upvote_button.setOnClickListener(this);


        }

        public void isAddedToWishList(CheckBox checkBox){
            if(wishList!=null) {
                String itemId = productList.get(getAdapterPosition()).getId();
                for (int i = 0; i < wishList.size(); i++) {
                    if (wishList.get(i).getId().equals(itemId)) {
                        checkBox.setChecked(true);
                        break;
                    } else {
                        checkBox.setChecked(false);
                    }
                }
            }
        }

        @Override
        public void onClick(View v) {
            if(!isShimmering)
            {
                if(v==itemView)
                {
                    clickListner.onClickPosition(v, Constants.TAG_PRODUCT_DETAIL, Utility.getStringFromObject(productList.get(getAdapterPosition())));
                }else if (v == upvote_button){
                    Utility.buttonAnimation(v);
                    clickListner.onClickPosition(v, Constants.TAG_ADD_TO_WISHLIST, Utility.getStringFromObject(productList.get(getAdapterPosition())));
                }

            }
        }
    }
}
