package android.example.ub_durensawit.DbConn.local;

import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.Model.Operation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CartDao {
        @Query("SELECT * FROM Cart")
        public LiveData<List<Cart>> getCartItems();

        @Query("SELECT * FROM Cart Where Produk_Id =:produk_id")
        public LiveData<Cart> GetCartItemById(int produk_id);

        @Query("SELECT COUNT(*) FROM Cart")
        public int countCartItems();

        @Query("DELETE FROM Cart")
        public void emptyCart();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public void InsertToCart(Cart carts);

        @Update
        public void UpdateCart(Cart carts);

        @Delete
        public void deleteCartItem(Cart cart);

        @Query("SELECT SUM(harga) as total_harga FROM Cart")
        public Operation getTotal_Harga();



}
