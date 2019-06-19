package com.example.ecommerce.view;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.CategoryController;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.Interface.Actions.CategoryActions;
import com.example.ecommerce.Models.Interface.Actions.UserActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.AuthInterface;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.AuthPagerAdapter;
import com.example.ecommerce.view.widget.NoSwipePager;
import com.flipboard.bottomsheet.BottomSheetLayout;

public class LoginActivity extends AppCompatActivity implements AuthInterface {

    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    NoSwipePager viewPager;

    BottomSheetLayout bottomsheet;
    View view;

    AuthPagerAdapter adapter;
    CategoryController categoryController;
    Button loginButton;
    TextView name, message;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_main);

        String loginDetails = new AppPreferences(getApplicationContext()).getLoginDetails();
        if(loginDetails != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class ));
            finish();
        }

        view = LayoutInflater.from(getApplicationContext()).inflate( R.layout.unit_load,bottomsheet,false );
        name = view.findViewById(R.id.name);
        message = view.findViewById(R.id.message);
        viewPager = findViewById(R.id.viewPager);
        textView = findViewById(R.id.textV);
        bottomsheet = findViewById(R.id.bottomsheet);
        viewPager.setPagingEnabled(false);
        adapter = new AuthPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.e( "onPageScrolled: ",v+" " );
                if(i==0){
                    textView.setText("LOGIN");
                }else {
                    textView.setText("REGISTER");
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 1){
            viewPager.setCurrentItem(0,true);
        }else
        {
            super.onBackPressed();
        }

    }

    @Override
    public void onSwipe() {
        if(viewPager.getCurrentItem()==0){
            viewPager.setCurrentItem(1,true);
        }else{
            viewPager.setCurrentItem(0,true);
        }
    }

    @Override
    public void onLoad(String operation, String name_) {
       if(operation.equals("login"))
       {
           name.setText("Hi"+ " "+name_);
           message.setText("Please wait while we are \n  getting your details... ");
           bottomsheet.showWithSheetView(view);
       }

    }

    @Override
    public void onSuccessOperation(String type) {
        if(type.equals("loginSuccess")){
            bottomsheet.dismissSheet();
            startActivity(new Intent(getApplicationContext(), MainActivity.class ));
            finish();
        }else if(type.equals("loginFailed")){

        }
    }
}
