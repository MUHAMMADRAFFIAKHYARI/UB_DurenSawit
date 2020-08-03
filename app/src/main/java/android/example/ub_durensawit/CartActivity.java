package android.example.ub_durensawit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.example.ub_durensawit.Adapter.CartListAdapter;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.Model.Cart;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.google.gson.Gson;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CartActivity extends AppCompatActivity {

    Button addCart, toWarn;
    CartRepository cartRepository;
    ConstraintLayout emptyCart, nextBuy;
    private List<Cart> allItems;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        addCart =findViewById(R.id.addCart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        emptyCart = findViewById(R.id.emptyCart);
        nextBuy = findViewById(R.id.buyNext);
        toWarn = findViewById(R.id.toWarn);

        // Set up the RecyclerView.
        /**
        final CartListAdapter adapter = new CartListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         **/
        cartRepository = new CartRepository(getApplicationContext());

        //tombol ga bisa di klik kalau cart empty
        /*if (emptyCart.getVisibility() == View.VISIBLE){
            toWarn.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toWarn.setBackground(getDrawable(R.drawable.btn_rounded_secondary));
            }
        } else {
            toWarn.setEnabled(true);
        }*/


        //hilangin actionBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private List<Cart> getAllItems(){


        cartRepository.getCarts().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable List<Cart> carts) {
                // Update the cached copy of the words in the adapter.

                //adapter.setCarts(carts);
                setData(carts);
            }
        });
        return allItems;


    }

    private void setData(List<Cart> carts){
        allItems = carts;
    }

    private void proccessTransaction(){


    }

    public void toWarn(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.buy_acception);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button denyBuy = dialog.findViewById(R.id.denyBuy);
        denyBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button acceptBuy = dialog.findViewById(R.id.acceptBuy);
        acceptBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ThanksActivity.class));
                finish();
            }
        });

    }

}