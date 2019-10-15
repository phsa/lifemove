package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.lifemove.R;
import br.com.lifemove.utils.StringUtils;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText emailInput = findViewById(R.id.email_field_to_reset);
        Button resetButton = findViewById(R.id.reset_password_button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.checkEmail(emailInput.getText().toString())) {
                    Toast.makeText(ResetPasswordActivity.this, "[Ação Fake] Enviamos um email pra você.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(ResetPasswordActivity.this, "Formato de email inválido.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
