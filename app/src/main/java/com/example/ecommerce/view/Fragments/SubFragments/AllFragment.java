package com.example.ecommerce.view.Fragments.SubFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.view.Adapter.AllAdapter;
import com.example.ecommerce.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    RecyclerView allRecyclerview;
    LinearLayoutManager layoutManager;
    AllAdapter allAdapter;
    ClickListner clickListner;

    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all, container, false);
        layoutManager = new LinearLayoutManager(getContext());
        allRecyclerview =view.findViewById(R.id.allRecyclerview);
        allRecyclerview.setLayoutManager(layoutManager);
        allAdapter = new AllAdapter(getContext(),clickListner);
        allRecyclerview.setAdapter(allAdapter);



        return  view;
    }

}
