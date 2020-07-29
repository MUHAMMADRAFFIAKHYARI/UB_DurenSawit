package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.Model.LoginUser;
import android.example.ub_durensawit.Model.User;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.io.StringReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin_;
    EditText etEmail, etPassword;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin_= findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        btnLogin_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        final String email=etEmail.getText().toString().trim();
        final String password=etPassword.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Masukkan Email Terlebih Dahulu !");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Masukkan Email yang Benar !");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etPassword.setError("Masukkan Password Terlebih Dahulu !");
            etPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            etPassword.setError("Password Harus 6 Karakter atau Lebih !");
            etPassword.requestFocus();
            return;
        }

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.login(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        Intent toLanding = new Intent(LoginActivity.this, LandingActivity.class);
        startActivity(toLanding);
        finish();
    }

    public void daftarText(View view) {
        Intent toLogin = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(toLogin);
        finish();
    }
}