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
    private DatabaseReference mDataBase;
    Button registrarse, iniciar;
    TextInputEditText mEmail, mContrasenia;
    private String email = "", contrasenia = "";
    private FirebaseAuth mAuth;
    Switch mantenerSesion;
    private static final String STRING_PREFERENCES = "usuarioGuardado";
    private static final String STRING_PREFERENCES_SWITCH = "switchGuardado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciar = findViewById(R.id.iniciaSes);
        mEmail = findViewById(R.id.mEmailEdit);
        mContrasenia = findViewById(R.id.mContraEdit);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // loginUser();

                email = mEmail.getText().toString();
                contrasenia = mContrasenia.getText().toString();
                if (!email.isEmpty() && !contrasenia.isEmpty()) {
                    FirebaseMessaging.getInstance().getToken()
                            .addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                    if (!task.isSuccessful()) {
                                        Log.w("cd", "Fetching FCM registration token failed", task.getException());
                                        return;
                                    }

                                    // Get new FCM registration token
                                    String token = task.getResult();

                                    // Log and toast

                                    Toast.makeText(Login.this, token, Toast.LENGTH_SHORT).show();
                                    Log.d("Impresion", token);
                                }
                            });
                    loginUser();
                }
            }
        });

    }

    private void loginUser() {

        mAuth.signInWithEmailAndPassword(email, contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    obtenerUsuariop();
                    finish();

                } else {
                    Toast.makeText(Login.this, "No se pudo inicar sesión comprueba los datos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

            startActivity(new Intent(Login.this, MainActivity.class));
            obtenerUsuariop();
            finish();

        }


    }
    public void obtenerUsuariop(){
        String id= mAuth.getCurrentUser().getUid();
        mDataBase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("Nombre").getValue().toString();
                    String correo = snapshot.child("Correo").getValue().toString();
                    Toast.makeText(Login.this, name+"   "+correo, Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
