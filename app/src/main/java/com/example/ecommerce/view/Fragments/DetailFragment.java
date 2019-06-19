package com.example.ecommerce.view.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.view.Adapter.TabAdapter;
import com.example.ecommerce.view.Fragments.SubFragments.AllFragment;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Fragments.SubFragments.ExclusiveFragment;
import com.example.ecommerce.view.Fragments.SubFragments.OnSaleFragment;
import com.example.ecommerce.view.Fragments.SubFragments.PopularFragment;
import com.example.ecommerce.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TabLayout tablayout;
    ViewPager viewPager;
    TabAdapter tabAdapter;
    TextView category_name;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        tablayout=view.findViewById(R.id.textV);
        viewPager=view.findViewById(R.id.viewPager);
        category_name= view.findViewById(R.id.category_name);

        tabAdapter = new TabAdapter(getChildFragmentManager());
        tabAdapter.addFragment(new AllFragment(), "  All  ");
        tabAdapter.addFragment(new PopularFragment(), "  Popular  ");
        tabAdapter.addFragment(new OnSaleFragment(), "  On Sale  ");
        tabAdapter.addFragment(new ExclusiveFragment(), "  Exclusive  ");

        viewPager.setAdapter(tabAdapter);

        tablayout.setupWithViewPager(viewPager);

        category category = ((MainActivity)getActivity()).getCaterogy();
        if(category!=null){{
            category_name.setText(category.getmCategoryName());
        }}

        changeTabsFont();
        return  view;

    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tablayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(ResourcesCompat.getFont(getContext(), R.font.playfairdisplayregular));
                }
            }
        }
    }

}
