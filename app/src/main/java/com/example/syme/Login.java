package com.example.syme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText correo,contra;
    String email,password;
    Button registrarse,iniciar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correoL);
        contra = findViewById(R.id.ContraseñaL);


        iniciar = findViewById(R.id.btnIniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = correo.getText().toString();
                password = contra.getText().toString();

                if (email.isEmpty()){
                    correo.setError("Ingrese un correo electronico valido");
                }else if (password.isEmpty()){
                    contra.setError("Ingrese su contraseña");
                }else{
                    IniciarSesion();
                }

            }
        });
        registrarse=findViewById(R.id.btnRegistrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registrarse.class);
                startActivity(intent);
            }


        });
    }
    private void IniciarSesion(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Login.this,"No se pudo iniciar sesión",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}