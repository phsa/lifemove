package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import br.com.lifemove.R;
import br.com.lifemove.interfaces.optimization.optTextWatcher;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.service.AccessControlService;
import br.com.lifemove.utils.SharedPreferencesUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;

    private TextView forgotPasswordView;

    private Button signIn;
    private Button signUp;

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

        setViewElements();

        usernameInput.addTextChangedListener(new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setLoginButtonTapAbility();
                DrawableCompat.setTint(usernameInput.getBackground(), getResources().getColor(R.color.highlight));
            }
        });

        passwordInput.addTextChangedListener(new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setLoginButtonTapAbility();

                if (charSequence.length() >= 32) {
                    Toast.makeText(LoginActivity.this, R.string.maximum_password_length, Toast.LENGTH_SHORT).show();
                    DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.red));
                } else
                    DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.highlight));



            }
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
                setEnablingElements(false);

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(username.isEmpty()) {
                    DrawableCompat.setTint(usernameInput.getBackground(), getResources().getColor(R.color.red));
                    Toast.makeText(LoginActivity.this, R.string.all_fields_must_be_filled, Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.red));
                    Toast.makeText(LoginActivity.this, R.string.all_fields_must_be_filled, Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.red));
                    Toast.makeText(LoginActivity.this, R.string.minimum_password_length, Toast.LENGTH_SHORT).show();
                } else {
                    AccessControlService loginService = new AccessControlService(getSimpleAsynchronousTaskListener());
                    loginService.authenticate(username, password);
                }

                setEnablingElements(true);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }
    private void setViewElements() {
        ImageView logoHolder = findViewById(R.id.login_logo_holder);
        logoHolder.setImageResource(R.drawable.light_logo);

        usernameInput = findViewById(R.id.login_username_field);
        passwordInput = findViewById(R.id.login_password_field);
        forgotPasswordView = findViewById(R.id.forgot_password_view);
        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.login_sign_up);

        DrawableCompat.setTint(usernameInput.getBackground(), getResources().getColor(R.color.highlight));
        DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.highlight));
    }

    private void setLoginButtonTapAbility() {
        if (usernameInput.getText().toString().length() > 0
                && passwordInput.getText().toString().length() > 0
                && !signIn.isEnabled())
            enableLoginButton();
        else if ((usernameInput.getText().toString().length() == 0
                    || passwordInput.getText().toString().length() == 0)
                    && signIn.isEnabled())
            disableLoginButton();
    }

    private void setEnablingElements(boolean canBeUsed) {
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


    /**
     * LISTENERS AND WATCHERS
     */


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
                setEnablingElements(true);
            }
        };
    }

}