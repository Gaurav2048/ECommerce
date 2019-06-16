package com.example.ecommerce.view.Fragments.SubFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Adapter.ItemAdapter;
import com.example.ecommerce.view.Adapter.ProductAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements ProductActions {

    TextView yourProducts;
    RecyclerView productRecyclerView;
    ProductController productController;
    ItemAdapter adapter;
    LinearLayoutManager layoutmanager;
    ShimmerFrameLayout shimmerLayout;
    SwipeRefreshLayout swipeRefresh;
    RelativeLayout dataLayout;
    int offset;
    boolean isFirstLoad = true;
    boolean isLoading = false;
    List<Product> productList;
    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product, container, false);
        yourProducts=view.findViewById(R.id.yourProducts);
        shimmerLayout = view.findViewById(R.id.shimmerLayout);
        dataLayout = view.findViewById(R.id.dataLayout);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        productList = new ArrayList<>();
        productController = new ProductController(this);
        productRecyclerView=view.findViewById(R.id.productRecyclerView);
        adapter = new ItemAdapter(getContext(), productList);
        layoutmanager = new LinearLayoutManager(getContext());
        productRecyclerView.setLayoutManager(layoutmanager);
        productRecyclerView.setAdapter(adapter);
        swipeRefresh.setRefreshing(true);
        startLoadingData();

        productRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutmanager.getItemCount() - 4;
                int lastVisibleItem = layoutmanager.findLastVisibleItemPosition();

                if(lastVisibleItem>totalItemCount){
                    if(isLoading==false && dy>0 )
                    {
                        offset = productList.size();
                        isLoading = true;
                        productController.get_Products_List_by_category("2", "0", String.valueOf(offset) );
                    }
                }

            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startLoadingData();
            }
        });

        return view;
    }

    private void startLoadingData(){
        isFirstLoad = true;
        isLoading = false;
        productList.clear();
        adapter.notifyDataSetChanged();
        dataLayout.setVisibility(View.GONE);
        shimmerLayout.setVisibility(View.VISIBLE);
        shimmerLayout.startShimmer();
        productController.get_Products_List_by_category("2","0", "0");
    }



    @Override
    public void onFetchStart() {
        if(isFirstLoad){
            shimmerLayout.startShimmer();
        }
    }

    @Override
    public void onFetchProgress(Product product) {

    }

    @Override
    public void onFetchProgress(List<Product> products) {

        if(isFirstLoad==true){
            shimmerLayout.stopShimmer();
            shimmerLayout.setVisibility(View.GONE);
            isFirstLoad = false;
            dataLayout.setVisibility(View.VISIBLE);
            if(swipeRefresh.isRefreshing()){
                swipeRefresh.setRefreshing(false);
            }
        }

        for(int i=0;i<products.size();i++){
            productList.add(products.get(i));
            adapter.notifyItemInserted(productList.size()+1);
        }
        isLoading = false;


    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed(Throwable t) {

    }
}
