package com.example.ecommerce.view.Fragments.Search;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerce.Controllers.Searchcontroller;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.Search;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Interface.UI_Helpers.SearchInterface;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.PopularAdapter;
import com.example.ecommerce.view.Adapter.PopularSearchAdapter;
import com.example.ecommerce.view.MainActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements SearchInterface {

    public static PopularFragment instance;
    String SearchPhrase;
    Searchcontroller searchcontroller;
    RecyclerView popularRecyclerView;
    ClickListner clickListner;
    ShimmerFrameLayout shimmerFrameLayout;

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_popular, container, false);
        searchcontroller = new Searchcontroller(this, getContext());
        popularRecyclerView= view.findViewById(R.id.popularRecyclerView);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerFrameLayout = view.findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        searchcontroller.getPopularResults(SearchPhrase, "10", "0");


        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        SearchPhrase = ((MainActivity)getActivity()).getPhrase();
        clickListner = (ClickListner) getActivity();
    }

    public static PopularFragment getInstance(){
        if(instance == null){
            instance = new PopularFragment();
        }
        return instance;
    }


    @Override
    public void onPhraseSelected(Search search) {

        if(getActivity()!=null)
        {
            if(search!= null){
                if(search.getProductList() != null && (((MainActivity)getActivity()).getPhrase().equals(search.getPhrase()))){
                    shimmerFrameLayout.setVisibility(View.GONE);
                    shimmerFrameLayout.stopShimmer();
                    popularRecyclerView.setVisibility(View.VISIBLE);
                    popularRecyclerView.setAdapter(new PopularSearchAdapter(getContext(), clickListner, search.getProductList()));
                }else {
                    Toast.makeText(getContext(), "no result", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getContext(), "no response", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
