package br.com.lifemove.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import br.com.lifemove.R;
import br.com.lifemove.interfaces.optimization.optTextWatcher;
import br.com.lifemove.listener.SimpleAsynchronousTaskListener;
import br.com.lifemove.model.User;
import br.com.lifemove.service.AccessControlService;
import br.com.lifemove.utils.StringUtils;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameInput;

    private EditText usernameInput;
    private ImageView usernameAvailability;

    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;

    private Button createAccount;


    private AccessControlService registerService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        createAccount = findViewById(R.id.create_account);
        nameInput = findViewById(R.id.registration_name_field);
        usernameInput = findViewById(R.id.registration_username_field);
        usernameAvailability = findViewById(R.id.username_availability_sign);
        emailInput = findViewById(R.id.registration_email_field);
        passwordInput = findViewById(R.id.registration_password_field);
        confirmPasswordInput = findViewById(R.id.registration_confirm_password_field);

        TextWatcher watcher = new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0)
                    setCreateButtonsTapAbility();
            }
        };

        nameInput.addTextChangedListener(watcher);

        usernameInput.addTextChangedListener(new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean usernameInputHasText = charSequence.toString().length() > 0;
                setCreateButtonsTapAbility();

                boolean alreadyInUse = registerService.alreadyInUse(charSequence.toString());

                Drawable inputBackground = usernameInput.getBackground();

                if (usernameInputHasText) {
                    if (alreadyInUse) {
                        usernameAvailability.setImageDrawable(retrieveDrawable(R.drawable.remove_icon));
                        usernameAvailability.setVisibility(View.VISIBLE);
                        usernameInput.setTextColor(getResources().getColor(R.color.red));
                        DrawableCompat.setTint(inputBackground, getResources().getColor(R.color.red));
                    } else {
                        usernameAvailability.setImageDrawable(retrieveDrawable(R.drawable.checked_icon));
                        usernameAvailability.setVisibility(View.VISIBLE);
                        usernameInput.setTextColor(getResources().getColor(R.color.soft_green));
                        DrawableCompat.setTint(inputBackground, getResources().getColor(R.color.soft_green));
                    }
                } else {
                    usernameAvailability.setVisibility(View.INVISIBLE);
                    DrawableCompat.setTint(inputBackground, getResources().getColor(R.color.black));
                }
            }
        });
//        usernameInput.setFilters(new InputFilter[] {
//                    new InputFilter() {
//                        @Override
//                        public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
//                            if (charSequence.toString().matches("^[a-zA-Z0-9]*"))
//                                return null;
//                            else
//                                return charSequence.toString().replaceAll(StringUtils.INVALID_USERNAME_PATTERN, "");
//                        }
//                    }
//                }
//        );

        emailInput.addTextChangedListener(watcher);
        passwordInput.addTextChangedListener(watcher);
        confirmPasswordInput.addTextChangedListener(watcher);

        ImageView logo = findViewById(R.id.registration_logo_holder);
        logo.setImageResource(R.drawable.light_logo);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String passwordConfirmation = confirmPasswordInput.getText().toString();

                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, StringUtils.valueOf(R.string.all_fields_must_be_filled), Toast.LENGTH_LONG).show();
                } else if (username.matches(StringUtils.INVALID_USERNAME_PATTERN)) {
                    Toast.makeText(RegistrationActivity.this, R.string.invalid_username_characters_message, Toast.LENGTH_LONG).show();
                } else if (!StringUtils.checkEmail(emailInput.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, getString(R.string.invalid_email_format), Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(passwordConfirmation) != 0) {
                        passwordInput.setTextColor(getResources().getColor(R.color.red));
                        confirmPasswordInput.setTextColor(getResources().getColor(R.color.red));
                        Toast.makeText(RegistrationActivity.this, getString(R.string.invalid_password_confirmation), Toast.LENGTH_SHORT).show();
                    } else {
                        createAccount.setEnabled(false);
                        registerService.register(new User(name, username, email, password));
                    }
                }
            }
        });

        registerService = new AccessControlService(getSimpleAsynchronousTaskListener());
    }

    private Drawable retrieveDrawable(int dResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return getDrawable(dResId);
        else
            return getResources().getDrawable(dResId, getTheme());
    }



    /**
     * BUTTON BEHAVIOR
     */

    private void enableCreateButton() {
        createAccount.setEnabled(true);
        createAccount.setTextColor(getResources().getColor(R.color.black));
    }

    private void disableCreateButton() {
        createAccount.setEnabled(false);
        createAccount.setTextColor(getResources().getColor(R.color.disabled_text));
    }

    private void setCreateButtonsTapAbility() {
        if(doAllInputHaveText() && !createAccount.isEnabled())
            enableCreateButton();
        else if (anyInputHaveNoText() && createAccount.isEnabled())
            disableCreateButton();
    }


    /**
     * INPUT CHECK
     */

    private boolean anyInputHaveNoText() {
        return nameInput.getText().toString().length() == 0
                || usernameInput.getText().toString().length() == 0
                || emailInput.getText().toString().length() == 0
                || passwordInput.getText().toString().length() == 0
                || confirmPasswordInput.getText().toString().length() == 0;
    }

    private boolean doAllInputHaveText() {
        return nameInput.getText().toString().length() > 0
                && usernameInput.getText().toString().length() > 0
                && emailInput.getText().toString().length() > 0
                && passwordInput.getText().toString().length() > 0
                && confirmPasswordInput.getText().toString().length() > 0;
    }



    /**
     * LISTENERS
     */

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
