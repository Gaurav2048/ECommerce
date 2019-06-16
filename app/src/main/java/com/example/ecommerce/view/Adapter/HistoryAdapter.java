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

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder>  {
   Context context;
   boolean isShimmering= false;
   List<String> history;
   ClickListner clickListner;

    public HistoryAdapter(@NonNull Context context,  List<String> history, ClickListner clickListner) {
        this.context = context;
        this.clickListner = clickListner;
        this.history = history;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_history,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        viewHolder.product_name.setText(history.get(i));

    }




    @Override
    public int getItemCount() {
          return   history.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView product_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            product_name=itemView.findViewById(R.id.history_text);
        }

        @Override
        public void onClick(View v) {
            clickListner.onClickPosition(v,Constants.TAG_ACTION_SEARCH,history.get(getAdapterPosition()));
        }
    }
}
