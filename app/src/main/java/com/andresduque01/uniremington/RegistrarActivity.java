package com.andresduque01.uniremington;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;

public class RegistrarActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "RegistrarActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();



    private Button cancelar;
    private Button registrarse;
    private EditText correo;
    private EditText contrasena;
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
    private Spinner  tipodocumento;
    private Spinner  tiposangre;
    private Spinner  rh;
    private CheckBox checkbox;
    private int dia, mes, anio, dias2, meses, anios;
    private String Sfecha, Sdias2, Smeses, Sanios, cero;
    private String[] spiner= {"","Cédula de ciudadania","","Cédula de extranjeria","","Pasaporte","","Tarjeta de identidad",""};
    private String[] spiner1 = {"","A","","O","","AB","","B",""};
    private String[] spiner2 = {"","+","","-",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        registrarse = (Button) findViewById(R.id.guardar);
        cancelar = (Button) findViewById(R.id.cancelar);
        correo = (EditText) findViewById(R.id.correo);
        contrasena = (EditText) findViewById(R.id.contrasena);
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
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        nacimiento.setOnClickListener(this);
        tipodocumento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner));
        tiposangre.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner1));
        rh.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiner2));


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(correo.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar un correo",Toast.LENGTH_LONG).show();
                }else if(contrasena.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar una contraseña",Toast.LENGTH_LONG).show();
                }else if(nombres.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar su nombre",Toast.LENGTH_LONG).show();
                }else if(apellidos.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar sus apellidos",Toast.LENGTH_LONG).show();
                }else if(tipodocumento.getSelectedItem().equals("")){
                    Toast.makeText(RegistrarActivity.this, "Debe seleccionar un tipo de documento",Toast.LENGTH_LONG).show();
                }else if(numero.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar un número de documento",Toast.LENGTH_LONG).show();
                }else if(nacimiento.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe seleccionar su fecha de nacimiento",Toast.LENGTH_LONG).show();
                }else if(tiposangre.getSelectedItem().equals("")){
                    Toast.makeText(RegistrarActivity.this, "Debe seleccionar su grupo sanquíneo",Toast.LENGTH_LONG).show();
                }else if(rh.getSelectedItem().equals("")){
                    Toast.makeText(RegistrarActivity.this, "Debe seleccionar su RH",Toast.LENGTH_LONG).show();
                }else if(eps.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar su eps",Toast.LENGTH_LONG).show();
                }else if(enfermedades.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Si no aplica escriba N/A",Toast.LENGTH_LONG).show();
                }else if(departamento.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar un departamento",Toast.LENGTH_LONG).show();
                }else if(ciudad.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar una ciudad",Toast.LENGTH_LONG).show();
                }else if(direccion.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar una dirección",Toast.LENGTH_LONG).show();
                }else if(ncontacto.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar un nombre de contacto",Toast.LENGTH_LONG).show();
                }else if(tcontacto.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarActivity.this, "Debe ingresar el teléfono de un contacto",Toast.LENGTH_LONG).show();
                }else if(!checkbox.isChecked()) {
                    Toast.makeText(RegistrarActivity.this, "Debe aceptar los terminos y condiciones",Toast.LENGTH_LONG).show();
                }else{

                    registrarse(correo.getText().toString(), contrasena.getText().toString());

                    }
                }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

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



    public void  guardarPersona(String correo, String nombre, String apellido, String tipodocumento,
                                String numero, String fecha_nacimiento, String tipo_sangre, String _rh,
                                String eps, String enfermadades, String departamento, String ciudad,
                                String direccion, String nombre_contacto, String telefono_contacto) {

        Persona persona = new Persona(correo, nombre,apellido, tipodocumento, numero,
                              fecha_nacimiento, tipo_sangre, _rh, eps, enfermadades,departamento,
                              ciudad, direccion, nombre_contacto, telefono_contacto);


       /* db.collection("cilcistas")
                .add(persona)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });*/

        db.collection("ciclistas").document(correo)
                .set(persona)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }


    public void enviarDatos(){

        guardarPersona(correo.getText().toString().toLowerCase().trim(), nombres.getText().toString().toLowerCase().trim(), apellidos.getText().toString().toLowerCase().trim(),
                tipodocumento.getSelectedItem().toString(), numero.getText().toString().trim(), Sfecha,
                tiposangre.getSelectedItem().toString(), rh.getSelectedItem().toString(), eps.getText().toString().toLowerCase().trim(),
                enfermedades.getText().toString().toLowerCase().trim(), departamento.getText().toString().toLowerCase().trim(),
                ciudad.getText().toString().toLowerCase().trim(), direccion.getText().toString().toLowerCase().trim(), ncontacto.getText().toString().toLowerCase().trim(),
                tcontacto.getText().toString().trim());

    }

    private void registrarse(final String correo, String contrasena){
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!firebaseAuth.getCurrentUser().getEmail().equals(correo)) {
                    Toast.makeText(RegistrarActivity.this, "Este correo ya se encuentra en uso", Toast.LENGTH_SHORT).show();
                }else if(task.isSuccessful()){
                    enviarDatos();
                    Toast.makeText(RegistrarActivity.this, "Cuenta creada exitosamente",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(RegistrarActivity.this, "La cuenta no fue creada",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.nacimiento){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthofYear, int dayofMonth) {

                    dias2 = dayofMonth;
                    Sdias2 = String.valueOf(dayofMonth);
                    cero = "0";
                    if(dayofMonth<10){
                        Sdias2= cero+Sdias2;
                    }

                    meses = (monthofYear+1);
                    Smeses =  String.valueOf(monthofYear+1);
                    cero = "0";
                    if(monthofYear<10){
                        Smeses= cero+Smeses;
                    }

                    anios = year;
                    Sanios =  String.valueOf(year);

                    Sfecha = Sdias2+"/"+Smeses+"/"+Sanios;

                    nacimiento.setText(Sfecha);


                }
            }
                    ,anio,mes,dia);
            datePickerDialog.show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}

