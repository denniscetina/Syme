package com.example.syme.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syme.Producto;
import com.example.syme.ProductoAdapter;
import com.example.syme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener{

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewProductos;
    DatabaseReference reference;
    ArrayList<Producto> productos;
    ProductoAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        recyclerViewProductos=root.findViewById(R.id.rvProductos);



        reference = FirebaseDatabase.getInstance().getReference().child("Productos");

        recyclerViewProductos.setLayoutManager(new GridLayoutManager(getActivity(),2));
        productos = new ArrayList<Producto>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Producto p = snapshot.getValue(Producto.class);
                    productos.add(p);
                }
                adapter = new ProductoAdapter(getContext(), productos);
                recyclerViewProductos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getActivity().getMenuInflater().inflate(R.menu.main,menu);
        MenuItem item =menu.findItem(R.id.buscar);
        SearchView searchbtn =(SearchView) MenuItemCompat.getActionView(item);
        searchbtn.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.setFilter(productos);
                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {
            ArrayList<Producto>listaFiltra=filter(productos,s);
            adapter.setFilter(listaFiltra);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private ArrayList<Producto> filter(ArrayList<Producto> producto,String texto){
        ArrayList<Producto>listaFiltrada=null;
        try {
            texto=texto.toLowerCase();
            for (Producto pdt:producto){
                String pdt2=pdt.getNombreProductos().toLowerCase();
                if (pdt2.contains(texto)){
                    listaFiltrada.add(pdt);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaFiltrada;
    }
}