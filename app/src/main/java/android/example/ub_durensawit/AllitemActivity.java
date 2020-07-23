package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllitemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterHome recyclerViewAdapterHome;

    int [] imageProduct = {R.drawable.product1,R.drawable.product2,R.drawable.product3,R.drawable.product4,R.drawable.product1};
    String [] nameProduct = {"Mie Goren", "T-Shirt",  "Baygon", "Indomie", "Mie Goreng"};
    String [] categoryProduct = {"makanan", "pakaian",  "kebutuhan", "makanan", "makanan"};
    String [] priceProduct = {"33.23", "14.303",  "100.00", "34.00", "2.000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allitem);

        recyclerView = findViewById(R.id.rcv_allItem);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapterHome = new RecyclerViewAdapterHome(imageProduct, nameProduct, categoryProduct, priceProduct);

        recyclerView.setAdapter(recyclerViewAdapterHome);

        recyclerView.setHasFixedSize(true);

    }
}