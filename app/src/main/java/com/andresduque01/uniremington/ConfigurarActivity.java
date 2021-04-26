package com.andresduque01.uniremington;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfigurarActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText correo;
    private EditText contrasena;
    private Button guardar;
    private Button guardarcorreo;
    private Referencia referencia;
    private Bundle objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        correo = (EditText)findViewById(R.id.correo);
        contrasena = (EditText)findViewById(R.id.contrasena);
        guardar = (Button)findViewById(R.id.guardar);
        guardarcorreo = (Button)findViewById(R.id.guardarcorreo);

        objeto = getIntent().getExtras();
        referencia = (Referencia)objeto.getSerializable("correo");

        guardarcorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String correo = referencia.getCorreo();

                user.updateEmail(correo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ConfigurarActivity.this, "Correo electrónico actualizado con exito",Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String newPassword = "12345a";

                user.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ConfigurarActivity.this, "Contraseña actualizada con exito",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }
}
