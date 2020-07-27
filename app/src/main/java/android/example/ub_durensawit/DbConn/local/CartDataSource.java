package android.example.ub_durensawit.DbConn.local;

import android.example.ub_durensawit.DbConn.DataSource.ICartDataSource;
import android.example.ub_durensawit.Model.Cart;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CartDataSource implements ICartDataSource {
    private CartDao cartDao;
    private static CartDataSource instance;

    public CartDataSource(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public static CartDataSource getInstance(CartDao cartDao){
        if(instance==null){
            instance = new CartDataSource(cartDao);
        }
        return instance;
    }

    @Override
    public Flowable<List<Cart>> getCartItems() {
        return cartDao.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> GetCartItemById(int cartItemId) {
        return cartDao.GetCartItemById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return cartDao.countCartItems();
    }

    @Override
    public void emptyCart() {
    cartDao.emptyCart();
    }

    @Override
    public void InsertToCart(Cart... carts) {
    cartDao.InsertToCart(carts);
    }

    @Override
    public void UpdateCart(Cart... carts) {
    cartDao.UpdateCart(carts);
    }

    @Override
    public void deleteCartItem(Cart cart) {
    cartDao.deleteCartItem(cart);
    }
}
