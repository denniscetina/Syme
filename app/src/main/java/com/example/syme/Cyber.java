package com.example.syme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Cyber extends AppCompatActivity {
    List<Servicios> listaServicios;
    RecyclerView recyclerServicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber);

        listaServicios = new ArrayList<>();
        recyclerServicios = findViewById(R.id.rvCyber);
        recyclerServicios.setLayoutManager(new LinearLayoutManager(this));

        llenarServicios();

        ServiciosAdapter adapter = new ServiciosAdapter(listaServicios);
        recyclerServicios.setAdapter(adapter);
    }

    private void llenarServicios() {
        listaServicios.add(new Servicios("Impresión de curp"));
        listaServicios.add(new Servicios("Impresión de buró de crédito"));
        listaServicios.add(new Servicios("Impresión de número social"));

        listaServicios.add(new Servicios("Impresión de licencia de funcionamiento"));
        listaServicios.add(new Servicios("Elaboración de oficios"));
        listaServicios.add(new Servicios("Edición de videos"));

        listaServicios.add(new Servicios("Edición de música"));
        listaServicios.add(new Servicios("Elaboración de invitaciones"));
        listaServicios.add(new Servicios("Impresiones "));

        listaServicios.add(new Servicios("Copias"));
        listaServicios.add(new Servicios("Escaneos"));
        listaServicios.add(new Servicios("Envíos de correo"));
        listaServicios.add(new Servicios("Instalación de cámaras"));
    }
}