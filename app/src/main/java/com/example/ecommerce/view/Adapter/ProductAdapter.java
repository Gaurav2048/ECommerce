package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.CategoryController;
import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.Models.Interface.Actions.CategoryActions;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHolder> implements ProductActions {

    private static final String TAG = "CameraAdapter";
    Context context;
    ClickListner clickListner;
    List<Product> productList;
    boolean isSchimming=false ;
    category category;
    public ProductAdapter(@NonNull Context context, ClickListner clickListner, int position) {
        this.context = context;
        this.clickListner=clickListner;
        productList = new ArrayList<>();
        new ProductController(this).get_New_popular_Products_List("2");
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_camera,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        if(isSchimming){
            viewHolder.shimmerLayout1.setVisibility(View.VISIBLE);
            viewHolder.viewLayout.setVisibility(View.GONE);
            viewHolder.shimmerLayout1.startShimmer();

        }else{
            viewHolder.shimmerLayout1.stopShimmer();
            viewHolder.shimmerLayout1.setVisibility(View.GONE);
            viewHolder.viewLayout.setVisibility(View.VISIBLE);
            Picasso.get().load("https://images.pexels.com/photos/821738/pexels-photo-821738.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(viewHolder.product_image);
            viewHolder.priceOrg.setPaintFlags(viewHolder.priceOrg.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

    @Override
    public int getItemCount() {
        if(productList.size()==0){
            return 10;
        }else{
            return  productList.size();
        }
    }


    @Override
    public void onFetchStart() {
        isSchimming = true;
        notifyDataSetChanged();

    }

    @Override
    public void onFetchProgress(Product product) {

    }

    @Override
    public void onFetchProgress(List<Product> products) {
        productList =products;
        isSchimming = false;
        Log.e(TAG, "onFetchProgress: "+productList.size() );
        notifyDataSetChanged();

    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed(Throwable t) {
        Log.e(TAG, "onFetchFailed: "+t.getMessage() );
    }



    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView product_image;
    ShimmerFrameLayout shimmerLayout1;
    RelativeLayout viewLayout;
    TextView priceOrg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            product_image=(ImageView) itemView.findViewById(R.id.product_image);
            priceOrg=(TextView) itemView.findViewById(R.id.priceOrg);
            shimmerLayout1=(ShimmerFrameLayout) itemView.findViewById(R.id.shimmerLayout1);
            viewLayout=itemView.findViewById(R.id.viewLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListner.onClickPosition(v,"product","");
         }
    }
}
