package android.example.ub_durensawit.Model.ViewModel;

import android.app.Application;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.Model.Cart;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
private CartRepository mRepository;
private LiveData<List<Cart>> mAllCartItems;

public CartViewModel(Application application) {
        super(application);
        mRepository = new CartRepository(application);
        mAllCartItems = mRepository.getCartItems();
    }

    LiveData<List<Cart>> getAllItems() {
        return mAllCartItems;
    }

    public void insertToCart(Cart cart) {
        mRepository.InsertToCart(cart);
    }

    public void deleteAll() {
        mRepository.emptyCart();
    }

    public void deleteCart(Cart cart) {
        mRepository.deleteCartItem(cart);
    }

}
