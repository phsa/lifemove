package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import br.com.lifemove.R;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.model.User;
import br.com.lifemove.service.AuthenticationService;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, usernameInput, emailInput, passwordInput, confirmPasswordInput;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = findViewById(R.id.sign_up);
        nameInput = findViewById(R.id.name_field);
        usernameInput = findViewById(R.id.register_username_field);
        emailInput = findViewById(R.id.email_field);
        passwordInput = findViewById(R.id.register_password_field);
        confirmPasswordInput = findViewById(R.id.confirm_password_field);

        ImageView logo = findViewById(R.id.logo_holder);
        logo.setImageResource(R.drawable.lifemove_logo_vector);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    String passwordConfirmation = confirmPasswordInput.getText().toString();
                    if(password.compareTo(passwordConfirmation) != 0) {
                        passwordInput.setTextColor(getResources().getColor(R.color.red));
                        confirmPasswordInput.setTextColor(getResources().getColor(R.color.red));
                        Toast.makeText(RegisterActivity.this, "Confirmação inválida.", Toast.LENGTH_SHORT).show();
                    } else {
                        signUp.setEnabled(false);
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
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                Toast.makeText(RegisterActivity.this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(RegisterActivity.this, reason, Toast.LENGTH_LONG).show();
            }
        };
    }

}
