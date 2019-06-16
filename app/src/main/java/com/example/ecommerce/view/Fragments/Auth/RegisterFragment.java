package com.example.ecommerce.view.Fragments.Auth;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.Controllers.UserController;
import com.example.ecommerce.Models.DataTypes.Register;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.Interface.Actions.UserActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.AuthInterface;
import com.example.ecommerce.Models.Utilities.Validator;
import com.example.ecommerce.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements UserActions {

    public static RegisterFragment instance;

    EditText first_name,last_name,email,password,cnf_password;
    TextView message_dialog;
    ImageView cancel_first_name,cancel_last_name,cancel_email_address,cancel_password,cancel_cnf_password;
    Button RegisterButton;

    AuthInterface authInterface;

    UserController userController;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        first_name = view.findViewById(R.id.first_name);
        last_name=view.findViewById(R.id.last_name);
        email = view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        cnf_password = view.findViewById(R.id.cnf_password);

        message_dialog = view.findViewById(R.id.message_dialog);

        cancel_first_name = view.findViewById(R.id.cancel_first_name);
        cancel_last_name = view.findViewById(R.id.cancel_last_name);
        cancel_email_address = view.findViewById(R.id.cancel_email_address);
        cancel_password = view.findViewById(R.id.cancel_password);
        cancel_cnf_password = view.findViewById(R.id.cancel_cnf_password);
        RegisterButton = view.findViewById(R.id.RegisterButton);

        userController = new UserController(getContext(), this);


        initTextWatchers();

        initTextRemovers();

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidity();

                if(!error){
                    message_dialog.setVisibility(View.GONE);
                    authInterface.onLoad("register", first_name.getText().toString());
                    // controller here
                    userController.register(new Register(
                            first_name.getText().toString()+" "+last_name.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            "default.jpg"
                    ));

                }else{
                    message_dialog.setVisibility(View.VISIBLE);
                    message_dialog.setText(message);
                }
            }
        });


        return view;
    }

    boolean error = false;
    String message = "";

    private void checkValidity() {
        error = false;
        if(first_name.getText().toString().length()==0){
            error = true;
            message = "No first name found";
        }

        if(last_name.getText().toString().length()==0){
            error = true;
            message = "No last name found";
        }

        if(!Validator.isEmail(email.getText().toString())){
            error = true;
            message = "Email Not valid";
        }

        if(password.getText().toString().length()<8){
            error = true;
            message = "Invalid Password";
        }

        if(!(password.getText().toString().equals(cnf_password.getText().toString()))){
            error = true;
            message = "Passwords does not match";
        }
    }


    private void initTextRemovers() {

        cancel_first_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name.setText("");
                cancel_first_name.setVisibility(View.INVISIBLE);
            }
        });

        cancel_last_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_name.setText("");
                cancel_last_name.setVisibility(View.INVISIBLE);
            }
        });

        cancel_email_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
                cancel_email_address.setVisibility(View.INVISIBLE);
            }
        });

        cancel_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
                cancel_password.setVisibility(View.INVISIBLE);
            }
        });

        cancel_cnf_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnf_password.setText("");
                cancel_cnf_password.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void initTextWatchers() {

        first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    cancel_first_name.setVisibility(View.VISIBLE);
                }else {
                    cancel_first_name.setVisibility(View.INVISIBLE);
                }
            }
        });

        last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    cancel_last_name.setVisibility(View.VISIBLE);
                }else {
                    cancel_last_name.setVisibility(View.INVISIBLE);
                }
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    cancel_email_address.setVisibility(View.VISIBLE);
                }else {
                    cancel_email_address.setVisibility(View.INVISIBLE);
                }
            }
        });


        cnf_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    cancel_cnf_password.setVisibility(View.VISIBLE);
                }else {
                    cancel_cnf_password.setVisibility(View.INVISIBLE);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    cancel_password.setVisibility(View.VISIBLE);
                }else {
                    cancel_password.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public static RegisterFragment getInstance(){
        if(instance == null){
            instance = new RegisterFragment();
        }
        return instance;
    }

    @Override
    public void onUserLogin(User user) {

    }

    @Override
    public void onError(Throwable t) {
        Log.e( "onError: ",t.getMessage() );
    }

    @Override
    public void onStartOperation() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authInterface = (AuthInterface)getActivity();
    }

    @Override
    public void registerSuccess(Object user) {

        Log.e( "registerSuccess: ",new Gson().toJson(user.toString()) );

         //        try {
//            JSONObject jsonObject = new JSONObject();
//            boolean success = jsonObject.getBoolean("success");
//            if(success){
//                    authInterface.onSuccessOperation("register");
//            }else{
//                    authInterface.onSuccessOperation("registerFailed");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Log.e( "registerSuccess: ",String.valueOf(user) +" " );
    }
}
