package com.andresduque01.uniremington;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import static com.andresduque01.uniremington.RegistrarActivity.db;


public class ActualizarActivity extends AppCompatActivity {

    private Button cancelar;
    private Button actualizar;
    private EditText nombres;
    private EditText apellidos;
    private EditText numero;
    private EditText nacimiento;
    private EditText eps;
    private EditText enfermedades;
    private EditText departamento;
    private EditText ciudad;
    private EditText direccion;
    private EditText ncontacto;
    private EditText tcontacto;
    private Spinner tipodocumento;
    private Spinner  tiposangre;
    private Spinner  rh;
    private String[] spiner= {"","Cédula de ciudadania","","Cédula de extranjeria","","Pasaporte","","Tarjeta de identidad",""};
    private String[] spiner1 = {"","A","","O","","AB","","B",""};
    private String[] spiner2 = {"","+","","-",""};
    private Bundle objeto;
    private Referencia referencia;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actualizar = (Button) findViewById(R.id.actualizar);
        cancelar = (Button) findViewById(R.id.cancelar);
        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        numero = (EditText) findViewById(R.id.numero);
        nacimiento = (EditText) findViewById(R.id.nacimiento);
        eps = (EditText) findViewById(R.id.eps);
        enfermedades = (EditText) findViewById(R.id.enfermedades);
        departamento = (EditText) findViewById(R.id.departamento);
        ciudad = (EditText) findViewById(R.id.ciudad);
        direccion = (EditText) findViewById(R.id.direccion);
        ncontacto = (EditText) findViewById(R.id.ncontacto);
        tcontacto = (EditText) findViewById(R.id.tcontacto);
        tipodocumento = (Spinner) findViewById(R.id.spinner);
        tiposangre = (Spinner) findViewById(R.id.spinner2);
        rh = (Spinner) findViewById(R.id.spinner3);
        tipodocumento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner));
        tiposangre.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner1));
        rh.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner2));

        objeto = getIntent().getExtras();
        referencia = (Referencia)objeto.getSerializable("correo");


        mostrarDatos();



        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference persona = db.collection("ciclistas").document(referencia.getCorreo());
                persona
                        .update("nombres", nombres.getText().toString(),
                                "apellidos",apellidos.getText().toString(),
                                "tipo_documento", tipodocumento.getSelectedItem().toString(),
                                "cedula", numero.getText().toString(),
                                "fecha_nacimiento", nacimiento.getText().toString(),
                                "tipo_sangre", tiposangre.getSelectedItem().toString(),
                                "rh", rh.getSelectedItem().toString(),
                                "eps", eps.getText().toString(),
                                "enfermedades", enfermedades.getText().toString(),
                                "departamento", departamento.getText().toString(),
                                "ciudad", ciudad.getText().toString(),
                                "direccion", direccion.getText().toString(),
                                "nombre_contacto", ncontacto.getText().toString(),
                                "numero_contacto", tcontacto.getText().toString()
                                )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ActualizarActivity.this, "Datos actulizados exitosamente",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ActualizarActivity.this, "Los datos no fueron actualizados",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActualizarActivity.this, MenuActivity.class);
                finish();
                startActivity(i);

            }
        });



    }



    public void mostrarDatos(){
        DocumentReference persona = db.collection("ciclistas").document(referencia.getCorreo());
        persona.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Persona persona = documentSnapshot.toObject(Persona.class);
                nombres.setText(persona.getNombres());
                apellidos.setText(persona.getApellidos());
                numero.setText(persona.getcedula());
                nacimiento.setText(persona.getFecha_nacimiento());
                eps.setText(persona.getEps());
                enfermedades.setText(persona.getEnfermedades());
                departamento.setText(persona.getDepartamento());
                ciudad.setText(persona.getCiudad());
                direccion.setText(persona.getDireccion());
                ncontacto.setText(persona.getNombre_contacto());
                tcontacto.setText(persona.getNumero_contacto());
                datos(persona.gettipo_documento(), persona.getTipo_sangre(), persona.getRh());

            }
        });

    }

    public void datos(String documento, String sangre, String Rh){
        for(int i=0; i<spiner.length;i++){
            if (spiner[i].equals(documento)) {
                tipodocumento.setSelection(i);

            }
        }
        for(int i=0; i<spiner1.length;i++){
            if (spiner1[i].equals(sangre)) {
                tiposangre.setSelection(i);
            }
        }
        for(int i=0; i<spiner2.length;i++){
            if (spiner2[i].equals(Rh)) {
                rh.setSelection(i);
            }
        }

    }


}
