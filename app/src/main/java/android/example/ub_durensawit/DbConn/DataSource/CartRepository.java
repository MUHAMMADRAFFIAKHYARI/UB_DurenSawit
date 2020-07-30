package android.example.ub_durensawit.DbConn.DataSource;

import android.app.Application;
import android.example.ub_durensawit.DbConn.local.CartDao;
import android.example.ub_durensawit.DbConn.local.CartDatabase;
import android.example.ub_durensawit.Model.Cart;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CartRepository {
    private CartDao mCartDao;
    private LiveData<List<Cart>> mAllItems;

    private static CartRepository instance;
    public CartRepository(Application application) {
        CartDatabase db = CartDatabase.getDatabase(application);
        mCartDao = db.cartDao();
        mAllItems = mCartDao.getCartItems();
    }


/**
    public LiveData<List<Cart>> GetCartItemById(int cartItemId) {
        return iCartDataSource.GetCartItemById(cartItemId);
    }
    public int countCartItems() {
    return iCartDataSource.countCartItems();
    }

 **/
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

    /**
     * Delete all words from the database (does not delete the table)
     */
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

    /**
     *  Delete a single word from the database.
     */
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
}
