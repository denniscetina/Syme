package com.example.syme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button registrarse,iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciar = findViewById(R.id.btnIniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
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
}