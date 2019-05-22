package com.example.ecommerce.view.Fragments.SubFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegalFragment extends Fragment implements View.OnClickListener {

    TextView tem_of_use, privacy_policy,Affiliate_policy,infringement_policy,license,return_policy;
    ClickListner clickListner;
    public LegalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_legal, container, false);
        tem_of_use = view.findViewById(R.id.tem_of_use);
        privacy_policy = view.findViewById(R.id.privacy_policy);
        Affiliate_policy = view.findViewById(R.id.Affiliate_policy);
        infringement_policy = view.findViewById(R.id.infringement_policy);
        license = view.findViewById(R.id.license);
        return_policy = view.findViewById(R.id.return_policy);

        tem_of_use.setOnClickListener(this);
        privacy_policy.setOnClickListener(this);
        Affiliate_policy.setOnClickListener(this);
        infringement_policy.setOnClickListener(this);
        license.setOnClickListener(this);
        return_policy.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.tem_of_use){
            clickListner.onClickPosition(v, Constants.TAG_LEGAL_INFO, "");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner) getActivity() ;
    }
}
