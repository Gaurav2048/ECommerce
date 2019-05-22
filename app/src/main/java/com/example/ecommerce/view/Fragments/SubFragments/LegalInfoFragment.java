package com.example.ecommerce.view.Fragments.SubFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Models.Interface.UI_Helpers.DataInterface;
import com.example.ecommerce.R;
import com.example.ecommerce.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegalInfoFragment extends Fragment implements View.OnClickListener, DataInterface {

    TextView receiver;
    public LegalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).setDataInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_legal_info, container, false);
        receiver = view.findViewById(R.id.receiver);

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onReceiveData(String data) {

    }
}
