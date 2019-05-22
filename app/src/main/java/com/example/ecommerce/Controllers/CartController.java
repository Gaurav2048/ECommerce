package com.example.ecommerce.Controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.Interface.Actions.CartAction;
import com.example.ecommerce.Models.Interface.Database.CartDatabase;
import com.example.ecommerce.Models.Interface.Database.RoomHelper;

import java.util.List;

public class CartController {

    CartDatabase cartDatabase;
    Context context;
    CartAction cartAction;

        public CartController(Context context,CartAction cartAction){
            this.context = context;
            this.cartAction = cartAction;
            cartDatabase = new RoomHelper(context).getCrtDatabase();
    }

    public void addToCart(final Cart cart){
        new AsyncTask<Cart, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Cart... carts) {
                Cart ToAddCart = carts[0];
               List<Cart> catList = cartDatabase.cartDao().get_Cart_List();
               for (int i =0;i<catList.size();i++){
                    if(catList.get(i).getProductId().equals(ToAddCart.getProductId())){
                         cartDatabase.cartDao().updateCartItem(ToAddCart);
                        return null;
                    }
               }
                cartDatabase.cartDao().insertItemToCart(ToAddCart);
                return null;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                cartAction.onCartItemAdded();
            }
        }.execute(cart);
    }

    public void RemoveFromCart(final Cart cart){
        new AsyncTask<Cart, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Cart... carts) {
                cartDatabase.cartDao().delete_Cart_item(cart);
                return null;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                cartAction.onRemoveCartItem();
            }
        }.execute(cart);
    }

    public void getCart(){
            new AsyncTask<Void, Void, List<Cart>>(){

                @Override
                protected List<Cart> doInBackground(Void... voids) {
                   return cartDatabase.cartDao().get_Cart_List();
                }

                @Override
                protected void onPostExecute(List<Cart> carts) {
                    cartAction.onResultCartList(carts);
                }
            }.execute();
    }

    int count =0;
    public int getCartSize(){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return cartDatabase.cartDao().get_Cart_Size();
            }

            @Override
            protected void onPostExecute(Integer integer) {
                count = integer;
                cartAction.onResultCount(integer);
                Log.e( "onPostExecute: ",integer+" " );
            }
        }.execute();

        return count;
    }


}
