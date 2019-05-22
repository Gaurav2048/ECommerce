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
public class AddEditAddressFragment extends Fragment implements DataInterface {

    TextView show_data;
    public AddEditAddressFragment() {
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
        View view=  inflater.inflate(R.layout.fragment_add_edit_address, container, false);
        show_data=view.findViewById(R.id.show_data);


        return  view;
    }

    @Override
    public void onReceiveData(String data) {
        show_data.setText(data);
    }
}
