package com.example.ecommerce.view.Fragments.Search;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.HistoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    public static  HistoryFragment instance;
    RecyclerView historyRecycler;
    TextView history_indicator;
    TextView clear_history;
    List<String> history;
    ClickListner clickListner;
    AppPreferences appPreferences;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);
        historyRecycler = view.findViewById(R.id.historyRecycler);
        history_indicator = view.findViewById(R.id.history_indicator);
        clear_history=view.findViewById(R.id.clear_history);
        appPreferences = new AppPreferences(getContext());

        history = Utility.getHistory(appPreferences.getSearchHistroy());
        if(history == null || history.size() == 0 ){
            // todo NO HISTORY FOUND
            history_indicator.setText("NO RECENT SEARCHES");
            clear_history.setVisibility(View.GONE);

        }else {
            // populate the recyclerview
            history_indicator.setText("RECENT SEARCHES");
            clear_history.setVisibility(View.VISIBLE);
            historyRecycler.setAdapter(new HistoryAdapter(getContext(), history,clickListner));
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner)getActivity();
    }

    public static HistoryFragment getInstance(){
        if (instance == null) {
            instance = new HistoryFragment();
        }
        return instance;
    }

}
