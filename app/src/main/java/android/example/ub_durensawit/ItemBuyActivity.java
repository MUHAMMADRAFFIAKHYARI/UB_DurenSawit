package android.example.ub_durensawit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.DbConn.local.CartDatabase;
import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.Model.Product;
import android.example.ub_durensawit.Model.User;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemBuyActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private ProgressDialog progress;
    TextView quantityItem, prodName, prodCtg, prodPrice, coba, goBuy;
    String productName, productCategory, productPrice;
    int  productQuantity,productId;
    ImageView prodImage;
    int angka = 1;
    Context context;
    int position;
    int [] imageProduct = {R.drawable.product1,R.drawable.product2,R.drawable.product3,R.drawable.product4,R.drawable.product1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buy);

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        goBuy = findViewById(R.id.goBuy);
        ImageView backArr = findViewById(R.id.backArr);
        quantityItem = findViewById(R.id.qtyItem);
        prodImage = findViewById(R.id.imageView4);
        prodName = findViewById(R.id.textView4);
        prodCtg = findViewById(R.id.textView14);
        prodPrice = findViewById(R.id.textView15);
        coba = findViewById(R.id.textView13);

        goBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intoTheCart();
                startActivity(new Intent(ItemBuyActivity.this, CartActivity.class));
            }
        });

        backArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        Intent i = getIntent();

        int idProduk  = i.getIntExtra("idProduk", 0);
        productName = i.getStringExtra("namaProduk");
        productPrice = i.getStringExtra("hargaProduk");

       /**
        productCategory = i.getStringExtra("product category");
        String productPrice = i.getStringExtra("product price");


        int positionImage = productImagePosition;
        **/

//        prodImage.setImageResource(imageProduct[positionImage]);
//        coba.setText(imageProduct[positionImage]);
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide.with(ItemBuyActivity.this)
                .load("https://budiganteng.000webhostapp.com/product/"+Integer.toString(idProduk)+".png")
                .placeholder(circularProgressDrawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //circularProgressDrawable.stop();
                        return false;
                    }
                })
                .into(prodImage);


        prodName.setText(productName);
        prodCtg.setText("Rahasia");
        prodPrice.setText(productPrice);

    }


/**
    private void getProduct(int produk_id){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Mohon tunggu sebentar");
        progress.show();
        Call<Product> call = apiInterface.getProduct(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                progress.dismiss();
                Product responseProduct = response.body();
                if (response.isSuccessful() && responseProduct != null) {
                   // Toast.makeText(VerificationActivity.this,"Pendaftaran berhasil",Toast.LENGTH_LONG).show();
                    prodName.setText(responseProduct.getNama_produk());
                    prodCtg.setText(responseProduct.getKategori_id());
                    prodPrice.setText(responseProduct.getHarga());

                } else {
                    Toast.makeText(ItemBuyActivity.this,"Gagal Mengambil data",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(ItemBuyActivity.this,
                        "Jaringan Bermasalah " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

 **/

    private void intoTheCart(){
        CartRepository cartRepository = new CartRepository(getApplicationContext());
       //Sementara
        String nama= prodName.getText().toString();
        int harga = Integer.parseInt(prodPrice.getText().toString());
        int jumlah = Integer.parseInt(quantityItem.getText().toString());
        cartRepository.insertCart(productId,nama,jumlah,harga);
    }



    public void increaseItem(View view) {
        if(angka < 10 && quantityItem != null) {
            angka++;
            quantityItem.setText(Integer.toString(angka));
        }else if( angka == 10){
            Toast toast = Toast.makeText(this, "Tidak Boleh Lebih Dari Stock", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void decreaseItem(View view) {
        if(angka > 1 && quantityItem != null){
            angka--;
            quantityItem.setText(Integer.toString(angka));

        }else if( angka == 1){
            Toast toast = Toast.makeText(this, "Tidak Boleh Kurang Dari Nol", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




}