package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void daftarText(View view) {
        Intent toLogin = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(toLogin);
    }
}