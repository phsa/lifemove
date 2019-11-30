package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.lifemove.R;

public class PresentationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        ImageView logoHolder = findViewById(R.id.presentation_logo_holder);
        logoHolder.setImageResource(R.drawable.light_logo);

        Button signIn = findViewById(R.id.presentation_sign_in);
        Button signUp = findViewById(R.id.presentation_sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PresentationActivity.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PresentationActivity.this, RegistrationActivity.class));
            }
        });

    }
}
