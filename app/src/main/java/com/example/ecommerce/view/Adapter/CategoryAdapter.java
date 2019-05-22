package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ecommerce.Controllers.CategoryController;
import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.Models.Interface.Actions.CategoryActions;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> implements CategoryActions {
    Context context;
    List<category> categoryList;
    boolean isShimming = false;
    ClickListner clickListner;

    public CategoryAdapter(@NonNull Context context, ClickListner clickListner) {
        this.context = context;
        categoryList = new ArrayList<>();
        this.clickListner = clickListner;
        new CategoryController(context, this).getCategories();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_category, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        if (isShimming) {
            viewHolder.shimmerFrameLayout.setVisibility(View.VISIBLE);
            viewHolder.shimmerFrameLayout.startShimmer();
            viewHolder.categorylayout.setVisibility(View.GONE);
        } else {
            viewHolder.shimmerFrameLayout.setVisibility(View.GONE);
            viewHolder.shimmerFrameLayout.stopShimmer();
            viewHolder.categorylayout.setVisibility(View.VISIBLE);
            Picasso.get().load("https://images.pexels.com/photos/69463/sowa-key-ring-keychain-key-ring-pendant-69463.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(viewHolder.category_image);
        }
    }

    @Override
    public int getItemCount() {
        if (categoryList.size() == 0) {
            return 5;
        } else {
            return categoryList.size();
        }
    }

    @Override
    public void onStartFetch() {
    isShimming = true;
    notifyDataSetChanged();
    }

    @Override
    public void onFetchProgress(List<category> categories) {
        categoryList = categories;
        isShimming = false;
        notifyDataSetChanged();
    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onError(Throwable t) {

    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView category_image;
        ShimmerFrameLayout shimmerFrameLayout;
        RelativeLayout categorylayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_image);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerlayout2);
            categorylayout = itemView.findViewById(R.id.categorylayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListner.onClickPosition(v, Constants.TAG_PRODUCT_VIEW,"");
        }
    }

}
