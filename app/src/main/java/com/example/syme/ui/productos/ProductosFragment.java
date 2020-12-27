package com.example.syme.ui.productos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.syme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class ProductosFragment extends Fragment {
    private ImageView addImg;
    private Button subir;
    private EditText precio,nombre;
    private DatabaseReference mDataBase;
    private StorageReference mStorageRef;
    private String pre,nom,confirmancion,url;
    int hijos=0,contador=0;
    String numero="";
    ProgressDialog progressDialog;
    private static final int GALERY_INTENT=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_productos, container, false);
        addImg = root.findViewById(R.id.addImg);
        subir = root.findViewById(R.id.subirProd);
        precio = root.findViewById(R.id.precioProd);
        nombre = root.findViewById(R.id.nombreProd);

        progressDialog  = new ProgressDialog(getContext());
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDataBase.child("Productos").addValueEventListener(new ValueEventListener() {
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
        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirDatos();
            }
        });

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALERY_INTENT);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERY_INTENT && resultCode==RESULT_OK){

            Uri uri = data.getData();
            addImg.setImageURI(uri);
            StorageReference filePath = mStorageRef.child("Productos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    confirmancion="si";
                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url = uri.toString();
                            //createNewPost(imageUrl);
                        }
                    });
                }
            });
        }
    }

    private void SubirDatos(){
        nom = nombre.getText().toString();
        pre= precio.getText().toString();
        if (nom.isEmpty()){
            nombre.setError("Todos los datos son obligatorios: Ingrese nombre del producto");
        }else if (pre.isEmpty()){
            precio.setError("Todos los datos son obligatorios: Ingrese precio del producto");
        }else if (confirmancion != "si"){
            Toast.makeText(getContext(),"Por favor seleccione una imagen del producto",Toast.LENGTH_SHORT).show();
        }else {


            Log.e("Datos ", numero);
            progressDialog.show();
            progressDialog.setTitle("Subiendo...");
            progressDialog.setMessage("Subiendo Archivo");
            Map<String, Object> map = new HashMap<>();
            map.put("nombreProductos",nom);
            map.put("precioProducto",pre);
            map.put("urlFotoP",url);
            mDataBase.child("Productos").child("Producto"+ numero).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        nombre.setText("");
                        precio.setText("");
                        addImg.setImageResource(R.drawable.ic_add_imagen);
                        progressDialog.dismiss();
                        Log.e("Datos ", numero);
                    }else {
                        Toast.makeText(getContext(),"Hubo un error al intentar subir los datos",Toast.LENGTH_LONG).show();
                    }

                }
            });
/*

           */
        }
    }
}