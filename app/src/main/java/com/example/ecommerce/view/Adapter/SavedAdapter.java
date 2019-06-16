package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.Interface.UI_Helpers.EditCartInterface;
import com.example.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.viewHolder> {

    Context context;
    List<Cart> cartList;
    EditCartInterface cartInterface;

    public SavedAdapter(@NonNull Context context, List<Cart> cartList, EditCartInterface cartInterface) {
        this.context = context;
        this.cartList = cartList;
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_savd,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Cart cart = cartList.get(i);
        Picasso.get().load(cart.getImage()).into(viewHolder.imageView);
        viewHolder.name_product.setText(cart.getProductName());
        viewHolder.price.setText("$"+cart.getPrice());
        try{
            int real_price = Integer.parseInt(cart.getPrice());
            int discount= Integer.parseInt(cart.getDiscount());
            float price = real_price*(1-(discount/100.0f));
            viewHolder.price_after_discount.setText(String.valueOf(Math.floor(price)));
        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        Spinner spinner;
        TextView name_product,price,add_to_cart,remove,price_after_discount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_cart_image);
            name_product=itemView.findViewById(R.id.name_product);
            price=itemView.findViewById(R.id.price);
            spinner=itemView.findViewById(R.id.spinner);
            price_after_discount=itemView.findViewById(R.id.price_after_discount);
            add_to_cart=itemView.findViewById(R.id.add_to_cart);
            remove=itemView.findViewById(R.id.remove);
            add_to_cart.setOnClickListener(this);
            remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == add_to_cart){
                Cart cart = cartList.get(getAdapterPosition());
                cart.setFlag(1);
                cartInterface.updateItem(cart);
            }
        }
    }

}
