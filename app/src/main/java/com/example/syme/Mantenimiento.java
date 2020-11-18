package com.example.syme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Mantenimiento extends AppCompatActivity {
    ArrayList<Paquetes> listaPaquetes;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento);
        listaPaquetes = new ArrayList<>();
        recyclerView = findViewById(R.id.rvPaquetes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llenarPaquetes();

        PaquetesAdapter adapter = new PaquetesAdapter(listaPaquetes);
        recyclerView.setAdapter(adapter);
    }

    private void llenarPaquetes() {
        listaPaquetes.add(new Paquetes("MANTENIMIENTO DE COMPUTADORAS","",""));
        listaPaquetes.add(new Paquetes("Paquete 1:","Configuración inicial que incluye:"
                ,"•\tInstalación de antivirus\n" +
                "•\tInstalación de la paquetería office 2013-2019\n" +
                "•\tInstalación de programas y complementos básicos \n"));

        listaPaquetes.add(new Paquetes("Paquete 2:","Mantenimiento preventivo, que contiene:"
                ,"•\tRespaldo de información \n" +
                "•\tInstalación del sistema operativo (Win7-Win10)\n" +
                "•\tInstalación del paquete 1\n"));
        listaPaquetes.add(new Paquetes("Paquete 3","Mantenimiento correctivo, que contiene:"
                ,"•\tLimpieza interna del equipo de computo\n" +
                "•\tRespaldo por medio del disco duro\n" +
                "•\tInstalación del paquete 2\n"));
        listaPaquetes.add(new Paquetes("MANTENIMIENTO DE IMPRESORAS","",""));

        listaPaquetes.add(new Paquetes("","","Limpieza en general"));
        listaPaquetes.add(new Paquetes("","","Limpieza de almohadillas"));
        listaPaquetes.add(new Paquetes("","","Limpieza de cabezales "));
        listaPaquetes.add(new Paquetes("","","Rellenado de tintas (Cartuchos)"));
        listaPaquetes.add(new Paquetes("","","Rellenado de tintas (Impresoras)"));
        listaPaquetes.add(new Paquetes("","","Purgado de impresoras"));
    }
}