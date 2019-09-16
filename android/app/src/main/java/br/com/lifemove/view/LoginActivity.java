package br.com.lifemove.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        ImageView logo_holder = findViewById(R.id.logo_holder);
        logo_holder.setVisibility(View.VISIBLE);
    }
}
