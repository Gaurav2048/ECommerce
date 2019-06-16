package com.example.ecommerce.view.Fragments.Search;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.SearchpagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    public static SearchResultFragment instance;
    TabLayout tablayout;
    ViewPager searchViewPager;
    SearchpagerAdapter adapter;

    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_, container, false);
        tablayout = view.findViewById(R.id.tablayout);
        searchViewPager=view.findViewById(R.id.searchViewPager);
        adapter = new SearchpagerAdapter(getChildFragmentManager());
        searchViewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(searchViewPager);

        return view;
    }

    public static  SearchResultFragment getInstance(){
        if(instance == null){
            instance = new SearchResultFragment();
        }
        return instance;
    }



}
