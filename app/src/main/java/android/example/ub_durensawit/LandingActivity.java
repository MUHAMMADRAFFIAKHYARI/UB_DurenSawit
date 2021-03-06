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
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingActivity extends AppCompatActivity {

    String nama;
    String email;

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        nama = getIntent().getStringExtra("nama");
        email = getIntent().getStringExtra("email");
        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        /*load bottom navbar*/
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new BerandaFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment  selectedFragment = null;

                    Bundle bundle = new Bundle();
                    bundle.putString("nama", nama);
                    bundle.putString("email", email);
                   // bundle.putString("email", nama.getEmail());

//                    BerandaFragment berandaFragment = new BerandaFragment();
//                    berandaFragment.setArguments(bundle);

                    switch (menuItem.getItemId()){
                        case R.id.navHome:
                            selectedFragment = new BerandaFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.navCategory:
                            selectedFragment = new KategoriFragment();
                            break;
                        case R.id.navTransaction:
                            selectedFragment = new TransaksiFragment();
                            break;
                        case R.id.navUser:
                            selectedFragment = new PenggunaFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
                    return true;
                }
            };

}