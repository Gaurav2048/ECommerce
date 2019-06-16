package com.example.ecommerce.view.Fragments.Auth;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecommerce.Controllers.UserController;
import com.example.ecommerce.Models.DataTypes.Register;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.Interface.Actions.UserActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.AuthInterface;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.Models.Utilities.Validator;
import com.example.ecommerce.R;
import com.example.ecommerce.view.MainActivity;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, UserActions {


    public static LoginFragment instance;
    TextView signUpHer;
    Button loginButton;
    EditText username;
    EditText password;
    AppPreferences appPreferences;
    UserController userController;
    TextView error_showing;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_login, container, false);
        signUpHer=view.findViewById(R.id.signUpHer);
        appPreferences = new AppPreferences(getContext());
        username=view.findViewById(R.id.username);
        error_showing=view.findViewById(R.id.error_showing);
        password = view.findViewById(R.id.password);
        loginButton=view.findViewById(R.id.loginButton);
        userController = new UserController(getContext(), this);
        signUpHer.setPaintFlags(signUpHer.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        signUpHer.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        return  view;
    }

    public static LoginFragment getInstance(){
        if(instance == null){
            instance = new LoginFragment() ;
        }
        return instance;
    }

    AuthInterface authInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authInterface = (AuthInterface)getActivity();

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signUpHer){
            authInterface.onSwipe();
        }else if(v.getId() ==R.id.loginButton){
            if(Validator.isEmail(username.getText().toString()))
            {
                userController.Login(username.getText().toString(), password.getText().toString());

            }else {
                //  TODO show invalid email
                username.setError("invalid Password");
            }
        }
    }

    @Override
    public void onUserLogin(User user) {
        if(user!=null){
            appPreferences.setLoginDetails(user);
            authInterface.onSuccessOperation("loginSuccess");
        }else {
            authInterface.onSuccessOperation("loginFailed");
        }
      }

    @Override
    public void onError(Throwable t) {
        Log.e( "onError: ",t.getMessage() );
    }

    @Override
    public void onStartOperation() {
        authInterface.onLoad("login", username.getText().toString());
    }



    @Override
    public void registerSuccess(Object user) {
        Log.e( "registerSuccess: ",user +" " );
    }
}
