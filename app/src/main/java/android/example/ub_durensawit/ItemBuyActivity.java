package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemBuyActivity extends AppCompatActivity {

    TextView quantityItem, prodName, prodCtg, prodPrice, coba;
    ImageView prodImage;
    int angka = 1;
    int position;
    int [] imageProduct = {R.drawable.product1,R.drawable.product2,R.drawable.product3,R.drawable.product4,R.drawable.product1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buy);

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
        String productName = i.getStringExtra("product name");
        String productCategory = i.getStringExtra("product category");
        String productPrice = i.getStringExtra("product price");

        int positionImage = productImagePosition;

//        prodImage.setImageResource(imageProduct[positionImage]);
//        coba.setText(imageProduct[positionImage]);
        prodName.setText(productName);
        prodCtg.setText(productCategory);
        prodPrice.setText(productPrice);

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