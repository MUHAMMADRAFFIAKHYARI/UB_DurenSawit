package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPageAdapter introViewPageAdapter;
    TabLayout tabIndicator;
    Button buttonToRegister;
    Button buttonToGoogleLogin;
    TextView textViewToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        tabIndicator = (TabLayout) findViewById(R.id.tab_indicator);
        textViewToLogin = (TextView) findViewById(R.id.login_text);
        buttonToRegister = findViewById(R.id.register_button);

        buttonToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
            }
        });

        // mengisi list screen pada viepager
        List<ScreenItems> mList = new ArrayList<>();
        mList.add(new ScreenItems("Cari Semua Kebutuhanmu","temukan semua barang yang\n" +
                "kamu butuh",R.drawable.image1));
        mList.add(new ScreenItems("Pilih apa kesuakaanmu","Pilih barang apa saja yang\n" +
                "kamu suka",R.drawable.image2));
        mList.add(new ScreenItems("Dapatkan yang kamu pilih","Yang kamu dapatkan adalah\n" +
                "apa yang kamu pilih dan \n" +
                "kamu inginkan",R.drawable.image3));

        // set viewpager
        screenPager = findViewById(R.id.vpager);
        introViewPageAdapter = new IntroViewPageAdapter(this, mList);
        screenPager.setAdapter(introViewPageAdapter);

        //set TabLayout
        tabIndicator.setupWithViewPager(screenPager);

    }



    public void LoginText(View view) {
        Intent toLogin = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }
}