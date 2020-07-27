package android.example.ub_durensawit.DbConn.DataSource;

import android.example.ub_durensawit.Model.Cart;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface ICartDataSource {
    Flowable<List<Cart>> getCartItems();
    Flowable<List<Cart>> GetCartItemById(int cartItemId);
    int countCartItems();
    void emptyCart();
    void InsertToCart(Cart...carts);
    void UpdateCart(Cart...carts);
    void deleteCartItem(Cart cart);
}
