package com.example.syme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Ventas extends AppCompatActivity {
    ArrayList<VentasP> listaVentas;
    RecyclerView recyclerVentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        recyclerVentas = findViewById(R.id.rvVentas);
        recyclerVentas.setLayoutManager(new LinearLayoutManager(this));
        listaVentas = new ArrayList<>();

        rellenarVentas();
        VentasAdapter adapter = new VentasAdapter(listaVentas);
        recyclerVentas.setAdapter(adapter);
    }

    private void rellenarVentas() {
        listaVentas.add(new VentasP("Productos de limpieza para equipos de computo","Toallitas limpiadoras"));
        listaVentas.add(new VentasP("","Espuma limpiadora "));
        listaVentas.add(new VentasP("","Alcohol isopropílico "));
        listaVentas.add(new VentasP("","Aire comprimido"));

        listaVentas.add(new VentasP("Accesorios","Mouse "));
        listaVentas.add(new VentasP("","Teclado"));
        listaVentas.add(new VentasP("","Audífonos"));
        listaVentas.add(new VentasP("","Bocinas"));
        listaVentas.add(new VentasP("","Mouse Pad"));
        listaVentas.add(new VentasP("","Cámaras"));

        listaVentas.add(new VentasP("Cotizaciones de piezas de equipo de computo","Bateria"));
        listaVentas.add(new VentasP("","Teclados"));
        listaVentas.add(new VentasP("","Pantallas"));
        listaVentas.add(new VentasP("","Cargadores"));

        listaVentas.add(new VentasP("Equipos de seguridad (cámaras)","Cámaras 4 canales"));
        listaVentas.add(new VentasP("","Cámaras 8 canales"));

        listaVentas.add(new VentasP("Equipos de cómputo y de oficina","Laptops"));
        listaVentas.add(new VentasP("","Impresoras"));

        listaVentas.add(new VentasP("Papelería en general","Lápices"));
        listaVentas.add(new VentasP("","Carpetas"));
        listaVentas.add(new VentasP("","Lapicero"));
        listaVentas.add(new VentasP("","Borradores"));
        listaVentas.add(new VentasP("","Clips"));
        listaVentas.add(new VentasP("","Hojas blancas"));

        listaVentas.add(new VentasP("Memorias (USB y micro SD)","USB"));
        listaVentas.add(new VentasP("","Micro SD"));
    }
}