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
import com.example.ecommerce.view.MainActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OnSaleAdapter extends RecyclerView.Adapter<OnSaleAdapter.viewHolder> implements ProductActions {
   Context context;
   boolean isShimmering= false;
   List<Product> productList;
   ClickListner clickListner;

    public OnSaleAdapter(@NonNull Context context, ClickListner clickListner) {
        this.context = context;
        productList = new ArrayList<>();
        makeDatabaseCall();
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_all,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        if(isShimmering){
            viewHolder.shimmerFrameLayout.startShimmer();
            viewHolder.shimmerFrameLayout.setVisibility(View.VISIBLE);
            viewHolder.relativeLayout.setVisibility(View.GONE);
        }else {
            Product product = productList.get(i);
            viewHolder.shimmerFrameLayout.stopShimmer();
            viewHolder.shimmerFrameLayout.setVisibility(View.GONE);
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            viewHolder.product_name.setText(product.getmItemName());
            viewHolder.priceOrg_.setText(product.getmPrice());
            try{
                int amount = Integer.parseInt(product.getmPrice());
                int discount = Integer.parseInt(product.getmDiscount());
                float applicableError = amount*(1-(discount/100.0f));
                viewHolder.price_.setText(String.valueOf(Math.floor(applicableError)));
            }catch (Exception e){

            }
            Picasso.get().load(product.getmImage1()).into(viewHolder.im_one);
            Picasso.get().load(product.getmImage2()).into(viewHolder.im_twwo);
            Picasso.get().load(product.getmImage3()).into(viewHolder.im_three);
        }

    }


    public void makeDatabaseCall(){
        new ProductController(this).get_all_new_onsale_products("2","0", ((MainActivity)context).getCaterogy().getmCategoryid());
    }

    @Override
    public int getItemCount() {
        if(productList.size()==0){
            return 10;
        }else {
          return   productList.size();
        }

    }

    @Override
    public void onFetchStart() {
        isShimmering = true;
        notifyDataSetChanged();
    }

    @Override
    public void onFetchProgress(Product product) {

    }

    @Override
    public void onFetchProgress(List<Product> products) {
        productList = products;
        isShimmering = false;
        notifyDataSetChanged();
    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed(Throwable t) {

    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView im_one, im_twwo,im_three;
        ShimmerFrameLayout shimmerFrameLayout;
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
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerlayout3);
            relativeLayout = itemView.findViewById(R.id.viewlayout3);
        }

        @Override
        public void onClick(View v) {
            if(!isShimmering)
            {
                clickListner.onClickPosition(v, Constants.TAG_PRODUCT_DETAIL, Utility.getStringFromObject(productList.get(getAdapterPosition())));
            }
        }
    }
}
