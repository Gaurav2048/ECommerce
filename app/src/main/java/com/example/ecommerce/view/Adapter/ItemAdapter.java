package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder>   {
   Context context;
    List<Product> productList;
    public ItemAdapter(@NonNull Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;

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
            Picasso.get().load(product.getmImage1()).into(viewHolder.im_one);
            Picasso.get().load(product.getmImage2()).into(viewHolder.im_twwo);
            Picasso.get().load(product.getmImage3()).into(viewHolder.im_three);
            viewHolder.product_name.setText(product.getmItemName());
            viewHolder.priceOrg_.setText(product.getmPrice());
            try{
                int realPrice = Integer.parseInt(product.getmPrice());
                int discount = Integer.parseInt(product.getmDiscount());
                Log.e( "onBindViewHolder: ",realPrice+" "+discount );
                float realPrice1 =  (realPrice*(1-(discount/100.0f)));
                Log.e( "onBindViewHolder: ",realPrice1+" " );
                viewHolder.price_.setText(String.valueOf(Math.floor(realPrice1)));
            }catch (Exception e){
                viewHolder.price_.setText("voew");
            }


    }





    public void setData(List<Product> products){

    }

    @Override
    public int getItemCount() {
        if(productList.size()==0){
            return 10;
        }else {
            return   productList.size();
        }

    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView im_one, im_twwo,im_three,wishList;
        TextView product_name,price_,priceOrg_;
        RelativeLayout relativeLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            im_one=(ImageView) itemView.findViewById(R.id.im_one);
            im_twwo=(ImageView) itemView.findViewById(R.id.im_two);
            im_three=(ImageView) itemView.findViewById(R.id.im_three);
            priceOrg_ = itemView.findViewById(R.id.priceOrg_);
            product_name = itemView.findViewById(R.id.product_name);
            wishList = itemView.findViewById(R.id.wishList);
            price_=itemView.findViewById(R.id.price_);
            relativeLayout = itemView.findViewById(R.id.viewlayout3);
        }
    }
}
