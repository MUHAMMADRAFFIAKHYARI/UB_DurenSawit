package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.ub_durensawit.Adapter.RecyclerViewAdapterHome;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AllitemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterHome recyclerViewAdapterHome;

    int [] imageProduct = {R.drawable.product1,R.drawable.product2,R.drawable.product3,R.drawable.product4,R.drawable.product1};
    String [] nameProduct = {"Mie Goreng", "T-Shirt",  "Baygon", "Indomie", "Mie Goreng"};
    String [] categoryProduct = {"makanan", "pakaian",  "kebutuhan", "makanan", "makanan"};
    String [] priceProduct = {"33.23", "14.303",  "100.00", "34.00", "2.000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allitem);

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        recyclerView = findViewById(R.id.rcv_allItem);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapterHome = new RecyclerViewAdapterHome(imageProduct, nameProduct, categoryProduct, priceProduct);

        recyclerView.setAdapter(recyclerViewAdapterHome);

        recyclerView.setHasFixedSize(true);

    }
}