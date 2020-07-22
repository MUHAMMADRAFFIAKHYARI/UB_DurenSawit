package android.example.ub_durensawit;

import android.content.Intent;
import android.example.ub_durensawit.SMTP.SendMail;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;
import java.util.Random;
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Button RegisterButton;
    private Button tesButton;
    @NotEmpty
    @Length(min = 4, max =  30)
    @Pattern(regex =  "^[a-zA-Z]*$")
    private EditText InputName;
    @NotEmpty
    private EditText InputPhoneNumber;
    @NotEmpty
    @Length(min = 6, max =  20)
    @Pattern(regex =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$")
    private EditText InputPassword;
    @NotEmpty
    @Email
    private EditText InputEmail;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Init();
        validator = new Validator(this);
        validator.setValidationListener(this);

        TextView toLoginR = findViewById(R.id.toLoginR);
        toLoginR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    private void Init(){
        RegisterButton = (Button) findViewById(R.id.register_button);
        InputName = (EditText) findViewById(R.id.register_name_input);
        InputEmail = (EditText) findViewById(R.id.register_email_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Validate();


            }
        });

    }

    private String CodeGenerator(){
        Random RandNum = new Random();
        StringBuilder code = new StringBuilder();
        for (int i=0;i <= 4; i++){
            code.append(RandNum.nextInt(10));
        }
        return code.toString();


    }
    private void Validate(){
        validator.validate();
        //nanti disini ada code buat validasi apakah email sudah ada di database
        /**
         if (username.equalsIgnoreCase("pmk")) {
         editTextUsername.setError(getText(R.string.username_already_exists));
         } **/
    }

    //Method buat ngirim email
    private void sendEmail(String email, String kode) {
        //Getting content for email
        String subject = "Kode Verifikasi";
        String message = kode;

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onValidationSucceeded() {
        /*Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();*/
        String email = InputEmail.getText().toString();
        String nama = InputName.getText().toString();
        String password = InputPassword.getText().toString();
        String NoTelpon = InputPhoneNumber.getText().toString();
        String kode = CodeGenerator();
        sendEmail(email,kode);
        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra("kode",kode);
        intent.putExtra("nama",nama);
        intent.putExtra("password",password);
        intent.putExtra("email",email);
        intent.putExtra("NoTelpon",NoTelpon);
        intent.putExtra("kode",kode);

        startActivity(intent);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                TextView textView = (TextView) view;

                if(view.getId() == R.id.register_name_input){
                    String nama = textView.getText().toString();
                    ((EditText) view).setError(" Nama harus memiliki 4-30 huruf (tidak boleh angka)");

                }

                else if(view.getId() == R.id.register_password_input){
                    ((EditText) view).setError(" Password minimal memiliki 1 huruf kapital, 1 nomer, " +
                            "minimal 6 dan maksimal 20 karakter ");

                }
                else if(view.getId() == R.id.register_phone_number_input){
                    ((EditText) view).setError(" Nomer telepon tidak boleh kosong ");

                }
                else if(view.getId() == R.id.register_email_input){
                    ((EditText) view).setError(" Mohon isi email dengan benar ");

                }

                else{
                    ((EditText) view).setError(message);
                }

            }

            else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    //cek Apakah email ada
    public boolean EmailExist(String email){
        //Sebentar yaa wkwkwk
        return true;
    }

}