package com.andresduque01.uniremington;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;


public class MenuActivity extends AppCompatActivity {

    private LinearLayout generador;
    private LinearLayout escanear;
    private LinearLayout actualizar;
    private LinearLayout configurar;
    private LinearLayout salir;
    private Referencia referencia;
    private Bundle objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generador = (LinearLayout)findViewById(R.id.generador);
        escanear = (LinearLayout)findViewById(R.id.escanear);
        actualizar = (LinearLayout)findViewById(R.id.actualizar);
        configurar = (LinearLayout)findViewById(R.id.configurar);
        salir = (LinearLayout)findViewById(R.id.salir);

        objeto = getIntent().getExtras();
        referencia = (Referencia)objeto.getSerializable("correo");


        generador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, GenerarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("correo",referencia);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, EscanearActivity.class);
                startActivity(i);
            }
        });


        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, ActualizarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("correo",referencia);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        configurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MenuActivity.this, ConfigurarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("correo",referencia);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
