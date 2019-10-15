package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import br.com.lifemove.R;

public class EditarPerfil extends AppCompatActivity {

    EditText nome, info;
    ImageButton imagbtt;
    Button update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        nome = (EditText) findViewById(R.id.nome);
        info = (EditText) findViewById(R.id.info);

        imagbtt = (ImageButton) findViewById(R.id.imageButton);
        update = (Button) findViewById(R.id.update);

        imagbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarPerfil.this, PerfilActivity.class);
                intent.putExtra("nome", nome.getText().toString());
                intent.putExtra("info", info.getText().toString());
                startActivity(intent);
                finish();
            }
        });



    }
}
