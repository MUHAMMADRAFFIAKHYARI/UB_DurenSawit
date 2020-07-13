package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
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
    int RC_SIGN_IN = 0;

    GoogleSignInClient mGoogleSignInClient;

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

        buttonToGoogleLogin = findViewById(R.id.login_button);
        buttonToGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.login_button:
                        signIn();
                        break;
                }

            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        // mengisi list screen pada viepager
        List<ScreenItems> mList = new ArrayList<>();
        mList.add(new ScreenItems("Cari Semua Kebutuhanmu","temukan semua barang yang\n" +
                "kamu butuh",R.drawable.image1));
        mList.add(new ScreenItems("Pilih apa kesuakaanmu","Pilih barang apa saja yang\n" +
                "kamu suka",R.drawable.image2));
        mList.add(new ScreenItems("Dapatkan yang kamu pilih","Yang kamu dapatkan adalah apa yang kamu pilih dan kamu inginkan",R.drawable.image3));

        // set viewpager
        screenPager = findViewById(R.id.vpager);
        introViewPageAdapter = new IntroViewPageAdapter(this, mList);
        screenPager.setAdapter(introViewPageAdapter);

        //set TabLayout
        tabIndicator.setupWithViewPager(screenPager);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//
//    }

    public void LoginText(View view) {
        Intent toLogin = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(IntroActivity.this, Landing.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR", "signInResult:failed code=" + e.getStatusCode());
        }
    }

}