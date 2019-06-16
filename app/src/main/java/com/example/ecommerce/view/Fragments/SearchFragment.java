package com.example.ecommerce.view.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Interface.UI_Helpers.SearchInterface;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Fragments.Search.HistoryFragment;
import com.example.ecommerce.view.Fragments.Search.SearchResultFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    ImageView apply_filter;
    EditText searchWidget;ImageView removeSearchText;
    ClickListner clickListner;
    AppPreferences appPreferences;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        apply_filter = view.findViewById(R.id.apply_filter);
        removeSearchText=view.findViewById(R.id.removeSearchText);
        apply_filter.setOnClickListener(this);
        appPreferences = new AppPreferences(getContext());
        searchWidget = view.findViewById(R.id.searchWidget);
        getChildFragmentManager().beginTransaction().replace(R.id.search_container, HistoryFragment.getInstance(), "history").commit();
        initTextWatcher();

        return view;
    }



    private void initTextWatcher() {

        searchWidget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 4) {
                    if (getChildFragmentManager().findFragmentByTag("search") == null) {
                        getChildFragmentManager().beginTransaction().replace(R.id.search_container, new SearchResultFragment(), "search")
                                .addToBackStack("search")
                                .commit();
                    }else {
                        getChildFragmentManager().popBackStack();
                        getChildFragmentManager().beginTransaction().replace(R.id.search_container, new SearchResultFragment(), "search")
                                .addToBackStack("search")
                                .commit();
                    }

                   clickListner.onClickPosition(searchWidget, Constants.TAG_ACTION_SEARCH, s.toString());

                } else if (s.toString().trim().length() == 0) {
                    Toast.makeText(getContext(), getChildFragmentManager().getBackStackEntryCount() + " ", Toast.LENGTH_SHORT).show();
                    getChildFragmentManager().popBackStack();
                }else {
                    removeSearchText.setVisibility(View.VISIBLE);
                }
            }
        });


        searchWidget.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (getChildFragmentManager().findFragmentByTag("search") == null) {
                        getChildFragmentManager().beginTransaction().replace(R.id.search_container, new SearchResultFragment(), "search")
                                .addToBackStack("search")
                                .commit();
                    }else {
                        getChildFragmentManager().popBackStack();
                        getChildFragmentManager().beginTransaction().replace(R.id.search_container, new SearchResultFragment(), "search")
                                .addToBackStack("search")
                                .commit();
                    }

                    clickListner.onClickPosition(searchWidget, Constants.TAG_ACTION_SEARCH, searchWidget.getText().toString());
                    Toast.makeText(getContext(), searchWidget.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        removeSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWidget.setText("");
                removeSearchText.setVisibility(View.GONE);
            }
        });

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
