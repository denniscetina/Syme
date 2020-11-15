package com.example.syme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {
    EditText tvnombre,tvdireccion,tvubicacion,tvtelefono,tvusuario,tvcontra,tvcontraC;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        tvnombre=findViewById(R.id.Nombre);
        tvdireccion=findViewById(R.id.Direccion);
        tvubicacion=findViewById(R.id.Ubicacion);
        tvtelefono=findViewById(R.id.Telefono);
        tvusuario=findViewById(R.id.UsuarioR);
        tvcontra=findViewById(R.id.ContraseñaR);
        tvcontraC=findViewById(R.id.ContraseñaC);
        registrar=findViewById(R.id.Registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar();
            }
        });
    }

    private void Registrar() {
        String nombre = tvnombre.getText().toString().trim();
        String direccion = tvdireccion.getText().toString().trim();
        String ubicacion = tvubicacion.getText().toString().trim();
        String telefono = tvtelefono.getText().toString().trim();
        String usuario = tvusuario.getText().toString().trim();
        String contra = tvcontra.getText().toString().trim();
        String contraC = tvcontraC.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nombre.isEmpty()){
            tvnombre.setError("Todos los campos son obligatorios: Inserte su nombre completos");
        }else if (direccion.isEmpty()){
            tvdireccion.setError("Todos los campos son obligatorios: Inserte su dirección");
        }else if (ubicacion.isEmpty()){
            tvubicacion.setError("Todos los campos son obligatorios: Inserte su ubicación");
        }else if (telefono.isEmpty()){
            tvtelefono.setError("Todos los campos son obligatorios: Inserte su numero telefonico");
        }else if (usuario.isEmpty()){
            tvusuario.setError("Todos los campos son obligatorios: Inserte su usuario");
        }else if (contra.isEmpty()){
            tvcontra.setError("Todos los campos son obligatorios: Inserte su dirección");
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://syme.000webhostapp.com/PHP/Registrarse.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos insertados")) {
                        Toast.makeText(Registrarse.this, "Datos ingresados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(Registrarse.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Registrarse.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String>params=new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("direccion",direccion);
                    params.put("ubicacion",ubicacion);
                    params.put("telefono",telefono);
                    params.put("usuario",usuario);
                    params.put("contra",contra);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Registrarse.this);
            requestQueue.add(request);
        }
    }
}