package com.example.ecommerce.view.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ecommerce.view.Fragments.Search.PopularFragment;
import com.example.ecommerce.view.Fragments.Search.SuggestedFragment;

public class SearchpagerAdapter extends FragmentPagerAdapter {
    public SearchpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
         return SuggestedFragment.getInstance();
        }else {
            return PopularFragment.getInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==0 ){
            return "Suggested";
        }else {
            return  "Popular";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
