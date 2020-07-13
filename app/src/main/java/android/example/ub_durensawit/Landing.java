package android.example.ub_durensawit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.example.ub_durensawit.Fragment.BerandaFragment;
import android.example.ub_durensawit.Fragment.KategoriFragment;
import android.example.ub_durensawit.Fragment.PenggunaFragment;
import android.example.ub_durensawit.Fragment.TransaksiFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new BerandaFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment  selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.navHome:
                            selectedFragment = new BerandaFragment();
                            break;
                        case R.id.navCategory:
                            selectedFragment = new KategoriFragment();
                            break;
                        case R.id.navTransaction:
                            selectedFragment = new TransaksiFragment();
                            break;
                        case R.id.navUser:
                            selectedFragment = new PenggunaFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Klik lagi untuk keluar", Toast.LENGTH_SHORT).show();
    }
}