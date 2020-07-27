package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.DbConn.local.CartDataSource;
import android.example.ub_durensawit.DbConn.local.CartDatabase;
import android.example.ub_durensawit.Model.Product;
import android.example.ub_durensawit.Model.User;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemBuyActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private ProgressDialog progress;
    TextView quantityItem, prodName, prodCtg, prodPrice, coba, goBuy;
    String productName, productCategory;
    SharedPreferences sharedPreferences;

    public static CartDatabase cartDatabase;
    public static CartRepository cartRepository;

    int productPrice, productQuantity,productId;
    ImageView prodImage;
    int angka = 1;
    int position;
    int [] imageProduct = {R.drawable.product1,R.drawable.product2,R.drawable.product3,R.drawable.product4,R.drawable.product1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buy);

        sharedPreferences = getSharedPreferences("CartItem",MODE_PRIVATE);
        goBuy = findViewById(R.id.goBuy);
        goBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intoTheCart();
                startActivity(new Intent(ItemBuyActivity.this, ListBuyActivity.class));
            }
        });

        ImageView backArr = findViewById(R.id.backArr);
        quantityItem = findViewById(R.id.qtyItem);
        prodImage = findViewById(R.id.imageView4);
        prodName = findViewById(R.id.textView4);
        prodCtg = findViewById(R.id.textView14);
        prodPrice = findViewById(R.id.textView15);
        coba = findViewById(R.id.textView13);

        backArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        Intent i = getIntent();

        int productImagePosition  = i.getIntExtra("position image", 0);
        productName = i.getStringExtra("product name");
        productCategory = i.getStringExtra("product category");
        String productPrice = i.getStringExtra("product price");

        int positionImage = productImagePosition;

//        prodImage.setImageResource(imageProduct[positionImage]);
//        coba.setText(imageProduct[positionImage]);
        prodName.setText(productName);
        prodCtg.setText(productCategory);
        prodPrice.setText(productPrice);

    }

    private void initDB(){
        cartDatabase = CartDatabase.getInstance(this);
        cartRepository = CartRepository.getInstance(CartDataSource.getInstance(cartDatabase.cartDao()));

    }


    private void getProduct(int id){
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

    private void intoTheCart(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama", productName);
        editor.putString("kategori",productCategory);
        editor.putString("jumlah",quantityItem.getText().toString());
        editor.putString("harga", String.valueOf(productPrice));
        editor.commit();

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