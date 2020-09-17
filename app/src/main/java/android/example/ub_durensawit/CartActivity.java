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
import android.example.ub_durensawit.Adapter.MainRecyclerAdapter;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.Model.Operation;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
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
    private ImageView cartEmptyImage;
    private TextView emptyTitle,emptyDescription,totalHarga;
    CartRepository cartRepository;
    private CartListAdapter cartListAdapter;
    private RecyclerView itemRecycler;
    ConstraintLayout  nextBuy;
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
        cartEmptyImage = findViewById(R.id.imageCartEmpty);
        emptyTitle = findViewById(R.id.empty_title);
        emptyDescription = findViewById(R.id.empty_description);
        itemRecycler =findViewById(R.id.item_recycler);

        totalHarga = findViewById(R.id.totalHarga);



        nextBuy = findViewById(R.id.buyNext);
        toWarn = findViewById(R.id.toWarn);

        // Set up the RecyclerView.
        /**
        final CartListAdapter adapter = new CartListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         **/
        allItems = new ArrayList<>();
        cartRepository = new CartRepository(this);
        fetchAllItems(0);




        //hilangin actionBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

   private void fetchAllItems(int indicator){
       /**
        * Jenis Indikator :
        * 1 : buat ngirim data ke database
        * 0 : buat nampilin di recycler view
        */

       //take the list of item
       cartRepository.getCarts().observe(this, new Observer<List<Cart>>() {
           @Override
           public void onChanged(@Nullable List<Cart> carts) {

               if (carts.size() > 0){

                   itemRecycler.setVisibility(View.VISIBLE);

                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
                   itemRecycler.setLayoutManager(layoutManager);
                   cartListAdapter = new CartListAdapter(CartActivity.this, carts);
                   itemRecycler.setAdapter(cartListAdapter);
                   itemRecycler.setHasFixedSize(true);


                   toWarn.setEnabled(true);
                   cartEmptyImage.setVisibility(View.GONE);
                   emptyTitle.setVisibility(View.GONE);
                   emptyDescription.setVisibility(View.GONE);
                   addCart.setVisibility(View.GONE);
               } else {
                   toWarn.setEnabled(false);
                   cartEmptyImage.setVisibility(View.VISIBLE);
                   emptyTitle.setVisibility(View.VISIBLE);
                   emptyDescription.setVisibility(View.VISIBLE);
                   addCart.setVisibility(View.VISIBLE);
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       toWarn.setBackground(getDrawable(R.drawable.btn_rounded_secondary));
                   }
               }
           }
       });

    //take the total price
       cartRepository.getTotalHarga().observe(this, new Observer<Double>() {
           @Override
           public void onChanged(Double result) {
               totalHarga.setText(result.toString());
           }

       });
   }

    private Date getcurrentDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return cal.getTime();
    }


    private void setData(List<Cart> carts){
        this.allItems = carts;
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

    private void EmptyCart(){
        cartRepository.emptyCart();
    }

}