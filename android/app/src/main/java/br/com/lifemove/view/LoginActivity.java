package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;
import br.com.lifemove.SimpleAsynchronousTaskListener;
import br.com.lifemove.service.LoginService;

public class LoginActivity extends AppCompatActivity {

    private EditText username_field;
    private EditText password_field;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        username_field = findViewById(R.id.username_field);
        password_field = findViewById(R.id.password_field);

        Button sign_in = findViewById(R.id.sign_in_button);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_field.getText().toString();
                String password = password_field.getText().toString();

                LoginService loginService = new LoginService(getSimpleAsynchronousTaskListener());
                loginService.authenticate(username, password);
            }
        });
    }

    private SimpleAsynchronousTaskListener getSimpleAsynchronousTaskListener() {
        return new SimpleAsynchronousTaskListener() {

            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        };
    }
}