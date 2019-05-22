package com.example.ecommerce.view.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.view.Adapter.HomeAdapter;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForYouFragment extends Fragment implements ProductActions {

    private static final String TAG = "ForYouFragment";
    RecyclerView homeRecycerView;
    ClickListner clickListner;
    ProductActions productActions;
    public ForYouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_for_you, container, false);
        homeRecycerView=(RecyclerView) view.findViewById(R.id.homeRecyclerview);
        homeRecycerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRecycerView.setAdapter(new HomeAdapter(getContext(),clickListner));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner)getActivity();
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Product product) {

    }

    @Override
    public void onFetchProgress(List<Product> products) {
        Log.e(TAG, "onFetchProgress: "+products.size() );
    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed(Throwable t) {
        Log.e(TAG, "onFetchFailed: "+t.getMessage() );
    }
}
