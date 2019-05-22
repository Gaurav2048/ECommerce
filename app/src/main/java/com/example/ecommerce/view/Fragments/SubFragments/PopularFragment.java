package com.example.ecommerce.view.Fragments.SubFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.AllAdapter;
import com.example.ecommerce.view.Adapter.PopularAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    RecyclerView allRecyclerview;
    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all, container, false);
        allRecyclerview =view.findViewById(R.id.allRecyclerview);
        allRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        allRecyclerview.setAdapter(new PopularAdapter(getContext()


        ));

        return  view;
    }

}
