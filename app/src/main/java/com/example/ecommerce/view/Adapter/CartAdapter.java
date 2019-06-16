package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.UI_Helpers.EditCartInterface;
import com.example.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView .Adapter<CartAdapter.viewHolder>{

    List<Cart> cartList;
    EditCartInterface cartInterface;
    Context context;

    public CartAdapter(@NonNull Context context, List<Cart> cartList, EditCartInterface cartInterface) {
        this.cartList=cartList;
        this.context = context;
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_cart,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Cart cart = cartList.get(i);
        Picasso.get().load(cart.getImage()).into(viewHolder.imageView);
        viewHolder.name_product.setText(cart.getProductName());
        viewHolder.price.setText("$"+cart.getPrice());
    }

    public List<Cart> getDataSet(){
        return  cartList;
    }

    public void replaceDataSet(List<Cart> mNewList){
        cartList.clear();
        notifyDataSetChanged();
        cartList = mNewList;
    }

    public void add(Cart cart) {

        cartList.add(cart);
        notifyDataSetChanged();

    }

    public void removeItem(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Cart cart, int position) {
        cartList.add(position, cart);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RelativeLayout viewBackground, viewForeground;
        ImageView imageView;
        Spinner spinner;
        TextView name_product,price,save_for_later,remove;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_cart_image);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            name_product=itemView.findViewById(R.id.name_product);
            price=itemView.findViewById(R.id.price);
            spinner=itemView.findViewById(R.id.spinner);
            save_for_later=itemView.findViewById(R.id.save_for_later);
            remove=itemView.findViewById(R.id.remove);

            save_for_later.setOnClickListener(this);
            remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == remove){

            }else if(v == save_for_later){
                Cart cart = cartList.get(getAdapterPosition());
                cart.setFlag(0);
                cartInterface.updateItem(cart);
            }
        }
    }
}
