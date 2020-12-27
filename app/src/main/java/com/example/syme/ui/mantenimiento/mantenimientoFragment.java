package com.example.syme.ui.mantenimiento;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.syme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mantenimientoFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    FirebaseFirestore db;
    private Spinner spinner;
    private EditText est, tip;
    private String estado="", tipo="";
    private Button subir;
    String id="",idCliente="";
    private int hijos=0,contador=1;
    private String nombreSeleccionado="",numero="";
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList2 = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mantenimiento, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();
        spinner = root.findViewById(R.id.nombreCliente);
        est = root.findViewById(R.id.estado);
        tip = root.findViewById(R.id.tipo);
        id = mAuth.getCurrentUser().getUid();
        mDataBase.child("Mantenimiento").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hijos = (int) snapshot.getChildrenCount();
                for (int i = 0; contador < hijos; i++) {
                    contador++;
                }
                numero = String.valueOf(contador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nombreSeleccionado = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        subir = root.findViewById(R.id.btnSubir);
        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirDatos();
            }
        });
        mostrarDatosSpinner();
        return root;
    }


    private void mostrarDatosSpinner() {
        mDataBase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    arrayList.add(dataSnapshot.child("Nombre").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.style_spinner,arrayList);
                spinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void subirDatos() {
        estado = est.getText().toString();
        tipo = tip.getText().toString();
        if (estado.isEmpty()){
            est.setError("Todos los datos son obligatorios: Ingrese el estado del dispositivo");
        }else if (tipo.isEmpty()){
            tip.setError("Todos los datos son obligatorios: Ingrese el tipo de dispositivo");
        }else {
            db.collection("Usuarios").whereEqualTo("Nombre",nombreSeleccionado).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshot:task.getResult()){
                                idCliente= documentSnapshot.getData().get("Id").toString();
                        }
                        //Aqui se hace el conteo de cuantos dispositivos tiene el usuario

                        Map<String, Object> map = new HashMap<>();
                        map.put("Estado",estado);
                        map.put("Tipo",tipo);

//Aqui se crea el nuevo dispositivo
                        mDataBase.child("Mantenimiento").child(idCliente).child("Dispositivo"+numero).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    est.setText("");
                                    tip.setText("");
                                    Log.e("Datos ", numero);
                                }else {
                                    Toast.makeText(getContext(),"Hubo un error al intentar subir los datos",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });

        }
    }
}