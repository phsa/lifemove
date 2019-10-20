package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.service.AuthenticationService;
import br.com.lifemove.utils.SharedPreferencesUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private boolean usernameHasText = false;

    private EditText passwordInput;
    private boolean passwordHasText = false;

    private TextView forgotPasswordView;

    private Button signIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        String user = SharedPreferencesUtils.readOfSharedPreferences(SharedPreferencesUtils.USER_KEY);
        if (user != null && !user.isEmpty()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        ImageView logoHolder = findViewById(R.id.login_logo_holder);
        logoHolder.setImageResource(R.drawable.light_logo);

        usernameInput = findViewById(R.id.login_username_field);
        passwordInput = findViewById(R.id.login_password_field);

        forgotPasswordView = findViewById(R.id.forgot_password_view);
        signIn = findViewById(R.id.sign_in);
        Button signUp = findViewById(R.id.login_sign_up);


        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameHasText = charSequence.length() > 0;
                if(usernameHasText && passwordHasText && !signIn.isEnabled())
                    enableLoginButton();
                else if ((!usernameHasText || !passwordHasText) && signIn.isEnabled())
                    disableLoginButton();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordHasText = charSequence.length() > 0;
                if(usernameHasText && passwordHasText && !signIn.isEnabled())
                    enableLoginButton();
                else if ((!usernameHasText || !passwordHasText) && signIn.isEnabled())
                    disableLoginButton();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        forgotPasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setElementsUsability(false);

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(username.isEmpty() || password.isEmpty())
                    Toast.makeText(LoginActivity.this, R.string.all_fields_must_be_filled, Toast.LENGTH_SHORT).show();
                else {
                    AuthenticationService loginService = new AuthenticationService(getSimpleAsynchronousTaskListener());
                    loginService.authenticate(username, password);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void setElementsUsability(boolean canBeUsed) {
        if (canBeUsed) {
            signIn.setText(R.string.prompt_sign_in);
            enableLoginButton();
        } else {
            signIn.setText(R.string.logging_in);
            disableLoginButton();
        }
        usernameInput.setEnabled(canBeUsed);
        passwordInput.setEnabled(canBeUsed);
        forgotPasswordView.setEnabled(canBeUsed);
    }

    private void enableLoginButton() {
        signIn.setEnabled(true);
        signIn.setTextColor(getResources().getColor(R.color.black));
    }

    private void disableLoginButton() {
        signIn.setEnabled(false);
        signIn.setTextColor(getResources().getColor(R.color.disabled_text));
    }



    private SimpleAsynchronousTaskListener getSimpleAsynchronousTaskListener() {
        return new SimpleAsynchronousTaskListener() {

            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_SHORT).show();
                setElementsUsability(true);
            }
        };
    }
}