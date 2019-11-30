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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import br.com.lifemove.R;
import br.com.lifemove.interfaces.optimization.optTextWatcher;
import br.com.lifemove.listener.AccessControlListener;
import br.com.lifemove.model.User;
import br.com.lifemove.service.AccessControlService;
import br.com.lifemove.utils.LifeMoveApplicationUtils;
import br.com.lifemove.utils.StringUtils;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameInput;

    private EditText usernameInput;
    private ImageView usernameAvailability;

    private EditText emailInput;

    private EditText passwordInput;
    private TextView passwordWarning;

    private EditText confirmPasswordInput;

    private Button createAccount;


    private AccessControlService registerService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setViewElements();

        configureInputFields();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnablingElements(false);

                String name = nameInput.getText().toString().trim();
                String username = usernameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String passwordConfirmation = confirmPasswordInput.getText().toString().trim();

                if (emptyFieldsWereIdentified()) {
                    Toast.makeText(RegistrationActivity.this, StringUtils.valueOf(R.string.all_fields_must_be_filled), Toast.LENGTH_LONG).show();
                } else if (username.matches(StringUtils.INVALID_USERNAME_PATTERN)) {
                    LifeMoveApplicationUtils.setInputSomethingWrongAppearance(usernameInput);
                    Toast.makeText(RegistrationActivity.this, R.string.invalid_username_characters_message, Toast.LENGTH_LONG).show();
                } else if (!StringUtils.checkEmail(email)) {
                    LifeMoveApplicationUtils.setInputSomethingWrongAppearance(emailInput);
                    Toast.makeText(RegistrationActivity.this, getString(R.string.invalid_email_format), Toast.LENGTH_SHORT).show();
                } else if (password.compareTo(passwordConfirmation) != 0) {
                    LifeMoveApplicationUtils.setInputSomethingWrongAppearance(confirmPasswordInput);
                    Toast.makeText(RegistrationActivity.this, getString(R.string.invalid_password_confirmation), Toast.LENGTH_SHORT).show();
                } else {
                    createAccount.setEnabled(false);
                    registerService.register(new User(name, username, email, password));
                }

                setEnablingElements(true);
            }
        });

        registerService = new AccessControlService(getSimpleAsynchronousTaskListener());
    }



    private boolean emptyFieldsWereIdentified() {
        boolean thereAreEmptyFields = false;
        if (nameInput.getText().toString().isEmpty()) {
            thereAreEmptyFields = true;
            DrawableCompat.setTint(nameInput.getBackground(), getResources().getColor(R.color.red));
        }
        if (usernameInput.getText().toString().isEmpty()) {
            thereAreEmptyFields = true;
            DrawableCompat.setTint(usernameInput.getBackground(), getResources().getColor(R.color.red));
        }
        if (emailInput.getText().toString().isEmpty()) {
            thereAreEmptyFields = true;
            DrawableCompat.setTint(emailInput.getBackground(), getResources().getColor(R.color.red));
        }
        if (passwordInput.getText().toString().isEmpty()) {
            thereAreEmptyFields = true;
            DrawableCompat.setTint(passwordInput.getBackground(), getResources().getColor(R.color.red));
        }
        if (confirmPasswordInput.getText().toString().isEmpty()) {
            thereAreEmptyFields = true;
            DrawableCompat.setTint(confirmPasswordInput.getBackground(), getResources().getColor(R.color.red));
        }

        return  thereAreEmptyFields;
    }


    /**
     * SETTING UP VIEW ELEMENTS
     */

    private void setViewElements() {
        ImageView logo = findViewById(R.id.registration_logo_holder);
        logo.setImageResource(R.drawable.light_logo);
        nameInput = findViewById(R.id.registration_name_field);
        usernameInput = findViewById(R.id.registration_username_field);
        usernameAvailability = findViewById(R.id.username_availability_sign);
        emailInput = findViewById(R.id.registration_email_field);
        passwordInput = findViewById(R.id.registration_password_field);
        passwordWarning = findViewById(R.id.password_warning);
        confirmPasswordInput = findViewById(R.id.registration_confirm_password_field);
        createAccount = findViewById(R.id.create_account);

        int highlight = getResources().getColor(R.color.highlight);
        DrawableCompat.setTint(nameInput.getBackground(), highlight);
        DrawableCompat.setTint(usernameInput.getBackground(), highlight);
        DrawableCompat.setTint(emailInput.getBackground(), highlight);
        DrawableCompat.setTint(usernameInput.getBackground(), highlight);
        DrawableCompat.setTint(passwordInput.getBackground(), highlight);
        DrawableCompat.setTint(confirmPasswordInput.getBackground(), highlight);
    }

    private void configureInputFields() {

        nameInput.addTextChangedListener(getDefaultInputWatcher(nameInput));
        emailInput.addTextChangedListener(getDefaultInputWatcher(emailInput));
        confirmPasswordInput.addTextChangedListener(getDefaultInputWatcher(confirmPasswordInput));

        usernameInput.addTextChangedListener(new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setCreateButtonsTapAbility();


                usernameAvailability.setVisibility(View.INVISIBLE);
                LifeMoveApplicationUtils.setInputAlrightAppearance(usernameInput);

                registerService.checkForUsername(charSequence.toString());
            }
        });

        usernameInput.setFilters(new InputFilter[] {
                        new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
                                if (charSequence.toString().matches("^[a-zA-Z0-9]*"))
                                    return null;
                                else {
                                    String result = charSequence.toString().replaceAll(StringUtils.INVALID_USERNAME_PATTERN, "");
                                    if (result.length() != charSequence.length())
                                        Toast.makeText(RegistrationActivity.this, R.string.usernames_characters_restriction, Toast.LENGTH_SHORT).show();
                                    return result;
                                }
                            }
                        }
                }
        );

        passwordInput.addTextChangedListener(new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setCreateButtonsTapAbility();

                if(charSequence.length() > 0 && charSequence.length() < 8) {
                    passwordWarning.setVisibility(View.VISIBLE);
                    LifeMoveApplicationUtils.setInputSomethingWrongAppearance(passwordInput);
                } else if (charSequence.length() >= 32) {
                    Toast.makeText(RegistrationActivity.this, R.string.maximum_password_length, Toast.LENGTH_SHORT).show();
                    LifeMoveApplicationUtils.setInputSomethingWrongAppearance(passwordInput);
                } else {
                    passwordWarning.setVisibility(View.INVISIBLE);
                    LifeMoveApplicationUtils.setInputAlrightAppearance(passwordInput);
                }
            }
        });
    }



    private TextWatcher getDefaultInputWatcher(final EditText input) {
        return new optTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setCreateButtonsTapAbility();
                if(input.getCurrentTextColor() != getResources().getColor(R.color.white))
                    input.setTextColor(getResources().getColor(R.color.white));
                DrawableCompat.setTint(input.getBackground(), getResources().getColor(R.color.highlight));
            }
        };
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


    private Drawable retrieveDrawable(int dResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return getDrawable(dResId);
        else
            return getResources().getDrawable(dResId, getTheme());
    }



    /**
     * ELEMENTS BEHAVIOR
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

    private void setEnablingElements(boolean canBeUsed) {
        if (canBeUsed) {
            createAccount.setText(R.string.prompt_create_account);
            enableCreateButton();
        } else {
            createAccount.setText(R.string.logging_in);
            disableCreateButton();
        }
        nameInput.setEnabled(canBeUsed);
        usernameInput.setEnabled(canBeUsed);
        emailInput.setEnabled(canBeUsed);
        passwordInput.setEnabled(canBeUsed);
        confirmPasswordInput.setEnabled(canBeUsed);
    }


    /**
     * LISTENERS
     */

    private AccessControlListener getSimpleAsynchronousTaskListener() {
        return new AccessControlListener() {

            @Override
            public void handleUsernameCheck(String checkedUsername, boolean available) {
                if (usernameInput.getText().length() > 0
                        && usernameInput.getText().toString().compareTo(checkedUsername) == 0) {
                    if (available) {
                        usernameAvailability.setImageDrawable(retrieveDrawable(R.drawable.checked_icon));
                        usernameAvailability.setVisibility(View.VISIBLE);
                        LifeMoveApplicationUtils.setInputAppearance(usernameInput, R.color.soft_green, R.color.soft_green);
                    } else {
                        usernameAvailability.setImageDrawable(retrieveDrawable(R.drawable.remove_icon));
                        usernameAvailability.setVisibility(View.VISIBLE);
                        LifeMoveApplicationUtils.setInputSomethingWrongAppearance(usernameInput);
                    }
                } else {
                    usernameAvailability.setVisibility(View.INVISIBLE);
                    LifeMoveApplicationUtils.setInputAlrightAppearance(usernameInput);
                }
            }

            @Override
            public void onSuccess() {
                Intent homeIntent = new Intent(RegistrationActivity.this, HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                Toast.makeText(RegistrationActivity.this, getString(R.string.successfully_registration), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(RegistrationActivity.this, reason, Toast.LENGTH_LONG).show();
                setEnablingElements(true);
            }
        };
    }

}
