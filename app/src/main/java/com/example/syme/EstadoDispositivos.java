package com.example.syme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EstadoDispositivos extends AppCompatActivity {
    List<Dispositivos> dispositivos;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    DatabaseReference reference;
    DispositivosAdapter adapter;
    FirebaseAuth mAuth;
    TextView sinDes;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);
        recyclerView = findViewById(R.id.rvDispositivos);
        sinDes = findViewById(R.id.sinDispositivos);
        mAuth = FirebaseAuth.getInstance();
        id = mAuth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dispositivos = new ArrayList<>();

        reference.child("Usuarios").child(id).child("Dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equalsIgnoreCase("false")){
                    if (sinDes.getVisibility()==View.GONE){
                        sinDes.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }else {
                    if (sinDes.getVisibility()==View.VISIBLE){
                        sinDes.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    listarDispositivos();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void listarDispositivos() {

        reference.child("Mantenimiento").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Dispositivos d = snapshot.getValue(Dispositivos.class);
                    dispositivos.add(d);
                }
                adapter = new DispositivosAdapter(getApplicationContext(),dispositivos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}