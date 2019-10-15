package br.com.lifemove.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.lifemove.R;
import br.com.lifemove.RView.Evento;
import br.com.lifemove.RView.LineAdapter;

public class PerfilActivity extends AppCompatActivity {

    ImageView imageView;
    TextView seguidores, seguindo, eventos, pbl, q1,q2,q3, infouser, nomeuser;
    Button editarP;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private LineAdapter mAdapter;

    int segS=0,seg=0, evts=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);

        imageView = (ImageView) findViewById(R.id.imageView4);

        seguidores = (TextView) findViewById(R.id.seguidores);

        nomeuser = (TextView) findViewById(R.id.nomeUsuario);
        infouser = (TextView) findViewById(R.id.infoUser);
        seguindo = (TextView) findViewById(R.id.seguindo);
        eventos = (TextView) findViewById(R.id.eventos);

        q1 = (TextView) findViewById(R.id.qt1);
        q2 = (TextView) findViewById(R.id.qt2);
        q3 = (TextView) findViewById(R.id.qt3);

        q1.setText("" + seg);
        q2.setText(""+ segS);
        q3.setText(""+ evts);



        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new LineAdapter(new ArrayList<>(0));
        recyclerView.setAdapter(mAdapter);

        editarP = (Button) findViewById(R.id.button);

        floatingActionButton.setOnClickListener(view -> insertItem());

        editarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, EditarPerfil.class);
                startActivity(intent);
                finish();
            }
        });


        String n = getIntent().getStringExtra("nome");
        String i = getIntent().getStringExtra("info");

        //nesse trecho os componentes visuais não estão mudando
        new Thread(new Runnable() {




            @Override
            public void run() {
                if (n.equals("null") || n == null || i.equals("null") || i == null) {
                    nomeuser.getText();
                    infouser.getText();
                } else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nomeuser.setText(n);
                            infouser.setText(i);
                        }
                    });
                }
            }

        });

        nomeuser.setText(n);
        infouser.setText(i);

    }
    public void insertItem() {
        Evento model = new Evento();
        model.setNome("Futebol");
        model.setLocal("Barrocao");
        model.setData("10/11/19");
        model.setHora("19:00");
        mAdapter.insertItem(model);
    }

    public void addFoto(){

    }

}
