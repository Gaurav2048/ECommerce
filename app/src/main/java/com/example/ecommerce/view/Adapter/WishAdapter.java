package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.viewHolder>  {
   Context context;
   List<Product> productList;
   ClickListner clickListner;

    public WishAdapter(@NonNull Context context, ClickListner clickListner, List<Product> products) {
        this.context = context;
        this.clickListner = clickListner;
        this.productList = products;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_all,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

            Product product = productList.get(i);
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            viewHolder.product_name.setText(product.getmItemName());
            viewHolder.priceOrg_.setText(product.getmPrice());
            try{
                int amount = Integer.parseInt(product.getmPrice());
                int discount = Integer.parseInt(product.getmDiscount());
                int applicableError = amount*(1-(discount/100));
                viewHolder.price_.setText(String.valueOf(applicableError));
            }catch (Exception e){

            }
            Picasso.get().load(product.getmImage1()).into(viewHolder.im_one);
            Picasso.get().load(product.getmImage2()).into(viewHolder.im_twwo);
            Picasso.get().load(product.getmImage3()).into(viewHolder.im_three);

    }




    @Override
    public int getItemCount() {
          return   productList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView im_one, im_twwo,im_three;
        TextView product_name,price_,priceOrg_;
        ImageView wishList,help_part;
        RelativeLayout relativeLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            wishList = itemView.findViewById(R.id.wishList);
            help_part = itemView.findViewById(R.id.help_part);
            im_one=(ImageView) itemView.findViewById(R.id.im_one);
            im_twwo=(ImageView) itemView.findViewById(R.id.im_two);
            price_ = itemView.findViewById(R.id.price_);
            priceOrg_ =itemView.findViewById(R.id.priceOrg_);
            im_three=(ImageView) itemView.findViewById(R.id.im_three);
            product_name=itemView.findViewById(R.id.product_name);
            relativeLayout = itemView.findViewById(R.id.viewlayout3);
        }

        @Override
        public void onClick(View v) {
                clickListner.onClickPosition(v, Constants.TAG_PRODUCT_DETAIL, Utility.getStringFromObject(productList.get(getAdapterPosition())));

        }
    }
}
