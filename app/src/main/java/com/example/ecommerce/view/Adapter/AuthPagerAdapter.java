package com.example.ecommerce.view.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.ecommerce.view.Fragments.Auth.LoginFragment;
import com.example.ecommerce.view.Fragments.Auth.RegisterFragment;

public class AuthPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public AuthPagerAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return LoginFragment.getInstance();
            case 1:
                return RegisterFragment.getInstance();

            default:
                return LoginFragment.getInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "LOGIN";
        }else {
            return "REGISTER";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
