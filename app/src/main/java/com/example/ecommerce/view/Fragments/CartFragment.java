package com.example.ecommerce.view.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Controllers.CartController;
import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.Interface.Actions.CartAction;
import com.example.ecommerce.view.Adapter.CartAdapter;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Utility.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,
        CartAction {

    RecyclerView cartRecyclerView;
    CoordinatorLayout coordinatorLayout;
    List<Cart> cartList ;
    CartAdapter adapter;
    TextView cartValue;
    CartController cartController;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);
        cartController = new CartController(getContext(),this);
        cartController.getCart();
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(cartRecyclerView);
        return  view;
    }

    private void initView(View view) {
        cartRecyclerView = view.findViewById(R.id.CartRecyclr);
        coordinatorLayout=view.findViewById(R.id.coordinatorLayout);
        cartValue=view.findViewById(R.id.cartValue);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartAdapter.viewHolder) {
//            String name = adapter.getDataSet().get(viewHolder.getAdapterPosition()).getItemName();
              String name = "random";

            final Cart deletedItem = adapter.getDataSet().get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            cartController.RemoveFromCart(deletedItem);
            adapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from List!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    adapter.restoreItem(deletedItem, deletedIndex);
                    cartController.addToCart(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public void onResultCount(int Count) {

    }

    @Override
    public void onResultCartList(List<Cart> cartList) {
        this.cartList = cartList;
        adapter=new CartAdapter(getContext(),cartList);
        cartRecyclerView.setAdapter(adapter);
        Log.e( "onResultCartList: ",cartList.get(0).getQuantity()+" " );;
        calculateCartValue();
    }

    int Total = 0 ;
    private void calculateCartValue() {
        for(int i=0;i<cartList.size();i++){
            int Quantity = cartList.get(i).getQuantity();
            int Price = Integer.parseInt(cartList.get(i).getPrice());
            Total += Quantity*Price;
        }
        cartValue.setText("$"+Total);
    }

    @Override
    public void onRemoveCartItem() {

    }

    @Override
    public void onCartItemAdded() {

    }
}
