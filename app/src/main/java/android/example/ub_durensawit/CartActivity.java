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
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Observable;
import android.example.ub_durensawit.Adapter.CartListAdapter;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.Model.Order;
import android.example.ub_durensawit.Model.User;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    Button addCart, toWarn;
    CartRepository cartRepository;
    ConstraintLayout emptyCart, nextBuy;
    private List<Cart> allItems;
    ProgressDialog progress;

    ApiInterface apiInterface;

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

    private Date getcurrentDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return cal.getTime();
    }


    private void setData(List<Cart> carts){
        allItems = carts;
    }
/**
    private void proccessTransaction(){
        JSONArray json = new JSONArray();
        for (int i = 0; i < allItems.size(); i++) {
            JSONObject row = new JSONObject();
            try {
                row.put("Nama", allItems.get(i).getNama());
                row.put("Nama", allItems.get(i).getNama());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(row);
        }

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Sedang memproses transaksi");
        progress.show();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = date.format(getCurrentDate());
        Call<User> call = apiInterface.createOrder(currentDate,user_id,"Proses");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progress.dismiss();
                User responseUser = response.body();
                if (response.isSuccessful() && responseUser != null) {
                    Toast.makeText(CartActivity.this,"Pendaftaran berhasil",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CartActivity.this, LandingActivity.class));
                    finish();
                } else {
                    Toast.makeText(VerificationActivity.this,"Data gagal ditambahkan",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(VerificationActivity.this,
                        "Jaringan Bermasalah " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });

    }

 **/
    private int getTotalHarga(){
        Cart cart = cartRepository.getTotalHarga();
        return cart.getTotal_harga();

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