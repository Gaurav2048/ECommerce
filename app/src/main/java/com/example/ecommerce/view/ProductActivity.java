package com.example.ecommerce.view;

import android.graphics.Paint;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.CartController;
import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.CartAction;
import com.example.ecommerce.Models.Interface.Database.CartDatabase;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.view.Adapter.SliderAdapter;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Utility.GeneralUtility;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements CartAction {

    ViewPager product_image_viewPager;
    SliderAdapter adapter;
    ImageView cart_plus,cart_minus;
    TextView cart_number,product_name_bottom;
    private TextView[] dots;
    CoordinatorLayout productCoordinator;
    LinearLayout mDotLayout;
    Button addToCartBottom;
    Product product;
    BottomSheetLayout bottomSheet;
    View bottomView;
    LinearLayout add_to_wish_list,add_to_cart;
    private CartController cartController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        cartController = new CartController(getApplicationContext(), this);
        product = GeneralUtility.getProductFromString(getIntent().getStringExtra(Constants.PRODUCT));

        createBottomView();
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        productCoordinator = findViewById(R.id.productCoordinator); 
        add_to_wish_list=findViewById(R.id.add_to_wish_list);
        product_image_viewPager=(ViewPager) findViewById(R.id.product_image_viewPager);
        adapter= new SliderAdapter(getApplicationContext());
        add_to_cart=findViewById(R.id.add_to_cart);
        mDotLayout=(LinearLayout) findViewById(R.id.mDotLayout);
        addDotIndicators(0);

        product_image_viewPager.setAdapter(adapter);

        product_image_viewPager.addOnPageChangeListener(viewListner);

        add_to_wish_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            cartController.getCartSize();
            }
        });

        cart_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                cart_number.setText(String.valueOf(count));
            }
        });

        cart_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(count>0) {
                   count--;
                   cart_number.setText(String.valueOf(count));
               }
               }
        });

        addToCartBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setProductId(product.getId());
                cart.setImage(product.getmImage1());
                cart.setProductName(product.getmItemName());
                cart.setDiscount(product.getmDiscount());
                cart.setPrice(product.getmPrice());
                cart.setQuantity(count);
                cartController.addToCart(cart);
                bottomSheet.dismissSheet();
            }
        });
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.showWithSheetView(bottomView);

            }
        });

    }

    int count=0;
    private void createBottomView() {
        bottomView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.my_sheet_layout, bottomSheet, false);
        cart_plus = bottomView.findViewById(R.id.cart_plus);
        cart_number=bottomView.findViewById(R.id.cart_number);
        addToCartBottom = bottomView.findViewById(R.id.addToCartBottom);
        product_name_bottom=bottomView.findViewById(R.id.product_name_bottom);
        cart_minus = bottomView.findViewById(R.id.cart_minus);
        ImageView imageView=bottomView.findViewById(R.id.item_image);
        TextView textView = bottomView.findViewById(R.id.originalPriceBottom);
        Picasso.get().load("https://images.pexels.com/photos/18105/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500").into(imageView);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        // populate
        cart_number.setText(String.valueOf(count));
        product_name_bottom.setText(product.getmItemName());

    }



    private void addDotIndicators(int position){
        dots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i =0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransperent));
            mDotLayout.addView(dots[i]);

        }

        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
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

ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        addDotIndicators(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
};

    @Override
    public void onResultCount(int Count) {

    }

    @Override
    public void onResultCartList(List<Cart> cartList) {

    }

    @Override
    public void onRemoveCartItem() {

    }

    @Override
    public void onCartItemAdded() {
        Snackbar snackbar = Snackbar
                .make(productCoordinator, "Confirm detete?", Snackbar.LENGTH_LONG)
                .setAction("OKAY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      

                       }
                });

        snackbar.show();
    }
}
