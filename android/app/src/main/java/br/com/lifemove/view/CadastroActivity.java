package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.lifemove.R;
import br.com.lifemove.model.User;

public class CadastroActivity extends AppCompatActivity {

    EditText name, username, email, password;
    Button cadastro;

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastro = (Button) findViewById(R.id.sign_in);
        name = (EditText) findViewById(R.id.name_field);
        username = (EditText) findViewById(R.id.username_field);
        email = (EditText) findViewById(R.id.email_field_field);
        password = (EditText) findViewById(R.id.password_field);

        logo = (ImageView) findViewById(R.id.logo_holder);
        logo.setImageResource(R.drawable.lifemove_logo_vector);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://lifemove-73e25.firebaseio.com/");
        final DatabaseReference myRef = database.getReference("users");



        myRef.setValue("Hello, World!");

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names, usernames, emails, passwords;
                names = name.getText().toString();
                usernames = username.getText().toString();
                emails = email.getText().toString();
                passwords = password.getText().toString();


                if(usernames.isEmpty() || names.isEmpty() || emails.isEmpty() || passwords.isEmpty()){
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else {
                    myRef.push().setValue(new User(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString()));
                    Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    perfilEdit();
                }
            }
        });
    }

    public void perfilEdit(){
        Intent intent = new Intent(CadastroActivity.this, PerfilActivity.class);
        startActivity(intent);
        finish();
    }
}
