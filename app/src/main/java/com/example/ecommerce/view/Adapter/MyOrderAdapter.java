package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.viewHolder> {


    ClickListner clickListner;
    Context context;
    public MyOrderAdapter(@NonNull Context context, ClickListner clickListner) {
        this.clickListner = clickListner;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_my_order, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        RatingBar ratingBar;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingBars);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListner.onClickPosition(v, Constants.TAG_ORDER_ITEM_DETAIL, "");
        }
    }
}
