package com.example.syme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetearContrasenia extends AppCompatActivity {

    private EditText mEditEmailRe;
    private Button RestablecerButton;
    private ProgressDialog mDialog;
    String correoR="";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_resetear_contrasenia);
        mEditEmailRe = (EditText) findViewById(R.id.mEmailRes);
        RestablecerButton = (Button) findViewById(R.id.restablecerBoton);

        RestablecerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correoR = mEditEmailRe.getText().toString();
                if(!correoR.isEmpty()){
                    mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mDialog.setMessage("Espera un momento...");
                    mDialog.show();
                    mDialog.setCanceledOnTouchOutside(false);

                    resetPass();
                } else{
                    Toast.makeText(ResetearContrasenia.this,"Debe ingresar un correo",Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });
    }
    private void resetPass(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correoR).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetearContrasenia.this,"Se ha enviado un correo para restablcer su contraseña",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetearContrasenia.this,"No se pudo enviar el correo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}