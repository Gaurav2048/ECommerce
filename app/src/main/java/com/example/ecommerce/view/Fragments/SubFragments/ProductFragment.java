package com.example.ecommerce.view.Fragments.SubFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.ItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    TextView yourProducts;
    RecyclerView productRecyclerView;
    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product, container, false);
        yourProducts=view.findViewById(R.id.yourProducts);
        productRecyclerView=view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productRecyclerView.setAdapter(new ItemAdapter(getContext()));
        return view;
    }

}
