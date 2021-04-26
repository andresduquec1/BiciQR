package com.andresduque01.uniremington;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Button ingresar;
    private Button crearCuenta;
    private EditText correo;
    private EditText contrasena;
    private Sesion sesion;
    private Referencia referencia;
    private int ingreso;
    private TextView restablecer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = (Button) findViewById(R.id.ingresar);
        crearCuenta = (Button) findViewById(R.id.crearCuenta);
        correo = (EditText) findViewById(R.id.correo);
        contrasena = (EditText) findViewById(R.id.contrasena);
        restablecer = (TextView)findViewById(R.id.restablecer);




        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(correo.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Debe ingresar un correo",Toast.LENGTH_LONG).show();
                }else if(contrasena.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Debe ingresar una contraseña",Toast.LENGTH_LONG).show();
                }else
                ingresar(correo.getText().toString().toLowerCase().trim(),contrasena.getText().toString().toLowerCase().trim());
                ingreso++;
                if(ingreso>=1) {
                    ingreso = contador();
                }

            }
        });

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(i);
            }
        });

        restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RestablecerActivity.class);
                startActivity(i);
               /* FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = "andres.duque01@gmail.com";

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Se a enviado correo electrónico",Toast.LENGTH_LONG).show();
                                }
                            }
                        });*/

            }
        });


        inicialize();
    }

    private void inicialize(){
        firebaseAuth = firebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Log.w(TAG, "onAuthStateChanged - signed_in"+ firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged - signed_in"+ firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
                }
            }
        };

    }

    private void ingresar( String correo, String contrasena){
        sesion = new Sesion(correo,contrasena);
        referencia = new Referencia(correo);
        firebaseAuth.getInstance().signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                 //   Toast.makeText(LoginActivity.this, "Ingreso exitoso",Toast.LENGTH_LONG).show();
                    if(ingreso==1){
                        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("correo",referencia);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Correo o contraseña invalidos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void openBrowser(View view){

        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

    public int contador(){
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ingreso=0;
            }
        }.start();
        return ingreso;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
