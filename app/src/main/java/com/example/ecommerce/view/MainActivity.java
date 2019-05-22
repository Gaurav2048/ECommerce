package com.example.ecommerce.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.DataInterface;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.view.Adapter.BottomBarAdapter;
import com.example.ecommerce.view.Fragments.AccountFragment;
import com.example.ecommerce.view.Fragments.CartFragment;
import com.example.ecommerce.view.Fragments.DetailFragment;
import com.example.ecommerce.view.Fragments.ForYouFragment;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Fragments.SearchFragment;
import com.example.ecommerce.view.Fragments.SubFragments.AccountInfoFragment;
import com.example.ecommerce.view.Fragments.SubFragments.AddEditAddressFragment;
import com.example.ecommerce.view.Fragments.SubFragments.AddressFragment;
import com.example.ecommerce.view.Fragments.SubFragments.LegalFragment;
import com.example.ecommerce.view.Fragments.SubFragments.LegalInfoFragment;
import com.example.ecommerce.view.Fragments.SubFragments.MyOrderFragment;
import com.example.ecommerce.view.Fragments.SubFragments.OrderItemFragment;
import com.example.ecommerce.view.Fragments.SubFragments.ProductFragment;
import com.example.ecommerce.view.Fragments.SubFragments.QAFragment;
import com.example.ecommerce.view.widget.NoSwipePager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListner {




    AHBottomNavigation bottomNavigation;
    NoSwipePager viewPager;
    ProductController controller;
    DataInterface dataInterface;
    private BottomBarAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
     //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setupViewPager();


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        setupBottomNavBehaviors();
        setupBottomNavStyle();


        addBottomNavigationItems();


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                  viewPager.setCurrentItem(position,false);
                  return true;
            }
        });

// controller initiated and the activity class is implementing Product action interface. And this is how we will use the controller to fetch data from controller methods
       /// controller = new ProductController(MainActivity.this);

       /**
        *     to fetch data from the controller
        *     controller.get_Products_List();
        */

    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public void setupBottomNavBehaviors() {
//        bottomNavigation.setBehaviorTranslationEnabled(false);

        /*
        Before enabling this. Change MainActivity theme to MyTheme.TranslucentNavigation in
        AndroidManifest.
        Warning: Toolbar Clipping might occur. Solve this by wrapping it in a LinearLayout with a top
        View of 24dp (status bar size) height.
         */
        bottomNavigation.setTranslucentNavigationEnabled(false);
    }


    private void setupBottomNavStyle() {
        /*
        Set Bottom Navigation colors. Accent color for active item,
        Inactive color when its view is disabled.
        Will not be visible if setColored(true) and default current item is set.
         */
        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        bottomNavigation.setAccentColor(fetchColor(R.color.mellon_rd));
        bottomNavigation.setInactiveColor(fetchColor(R.color.gray));

        // Colors for selected (active) and non-selected items.


        //  Enables Reveal effect




        //  Displays item Title always (for selected and non-selected items)
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    }

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.viewPager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(new ForYouFragment());
        pagerAdapter.addFragments(new SearchFragment());
        pagerAdapter.addFragments(new ForYouFragment());
        pagerAdapter.addFragments(new CartFragment());
        pagerAdapter.addFragments(new AccountFragment());

        // detail of products category wise 5

        pagerAdapter.addFragments(new DetailFragment());

        // detail of orders placed earlier 6
        pagerAdapter.addFragments(new MyOrderFragment());

        // address fragemnt 7
        pagerAdapter.addFragments(new AddressFragment());

        // legal fragment 8
        pagerAdapter.addFragments(new LegalFragment());

        //add edit address fragment 9
        pagerAdapter.addFragments(new AddEditAddressFragment());

        // legal information fragment 10
        pagerAdapter.addFragments(new LegalInfoFragment());

        // item detail fragment from oderlist 11
        pagerAdapter.addFragments(new OrderItemFragment());

        // account info fragment 12

        pagerAdapter.addFragments(new AccountInfoFragment());

        // product view as per category 13
        pagerAdapter.addFragments(new ProductFragment());

        viewPager.setAdapter(pagerAdapter);


    }

    public void setDataInterface(DataInterface Interface){
        this.dataInterface = Interface;
    }



    /**
     * Adds (items) {@link AHBottomNavigationItem} to {@link AHBottomNavigation}
     * Also assigns a distinct color to each Bottom Navigation item, used for the color ripple.
     */
    private void addBottomNavigationItems() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.mipmap.ic_star, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.mipmap.ic_nav_search, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.mipmap.ic_nav_heart, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_3, R.mipmap.ic_nav_heart, R.color.colorPrimary);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_3, R.mipmap.ic_nav_user, R.color.colorPrimary);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

    }

    private int fetchColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(color, getTheme());
        } else {
            return getResources().getColor(color);
        }
    }


    @Override
    public void onClickPosition(View view, String tag,  String data) {
        if(tag.equals(Constants.TAG_CAMERA)){
            viewPager.setCurrentItem(5,false);
        }else if (tag.equals(Constants.TAG_PRODUCT_DETAIL)){
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            intent.putExtra(Constants.PRODUCT,data);
            startActivity(intent);
        }else if(tag.equals(Constants.TAG_MY_ORDER)){
            viewPager.setCurrentItem(6, false);
        }else if (tag.equals(Constants.TAG_ADDRESS)){
            viewPager.setCurrentItem(7, false);
        }else if(tag.equals(Constants.TAG_LEGAL)){
            viewPager.setCurrentItem(8, false);
        }else if (tag.equals(Constants.TAG_EDIT_ADDA_ADDRESS)){
            data="passed some";
            viewPager.setCurrentItem(9, false);
            dataInterface.onReceiveData(data);
        }else if(tag.equals(Constants.TAG_LEGAL_INFO)){
            viewPager.setCurrentItem(10, false);
            dataInterface.onReceiveData("legal detail id ");
        }else if (tag.equals(Constants.TAG_ORDER_ITEM_DETAIL)){
            viewPager.setCurrentItem(11, false);
            dataInterface.onReceiveData("");
        }else if(tag.equals(Constants.TAG_ACCOUNT_INFO)){
            viewPager.setCurrentItem(12, false);
        }else if(tag.equals(Constants.TAG_PRODUCT_VIEW)){
            viewPager.setCurrentItem(13, false);
        }
    }



}
