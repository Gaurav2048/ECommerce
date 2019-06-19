package com.example.ecommerce.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.DataInterface;
import com.example.ecommerce.Models.Interface.UI_Helpers.SearchInterface;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.view.Adapter.BottomBarAdapter;
import com.example.ecommerce.view.Fragments.AccountFragment;
import com.example.ecommerce.view.Fragments.CartFragment;
import com.example.ecommerce.view.Fragments.DetailFragment;
import com.example.ecommerce.view.Fragments.ForYouFragment;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Fragments.Search.SearchResultFragment;
import com.example.ecommerce.view.Fragments.Search.SuggestedFragment;
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
import com.example.ecommerce.view.Fragments.WishListFragment;
import com.example.ecommerce.view.widget.NoSwipePager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListner, PaymentResultListener {




    AHBottomNavigation bottomNavigation;
    NoSwipePager viewPager;
    ProductController controller;
    DataInterface dataInterface;
    AppPreferences appPreferences;
    ArrayList<Integer> trackList = new ArrayList<>();
    private BottomBarAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        trackList.add(0);
        setupViewPager();
        appPreferences = new AppPreferences(getApplicationContext());


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        setupBottomNavBehaviors();
        setupBottomNavStyle();


        addBottomNavigationItems();


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                  addTobackStack(position);
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
          if (trackList.size() == 0 ) {
              super.onBackPressed();
          } else {
              Toast.makeText(getApplicationContext(),trackList.size()+" ", Toast.LENGTH_SHORT).show();
              viewPager.setCurrentItem(trackList.get(trackList.size()-1),false);
              trackList.remove(trackList.size()-1);
              Log.e( "onBackPressed: ",trackList.size()+" " );
          }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
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
        pagerAdapter.addFragments(new WishListFragment());
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
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.mipmap.ic_nav_cart_1, R.color.colorPrimary);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.mipmap.ic_nav_user, R.color.colorPrimary);

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

    String phrase = null;
    category Caterogy = null;

    public category getCaterogy() {
        return Caterogy;
    }

    public String getPhrase() {
        return phrase;
    }

    @Override
    public void onClickPosition(View view, String tag,  String data) {
       int position = 0;
        if(tag.equals(Constants.TAG_CAMERA)){
            trackList.add(5);
            position = 5;
            Caterogy = Utility.getCategoryObject(data);
            viewPager.setCurrentItem(5,false);
        }else if (tag.equals(Constants.TAG_PRODUCT_DETAIL)){
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            intent.putExtra(Constants.PRODUCT,data);
            startActivity(intent);
        }else if(tag.equals(Constants.TAG_MY_ORDER)){
            position = 5;
            trackList.add(6);
            viewPager.setCurrentItem(6, false);
        }else if (tag.equals(Constants.TAG_ADDRESS)){
            position = 7;
            trackList.add(7);
            viewPager.setCurrentItem(7, false);
        }else if(tag.equals(Constants.TAG_LEGAL)){
            position = 8;
            trackList.add(8);
            viewPager.setCurrentItem(8, false);
        }else if (tag.equals(Constants.TAG_EDIT_ADDA_ADDRESS)){
            position = 9;
            data="passed some";
            trackList.add(9);
            viewPager.setCurrentItem(9, false);
            dataInterface.onReceiveData(data);
        }else if(tag.equals(Constants.TAG_LEGAL_INFO)){
            position = 10;
            trackList.add(10);
            viewPager.setCurrentItem(10, false);
            dataInterface.onReceiveData("legal detail id ");
        }else if (tag.equals(Constants.TAG_ORDER_ITEM_DETAIL)){
            position = 11;
            trackList.add(11);
            viewPager.setCurrentItem(11, false);
            dataInterface.onReceiveData("");
        }else if(tag.equals(Constants.TAG_ACCOUNT_INFO)){
            position = 12;
            trackList.add(12);
            viewPager.setCurrentItem(12, false);
        }else if(tag.equals(Constants.TAG_PRODUCT_VIEW)){
            position = 13;
            trackList.add(13);
            viewPager.setCurrentItem(13, false);
        }else if(tag.equals(Constants.TAG_ADD_TO_WISHLIST)){
            addToWishList(data);
        }else if(tag.equals(Constants.TAG_ACTION_SEARCH)){
           this.phrase = data;
        }else if(tag.equals(Constants.TAG_PAYMENT_ACTION)){
            startPayment(data);
        }else if(tag.equals(Constants.TAG_SEARCH_TWO)){
            viewPager.setCurrentItem(1);
            bottomNavigation.getItem(bottomNavigation.getCurrentItem()).setColorRes(R.color.gray);
            bottomNavigation.getItem(1).setColorRes(R.color.mellon_rd);
        }
        addTobackStack(position);
    }

    private void addTobackStack(int position) {

        if(trackList.size()==0){
            trackList.add(position);
        }else {
            int toClearAbove = -3;
            for(int i=0; i<trackList.size(); i++){
                if(trackList.get(i) == position){
                    toClearAbove = i;
                    break;
                }
            }

            if(toClearAbove < 0)
            {
                trackList.add(position);
                Log.e( "addTobackStack: ",trackList.size()+" " +toClearAbove );

            }else {
               ArrayList<Integer> list = new ArrayList<>();
                list.addAll(trackList.subList(0, toClearAbove));
                trackList.clear();
                trackList.addAll(list);
                Log.e( "addTobackStack: ",trackList.size()+" "+toClearAbove );
            }

        }

    }

    private void addToWishList(String data) {

        Product product = Utility.getProductObject(data);
        List<Product> wishList = Utility.getWishList(appPreferences.getWishList());
        if(wishList!=null)
        {
            int  position = -300;

            for(int i =0;i<wishList.size();i++){
                if(product.getId().equals(wishList.get(i).getId())) {
                    position = i;
                    break;
                }
            }
            if(position<0){
                wishList.add(product);
            }else {
                wishList.remove(position);
            }

//            if(wishList.contains(product)){
//                Log.e( "addToWishList: ","product removed" );
//                wishList.remove(product);
//            }else {
//                Log.e( "addToWishList: ","product added" );
//                wishList.add(product);
//            }
        }else {
            Log.e( "addToWishList: ","product new added" );
            wishList = new ArrayList<>();
            wishList.add(product);
        }
        appPreferences.setWishList(wishList);

        Log.e( "addToWishList: ",wishList.size()+" " );

    }



    public void startPayment(String amount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.ic_question_answers);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Vantage Point");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Order #123456");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", amount);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(" Razorpay Checkout", e.getMessage());
        }
    }



    @Override
    public void onPaymentSuccess(String s) {
        Log.e( "onPaymentSuccess: ",s.toString() );
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.e( "onPaymentError: ",s+" " );

    }
}
