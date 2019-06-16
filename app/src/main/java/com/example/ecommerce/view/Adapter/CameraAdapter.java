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

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Utility.GeneralUtility;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.viewHolder> implements ProductActions {

    private static final String TAG = "CameraAdapter";
    Context context;
    ClickListner clickListner;
    List<Product> productList;
    ProductController productController;
    boolean isSchimming = false;

    public CameraAdapter(@NonNull Context context, ClickListner clickListner) {
        this.context = context;
        this.clickListner = clickListner;
        productList = new ArrayList<>();
        new ProductController(this).get_all_new_products("1", "22");


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_camera, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        if (isSchimming) {
            viewHolder.shimmerLayout1.setVisibility(View.VISIBLE);
            viewHolder.viewLayout.setVisibility(View.GONE);
            viewHolder.shimmerLayout1.startShimmer();

        } else {
            Product product = productList.get(i);
            viewHolder.shimmerLayout1.stopShimmer();
            viewHolder.shimmerLayout1.setVisibility(View.GONE);
            viewHolder.viewLayout.setVisibility(View.VISIBLE);
            viewHolder.product_text.setText(product.getmItemName());
            viewHolder.priceOrg.setText("$ " + product.getmPrice());
            try {
                int amount = Integer.parseInt(product.getmPrice());
                int discount = Integer.parseInt(product.getmDiscount());
                float applicableError = amount * (1 - (discount / 100.0f));
                viewHolder.price.setText(String.valueOf(Float.valueOf(applicableError)));
            } catch (Exception e) {

            }

            Picasso.get().load(product.getmImage1()).into(viewHolder.product_image);
            viewHolder.priceOrg.setPaintFlags(viewHolder.priceOrg.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

    @Override
    public int getItemCount() {
        if (productList.size() == 0) {
            return 10;
        } else {
            return productList.size();
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
        productList = products;
        isSchimming = false;
        Log.e(TAG, "onFetchProgress: " + products.size());
        notifyDataSetChanged();
    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed(Throwable t) {

    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView product_image, add_to_wish_list;
        ShimmerFrameLayout shimmerLayout1;
        RelativeLayout viewLayout;
        TextView product_text, price;
        TextView priceOrg;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            add_to_wish_list = itemView.findViewById(R.id.add_to_wish_list);
            priceOrg = (TextView) itemView.findViewById(R.id.priceOrg);
            shimmerLayout1 = (ShimmerFrameLayout) itemView.findViewById(R.id.shimmerLayout1);
            product_text = itemView.findViewById(R.id.product_text);
            price = itemView.findViewById(R.id.price);
            viewLayout = itemView.findViewById(R.id.viewLayout);
            itemView.setOnClickListener(this);
            add_to_wish_list.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Product product = productList.get(position);
            String data = GeneralUtility.getStringFromObject(product);
            if (v == add_to_wish_list) {
                clickListner.onClickPosition(v, Constants.TAG_ADD_TO_WISHLIST, data);
            } else if (v == itemView) {
                if (!isSchimming) {
                    clickListner.onClickPosition(v, "product", data);
                }
            }
        }
    }
}
