package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemBuyActivity extends AppCompatActivity {

    TextView quantityItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buy);

        ImageView backArr = findViewById(R.id.backArr);
        quantityItem = findViewById(R.id.qtyItem);

        backArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private int qtyItem;

    public void increaseItem(View view) {
        qtyItem++;
        quantityItem.setText(String.valueOf(qtyItem));
    }

    public void decreaseItem(View view) {
        if (quantityItem.getText()=="0"){
            quantityItem.setText("0");
        } else{
            qtyItem--;
            quantityItem.setText(String.valueOf(qtyItem));
        }
    }
}