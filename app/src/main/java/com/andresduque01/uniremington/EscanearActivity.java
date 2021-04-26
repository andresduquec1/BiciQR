package com.andresduque01.uniremington;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.andresduque01.uniremington.RegistrarActivity.db;

public class EscanearActivity extends AppCompatActivity {

    private Button escanear;
    private TextView texto;
    private TextView texto2;
    private TextView texto3;
    private TextView texto4;
    private TextView texto5;
    private TextView texto6;
    private TextView texto7;
    private TextView texto8;
    private TextView texto9;
    private TextView texto10;
    private TextView texto11;
    private TextView texto12;
    private TextView texto13;
    private TextView texto14;
    private TextView texto15;
    private String  resultado;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main5);

        escanear = findViewById(R.id.escanear);
        texto = findViewById(R.id.texto);
        texto2 = findViewById(R.id.texto2);
        texto3 = findViewById(R.id.texto3);
        texto4 = findViewById(R.id.texto4);
        texto5 = findViewById(R.id.texto5);
        texto6 = findViewById(R.id.texto6);
        texto7 = findViewById(R.id.texto7);
        texto8 = findViewById(R.id.texto8);
        texto9 = findViewById(R.id.texto9);
        texto10 = findViewById(R.id.texto10);
        texto11 = findViewById(R.id.texto11);
        texto12 = findViewById(R.id.texto12);
        texto13 = findViewById(R.id.texto13);
        texto14 = findViewById(R.id.texto14);
        texto15 = findViewById(R.id.texto15);

        escanear.setOnClickListener(mOnClickListener);
        new IntentIntegrator(EscanearActivity.this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null) {
                resultado = result.getContents();
                mostrarDatos(resultado.toString());
            }else{
                texto.setText("Error al escanear el código QR");
            }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.escanear:
                    new IntentIntegrator(EscanearActivity.this).initiateScan();
                    break;
            }
        }
    };

    public void mostrarDatos(String resultado){
        DocumentReference docRef = db.collection("ciclistas").document(resultado);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Persona persona = documentSnapshot.toObject(Persona.class);
                texto2.setText(persona.getNombres());
                texto3.setText(persona.getApellidos());
                texto4.setText(persona.gettipo_documento());
                texto5.setText(persona.getcedula());
                texto6.setText(persona.getFecha_nacimiento());
                texto7.setText(persona.getTipo_sangre());
                texto8.setText(persona.getRh());
                texto9.setText(persona.getEps());
                texto10.setText(persona.getEnfermedades());
                texto11.setText(persona.getDepartamento());
                texto12.setText(persona.getCiudad());
                texto13.setText(persona.getDireccion());
                texto14.setText(persona.getNombre_contacto());
                texto15.setText(persona.getNumero_contacto());

            }
        });

    }


}
