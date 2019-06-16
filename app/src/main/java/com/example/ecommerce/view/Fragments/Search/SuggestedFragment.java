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
import com.example.ecommerce.view.Adapter.SuggestedAdapter;
import com.example.ecommerce.view.MainActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestedFragment extends Fragment implements SearchInterface {

    public static SuggestedFragment instance;
    RecyclerView suggestedRecyclerView;
    Searchcontroller searchcontroller;
    String searchPhrase;
    ShimmerFrameLayout shimmerLayout;
    ClickListner clickListner;


    public SuggestedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_suggested, container, false);
        searchcontroller = new Searchcontroller(this, getContext());
        shimmerLayout = view.findViewById(R.id.shimmerLayout);
        suggestedRecyclerView = view.findViewById(R.id.suggestedRecyclerView);
        suggestedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchcontroller.getSuggestionsFromPhrase(searchPhrase, "10", "0");
        suggestedRecyclerView.setVisibility(View.GONE);
        shimmerLayout.startShimmer();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        searchPhrase = ((MainActivity)getActivity()).getPhrase();
        clickListner = (ClickListner)getActivity();
    }

    public static SuggestedFragment getInstance(){
        if(instance == null){
            instance = new SuggestedFragment();
        }
        return instance;
    }



    @Override
    public void onPhraseSelected(Search search) {
       if(getActivity()!=null)
       {
           if(search!= null){
               if(search.getProductList() != null && (((MainActivity)getActivity()).getPhrase().equals(search.getPhrase()))){
                   shimmerLayout.setVisibility(View.GONE);
                   shimmerLayout.stopShimmer();
                   suggestedRecyclerView.setVisibility(View.VISIBLE);
                   suggestedRecyclerView.setAdapter(new SuggestedAdapter(getContext(), clickListner, search.getProductList()));
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
