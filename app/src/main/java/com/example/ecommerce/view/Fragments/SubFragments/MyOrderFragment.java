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
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.MyOrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment  implements View.OnClickListener {

    ClickListner clickListner;
    RecyclerView myOrderRecyclerViews;

    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_order, container, false);
        myOrderRecyclerViews= view.findViewById(R.id.myOrderRecyclerViews);
        myOrderRecyclerViews.setLayoutManager(new LinearLayoutManager(getContext()));
        myOrderRecyclerViews.setAdapter(new MyOrderAdapter(getContext(), clickListner));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner) getActivity();
    }

    @Override
    public void onClick(View v) {

    }
}
