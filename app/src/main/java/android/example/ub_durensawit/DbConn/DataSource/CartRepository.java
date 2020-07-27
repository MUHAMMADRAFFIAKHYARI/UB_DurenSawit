package android.example.ub_durensawit.DbConn.DataSource;

import android.example.ub_durensawit.Model.Cart;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CartRepository implements ICartDataSource {
    private ICartDataSource iCartDataSource;
    private static CartRepository instance;
    public static CartRepository getInstance(ICartDataSource iCartDataSource){
        if(instance == null){
            instance = new CartRepository(iCartDataSource);

        }
        return instance;

    }

    public CartRepository(ICartDataSource iCartDataSource) {
        this.iCartDataSource = iCartDataSource;
    }


    @Override
    public Flowable<List<Cart>> getCartItems() {
        return iCartDataSource.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> GetCartItemById(int cartItemId) {
        return iCartDataSource.GetCartItemById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return iCartDataSource.countCartItems();
    }

    @Override
    public void emptyCart() {
    iCartDataSource.emptyCart();
    }

    @Override
    public void InsertToCart(Cart... carts) {

    }

    @Override
    public void UpdateCart(Cart... carts) {
    iCartDataSource.UpdateCart(carts);
    }

    @Override
    public void deleteCartItem(Cart cart) {
    iCartDataSource.deleteCartItem(cart);
    }
}
