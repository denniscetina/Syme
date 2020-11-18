package com.example.syme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {
    EditText tvnombre,tvdireccion,tvubicacion,tvtelefono,tvusuario,tvcontra,tvcontraC;
    Button registrar;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    ProgressDialog progressDialog;

    String nombre = "";
    String direccion = "";
    String ubicacion = "";
    String telefono = "";
    String usuario = "";
    String contra = "";
    String contraC = "";
    String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        progressDialog  = new ProgressDialog(this);

        tvnombre=findViewById(R.id.Nombre);
        tvdireccion=findViewById(R.id.Direccion);
        tvubicacion=findViewById(R.id.Ubicacion);
        tvtelefono=findViewById(R.id.Telefono);
        tvusuario=findViewById(R.id.UsuarioR);
        tvcontra=findViewById(R.id.ContraseñaR);
        tvcontraC=findViewById(R.id.ContraseñaC);
        registrar=findViewById(R.id.Registrar);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=tvnombre.getText().toString();
                direccion=tvdireccion.getText().toString();
                ubicacion=tvubicacion.getText().toString();
                telefono=tvtelefono.getText().toString();
                usuario=tvusuario.getText().toString();
                contra=tvcontra.getText().toString();
                contraC=tvcontraC.getText().toString();
                Registrar();
            }
        });
    }

    private void Registrar() {
        if (nombre.isEmpty()){
            tvnombre.setError("Todos los datos son obligatorios: Ingrese su nombre completo");
        }else if (direccion.isEmpty()){
            tvdireccion.setError("Todos los datos son obligatorios: Ingrese su dirección");
        }else if (ubicacion.isEmpty()){
            tvubicacion.setError("Todos los datos son obligatorios: Ingrese su municipio o ciudad");
        }else if (telefono.isEmpty()){
            tvtelefono.setError("Todos los datos son obligatorios: Ingrese su numero telefonico");
        }else if (usuario.isEmpty()){
            tvusuario.setError("Todos los datos son obligatorios: Ingrese su correo electronico");
        }else if (contra.isEmpty()){
            tvcontra.setError("Todos los datos son obligatorios: Ingrese su contraseña");
        }else if (contra.length() < 6){
            tvdireccion.setError("La contraseña debe tener al menos 6 caracteres");
        }else if (contraC.isEmpty()){
            tvcontraC.setError("Todos los datos son obligatorios: Confirme su contraseña");
        }else{
            enviarUsuario();
        }
    }

    private void enviarUsuario() {
        mAuth.createUserWithEmailAndPassword(usuario,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.show();
                    Map<String, Object> map = new HashMap<>();
                    map.put("Nombre",nombre);
                    map.put("Dirección",direccion);
                    map.put("Ubicación",ubicacion);
                    map.put("Telefono",telefono);
                    map.put("Correo",usuario);
                    map.put("Contraseña",contra);
                    String id = mAuth.getCurrentUser().getUid();
                    mDataBase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                progressDialog.dismiss();
                                startActivity(new Intent(Registrarse.this,Login.class));
                                finish();
                            }else{
                                Toast.makeText(Registrarse.this,"No se pudo registrar el usuario",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}