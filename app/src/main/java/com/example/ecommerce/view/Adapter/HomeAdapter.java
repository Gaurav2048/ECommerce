package com.example.ecommerce.view.Adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Controllers.ProductController;
import com.example.ecommerce.Models.Interface.UI_Helpers.ClickListner;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.Constants;
import com.example.ecommerce.Models.Utilities.Utility;
import com.example.ecommerce.view.ItemDecorators.SpacesItemDecoration;
import com.example.ecommerce.R;
import com.example.ecommerce.view.Utility.GeneralUtility;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ClickListner clickListner;
    ProductController controller;
    public HomeAdapter(@NonNull Context context,ClickListner clickListner) {
        this.context = context;
        this.clickListner=clickListner;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    if(i==0){
        return new cameraHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_camera,viewGroup,false));
    }else if(i==1) {
        return new categoryHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_category,viewGroup,false));
    }else{
        return new productHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_camera,viewGroup,false));
    }
     }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    if(viewHolder.getItemViewType()==0){
        cameraHolder viewCamera = (cameraHolder)viewHolder;
        viewCamera.recyclerCamera.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(viewCamera.recyclerCamera);
        viewCamera.recyclerCamera.addItemDecoration(new SpacesItemDecoration(GeneralUtility.DpToPx(20,context)));
        viewCamera.recyclerCamera.setAdapter(new CameraAdapter(context,clickListner));
    }else if (i==1) {
        categoryHolder viewCategory = (categoryHolder) viewHolder;
        viewCategory.categoryRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(viewCategory.categoryRecycler);
        viewCategory.categoryRecycler.addItemDecoration(new SpacesItemDecoration(GeneralUtility.DpToPx(20,context)));
        viewCategory.categoryRecycler.setAdapter(new CategoryAdapter(context, clickListner));
    }else {
        productHolder productHolder = (productHolder) viewHolder;
        productHolder.productRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(productHolder.productRecycler);
        productHolder.productRecycler.addItemDecoration(new SpacesItemDecoration(GeneralUtility.DpToPx(20,context)));
        productHolder.productRecycler.setAdapter(new ProductAdapter(context,clickListner, i-2));

    }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class cameraHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RecyclerView recyclerCamera;
        TextView seeAll;

        public cameraHolder(@NonNull View itemView) {
            super(itemView);
            recyclerCamera= itemView.findViewById(R.id.recyclerCamera);
            seeAll=itemView.findViewById(R.id.seeAll);
            seeAll.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(v==seeAll){
                clickListner.onClickPosition(v,Constants.TAG_PRODUCT_VIEW,"");
            }
        }
    }

    public class productHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView view_name,seeAll;
        RecyclerView productRecycler;
        public productHolder(@NonNull View itemView) {

            super(itemView);
            view_name=itemView.findViewById(R.id.view_name);
            seeAll=itemView.findViewById(R.id.seeAll);
            productRecycler= itemView.findViewById(R.id.recyclerCamera);
            itemView.setOnClickListener(this);
            seeAll.setOnClickListener(this);
            view_name.setText("POPULAR PRODUCTS");
            seeAll.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
           //  clickListner.onClickPosition(v, Constants.TAG_PARTICULAR_PRODUCT, "");
        }
    }

    public class categoryHolder extends RecyclerView.ViewHolder{
        RecyclerView categoryRecycler;
        public categoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryRecycler = itemView.findViewById(R.id.recyclercategory);
        }
    }



}
