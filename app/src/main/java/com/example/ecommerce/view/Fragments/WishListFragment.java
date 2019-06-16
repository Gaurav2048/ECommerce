package com.example.ecommerce.view.Fragments;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.WishAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {

    RecyclerView wishListRecyclerView;
    ClickListner listner;
    List<Product> wishList;
    LinearLayout no_item_layout;
    AppPreferences appPreferences;

    public WishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        wishListRecyclerView = view.findViewById(R.id.wishListRecyclerView);
        no_item_layout = view.findViewById(R.id.no_item_layout);
        populateData();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listner = (ClickListner) getActivity();
        appPreferences = new AppPreferences(getContext());
        wishList = Utility.getWishList(appPreferences.getWishList());
        if(wishListRecyclerView!=null && no_item_layout !=null){
            populateData();
        }
    }


    private void populateData(){
        if (wishList != null ) {
            if (wishList.size() > 0) {
                wishListRecyclerView.setVisibility(View.VISIBLE);
                wishListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                wishListRecyclerView.setAdapter(new WishAdapter(getContext(), listner, wishList));
            } else {
                startPlaceHolderAnimation();
            }
        }else{
            startPlaceHolderAnimation();
        }
    }


    private void startPlaceHolderAnimation() {
        PropertyValuesHolder animation = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.1f, 1f);
        PropertyValuesHolder animation1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.1f, 1f);
        PropertyValuesHolder animation3 = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(no_item_layout, animation, animation1, animation3);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(400);
        animator.setStartDelay(100);
        animator.start();
    }
}
/*
*  PropertyValuesHolder animation =  PropertyValuesHolder.ofFloat(View.SCALE_X, 0.1f, 1f);
        PropertyValuesHolder animation1 =  PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.1f, 1f);
        PropertyValuesHolder animation3 =  PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(aimate, animation, animation1, animation3);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(2000);
        animator.setStartDelay(1000);
        animator.start();
* */