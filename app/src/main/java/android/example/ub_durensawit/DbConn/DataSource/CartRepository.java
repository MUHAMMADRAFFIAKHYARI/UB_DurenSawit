package android.example.ub_durensawit.DbConn.DataSource;

import android.app.Application;
import android.content.Context;
import android.example.ub_durensawit.DbConn.local.CartDao;
import android.example.ub_durensawit.DbConn.local.CartDatabase;
import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.Model.Operation;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CartRepository {
    private CartDao mCartDao;
    private LiveData<List<Cart>> mAllItems;
    private CartDatabase cartDatabase;
    private static CartRepository instance;
    public CartRepository(Context context) {
        cartDatabase = Room
                .databaseBuilder(context, CartDatabase.class, "UB_DurenSawit")
                .fallbackToDestructiveMigration()
                .build();

    }




    public void insertCart(int produk_id,String nama, int jumlah,int harga) {

        Cart cart = new Cart(produk_id,nama,jumlah,harga);

        insertCart(cart);
    }

    public void insertCart(final Cart cart) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDao().InsertToCart(cart);
                return null;
            }
        }.execute();
    }

    public void updateCart(final Cart cart) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDao().UpdateCart(cart);
                return null;
            }
        }.execute();
    }

    public void deleteCart(final int id) {
        final LiveData<Cart> cart = getCart(id);
        if(cart != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    cartDatabase.cartDao().deleteCartItem(cart.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteCart(final Cart cart) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDao().deleteCartItem(cart);
                return null;
            }
        }.execute();
    }

    public void emptyCart(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDao().emptyCart();
                return null;
            }
        }.execute();

    }
    public int countItems(){

        return cartDatabase.cartDao().countCartItems();
    }

    public LiveData<Cart> getCart(int id) {
        return cartDatabase.cartDao(). GetCartItemById(id);
    }

    public Operation getTotalHarga() {
        return cartDatabase.cartDao().getTotal_Harga();
    }

    public LiveData<List<Cart>> getCarts() {
        return cartDatabase.cartDao().getCartItems();
    }
















/**
    public LiveData<List<Cart>> GetCartItemById(int cartItemId) {
        return iCartDataSource.GetCartItemById(cartItemId);
    }
    public int countCartItems() {
    return iCartDataSource.countCartItems();
    }

 **/
/**
    public LiveData<List<Cart>> getCartItems() {
    return mAllItems;
}



    public void emptyCart() {
        new deleteAllItemsAsyncTask(mCartDao).execute();
    }

    public void  InsertToCart(Cart cart) {
        new insertAsyncTask(mCartDao).execute(cart);
    }

    public void deleteCartItem(Cart cart) {
        new deleteCartAsyncTask(mCartDao).execute(cart);
    }


    private static class insertAsyncTask extends AsyncTask<Cart, Void, Void> {

        private CartDao mAsyncTaskDao;

        insertAsyncTask(CartDao dao) {
            mAsyncTaskDao = dao;
        }

        protected Void doInBackground(final Cart... params) {
            mAsyncTaskDao.InsertToCart(params[0]);
            return null;
        }
    }


    private static class deleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private CartDao mAsyncTaskDao;

        deleteAllItemsAsyncTask(CartDao dao) {
            mAsyncTaskDao = dao;
        }
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.emptyCart();
            return null;
        }
    }


    private static class deleteCartAsyncTask extends AsyncTask<Cart, Void, Void> {
        private CartDao mAsyncTaskDao;

        deleteCartAsyncTask(CartDao dao) {
            mAsyncTaskDao = dao;
        }

        protected Void doInBackground(final Cart... params) {
            mAsyncTaskDao.deleteCartItem(params[0]);
            return null;
        }
    }
    **/
}
