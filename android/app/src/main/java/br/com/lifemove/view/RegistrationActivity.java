package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.model.User;
import br.com.lifemove.service.AuthenticationService;
import br.com.lifemove.utils.StringUtils;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameInput, usernameInput, emailInput, passwordInput, confirmPasswordInput;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        createAccount = findViewById(R.id.create_account);
        nameInput = findViewById(R.id.registration_name_field);
        usernameInput = findViewById(R.id.registration_username_field);
        emailInput = findViewById(R.id.registration_email_field);
        passwordInput = findViewById(R.id.registration_password_field);
        confirmPasswordInput = findViewById(R.id.registration_confirm_password_field);

        ImageView logo = findViewById(R.id.registration_logo_holder);
        logo.setImageResource(R.drawable.light_logo);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, StringUtils.valueOf(R.string.all_fields_must_be_filled), Toast.LENGTH_LONG).show();
                } else {
                    String passwordConfirmation = confirmPasswordInput.getText().toString();
                    if(password.compareTo(passwordConfirmation) != 0) {
                        passwordInput.setTextColor(getResources().getColor(R.color.red));
                        confirmPasswordInput.setTextColor(getResources().getColor(R.color.red));
                        Toast.makeText(RegistrationActivity.this, getString(R.string.invalid_password_confirmation), Toast.LENGTH_SHORT).show();
                    } else {
                        createAccount.setEnabled(false);
                        AuthenticationService registerService = new AuthenticationService(getSimpleAsynchronousTaskListener());
                        registerService.register(new User(name, username, email, password));
                    }
                }
            }
        });
    }

    private SimpleAsynchronousTaskListener getSimpleAsynchronousTaskListener() {
        return new SimpleAsynchronousTaskListener() {

            @Override
            public void onSuccess() {
                startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                Toast.makeText(RegistrationActivity.this, getString(R.string.successfully_registration), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(RegistrationActivity.this, reason, Toast.LENGTH_LONG).show();
            }
        };
    }

}
