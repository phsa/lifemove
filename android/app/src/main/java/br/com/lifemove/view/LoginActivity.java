package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.service.LoginService;

public class LoginActivity extends AppCompatActivity {

    private EditText username_field;
    private EditText password_field;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        ImageView logoHolder = findViewById(R.id.logo_holder);
        logoHolder.setImageResource(R.drawable.lifemove_logo_vector);

        username_field = findViewById(R.id.name_field);
        password_field = findViewById(R.id.password_field);

        Button sign_in = findViewById(R.id.sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_field.getText().toString();
                String password = password_field.getText().toString();

                if(username.isEmpty() || password.isEmpty())
                    Toast.makeText(LoginActivity.this, R.string.all_fields_must_be_filled, Toast.LENGTH_SHORT).show();
                else {
                    LoginService loginService = new LoginService(getSimpleAsynchronousTaskListener());
                    loginService.authenticate(username, password);
                }
            }
        });
    }

    private SimpleAsynchronousTaskListener getSimpleAsynchronousTaskListener() {
        return new SimpleAsynchronousTaskListener() {

            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, PerfilActivity.class));
                finish();
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        };
    }
}