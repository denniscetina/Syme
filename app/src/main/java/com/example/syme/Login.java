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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button registrarse,iniciar;
    TextInputEditText mEmail, mContrasenia;
    private String email="",contrasenia="";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciar=findViewById(R.id.iniciaSes);
        mEmail=findViewById(R.id.mEmailEdit);
        mContrasenia=findViewById(R.id.mContraEdit);
        mAuth = FirebaseAuth.getInstance();

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loginUser();
                email= mEmail.getText().toString();
                contrasenia= mContrasenia.getText().toString();
                if(!email.isEmpty() && !contrasenia.isEmpty()){

                    loginUser();
                }
            }
        });

    }

    private void loginUser() {

    mAuth.signInWithEmailAndPassword(email, contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
         if(task.isSuccessful()){
             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
             startActivity(intent);
             finish();

         }
         else{
             Toast.makeText(Login.this,"No se pudo inicar sesión comprueba los datos.",Toast.LENGTH_LONG).show();
         }
        }
    });
    }

    public void SesionInicio(View view) {
        email= mEmail.getText().toString();
        contrasenia= mContrasenia.getText().toString();
        if(email.isEmpty() && contrasenia.isEmpty()){

            loginUser();
        }
    }
}