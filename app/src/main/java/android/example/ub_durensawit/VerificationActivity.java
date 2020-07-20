package android.example.ub_durensawit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.Model.User;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {
    private EditText InputCode;
    private String TrueCode,nama,password,email,NoTelpon;
    private ApiInterface apiInterface;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Bundle extras = getIntent().getExtras();
        Button btnVerif = findViewById(R.id.btnVerif);
        InputCode = (EditText) findViewById(R.id.input_code);
        if(extras == null){
            TrueCode = null;
            InputCode.setText("Gagal Mengirim Pesan");
        } else{
            TrueCode = extras.getString("kode");
            nama = extras.getString("nama");
            password = extras.getString("password");
            email = extras.getString("email");
            NoTelpon = extras.getString("kode");
        }
        InputCode.setText(TrueCode);



        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Validate();
            }
        });
    }

    private void Validate(){
        String input_code = InputCode.getText().toString();
        if(TrueCode.equals(input_code)){
            Toast.makeText(this, "Kode Verifikasi Benar", Toast.LENGTH_LONG).show();
            //insertUser();
            startActivity(new Intent(VerificationActivity.this, LandingActivity.class));
            finish();
        } else{
            Toast.makeText(this, "Kode Verifikasi Salah", Toast.LENGTH_LONG).show();
        }
    }

    private void insertUser() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        Call<User> call = apiInterface.insertUser(nama , email, password, NoTelpon);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")){
                    Toast.makeText(VerificationActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(VerificationActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(VerificationActivity.this, "Jaringan Error! "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}