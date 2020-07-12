package android.example.ub_durensawit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Button RegisterButton;
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

        //hilangin actionBar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS);

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
    private void Validate(){
        validator.validate();
        //nanti disini ada code buat validasi apakah email sudah ada di database
        /**
         if (username.equalsIgnoreCase("pmk")) {
         editTextUsername.setError(getText(R.string.username_already_exists));
         } **/
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
        Intent Login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(Login);
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

    public void loginText(View view) {
        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(toLogin);
        finish();
    }
}