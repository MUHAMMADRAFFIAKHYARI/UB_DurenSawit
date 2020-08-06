package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.Model.LoginResponse;
import android.example.ub_durensawit.Model.User;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                User user = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Masuk Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,LandingActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
//        User userRequest = new User();
//        userRequest.setEmail(etEmail.getText().toString());
//        userRequest.setPassword(etPassword.getText().toString());
//
//        Call<User> userCall= ApiClient.getApiInterface().login(userRequest);
//        userCall.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Toast.makeText(LoginActivity.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
//                User userResponse = response.body();
//
////                new Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        startActivity(new Intent(LoginActivity.this,LandingActivity.class).putExtra("data",userResponse.getEmail()));
////                    }
////                });
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
    }

    public void daftarText(View view) {
        Intent toLogin = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(toLogin);
        finish();
    }
}