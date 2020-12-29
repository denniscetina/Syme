package com.example.syme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity {
    Button registrarse,iniciar;
    private DatabaseReference mDataBase;
    EditText mEmail, mContrasenia;
    //
    private String email="",contrasenia="";
    private FirebaseAuth mAuth;
    private TextView olvidoContra;
    Switch mantenerSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciar=findViewById(R.id.iniciaSes);
        mEmail=findViewById(R.id.mEmailEdit);
        mContrasenia=findViewById(R.id.mContraEdit);
        registrarse=findViewById(R.id.registrarseL);
        olvidoContra = (TextView)findViewById(R.id.textViewReset);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Registrarse.class));
            }
        });
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= mEmail.getText().toString();
                contrasenia= mContrasenia.getText().toString();
                if(email.isEmpty()){
                    mEmail.setError("Ingrese su email");
                }else if (contrasenia.isEmpty()){
                    mContrasenia.setError("Ingrese su contraseña");
                }else {
                    loginUser();
                }
            }
        });

        olvidoContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ResetearContrasenia.class));
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
             obtenerUsuariop();
             finish();

         }
         else{
             Toast.makeText(Login.this,"No se pudo inicar sesión compruebe los datos.",Toast.LENGTH_LONG).show();
         }
        }
    });
    }


    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this,MainActivity.class));
            obtenerUsuariop();
            finish();
        }
    }
    public void obtenerUsuariop() {
        String id = mAuth.getCurrentUser().getUid();
        mDataBase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("Nombre").getValue().toString();
                    String correo = snapshot.child("Correo").getValue().toString();
                    Toast.makeText(Login.this, name + "   " + correo, Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
