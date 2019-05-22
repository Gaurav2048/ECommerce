package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder> implements ProductActions {
   Context context;
   boolean isShimmering= false;
   List<Product> productList;
    public ItemAdapter(@NonNull Context context) {
        this.context = context;
        productList = new ArrayList<>();
        makeDatabaseCall();
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
            viewHolder.shimmerFrameLayout.stopShimmer();
            viewHolder.shimmerFrameLayout.setVisibility(View.GONE);
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            Picasso.get().load("https://images.pexels.com/photos/437037/pexels-photo-437037.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(viewHolder.im_one);
            Picasso.get().load("https://images.pexels.com/photos/267394/pexels-photo-267394.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(viewHolder.im_twwo);
            Picasso.get().load("https://images.pexels.com/photos/393047/pexels-photo-393047.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(viewHolder.im_three);
        }

    }


    public void makeDatabaseCall(){
        new ProductController(this).get_Products_List_by_category("2","0");
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

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView im_one, im_twwo,im_three;
        ShimmerFrameLayout shimmerFrameLayout;
        RelativeLayout relativeLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            im_one=(ImageView) itemView.findViewById(R.id.im_one);
            im_twwo=(ImageView) itemView.findViewById(R.id.im_two);
            im_three=(ImageView) itemView.findViewById(R.id.im_three);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerlayout3);
            relativeLayout = itemView.findViewById(R.id.viewlayout3);
        }
    }
}
