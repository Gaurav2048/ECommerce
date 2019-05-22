package com.example.ecommerce.view.Fragments;


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
public class AccountFragment extends Fragment implements View.OnClickListener {

    ClickListner clickListner;
    TextView myOrders, question_answer,legal,account_address,account_data;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        myOrders=view.findViewById(R.id.myOrders);
        account_data=view.findViewById(R.id.account_data);
        question_answer=view.findViewById(R.id.q_a);
        legal=view.findViewById(R.id.legal);
        account_address=view.findViewById(R.id.account_address);
        myOrders.setOnClickListener(this);
        account_data.setOnClickListener(this);
        question_answer.setOnClickListener(this);
        legal.setOnClickListener(this);
        account_address.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListner = (ClickListner) getActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.myOrders){
            clickListner.onClickPosition(v, Constants.TAG_MY_ORDER,"");
        }else if (v.getId()==R.id.q_a){
         //    clickListner.onClickPosition(v, Constants.TAG_QUESTION_ANSWER);
        }else if(v.getId()==R.id.legal){
            clickListner.onClickPosition(v,Constants.TAG_LEGAL,"");
        }else if(v.getId()==R.id.account_address){
            clickListner.onClickPosition(v,Constants.TAG_ADDRESS,"");
        }else if (v.getId() == R.id.account_data){
            clickListner.onClickPosition(v, Constants.TAG_ACCOUNT_INFO, "");
        }
    }
}
