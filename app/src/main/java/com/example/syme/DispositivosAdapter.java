package com.example.syme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DispositivosAdapter extends RecyclerView.Adapter<DispositivosAdapter.ViewHolderDispositivos> {
    List<Dispositivos> dispositivos;
    Context context;
    public DispositivosAdapter( Context context , List<Dispositivos> dispositivos) {
        this.dispositivos = dispositivos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDispositivos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_dispositivos, parent, false);
        ViewHolderDispositivos holder = new ViewHolderDispositivos(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDispositivos holder, int position) {
        Dispositivos dispositivo = dispositivos.get(position);
        holder.tipoD.setText(dispositivo.getTipo());
        holder.estadoD.setText(dispositivo.getEstado());
        Log.e("Tipo de equipo ", ""+dispositivo.getTipo());
        if (dispositivo.getTipo().equalsIgnoreCase("laptop")){
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/syme-cdbee.appspot.com/o/iconos%2Fordenador-portatil.png?alt=media&token=96946986-f057-4c10-852d-de98a82f3a56").into(holder.fotoD);
        }else if (dispositivo.getTipo().equalsIgnoreCase("celular")){
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/syme-cdbee.appspot.com/o/iconos%2Fcelular.png?alt=media&token=d6503c43-75bb-4675-9fe3-0e0bf2587619").into(holder.fotoD);
        }

    }

    @Override
    public int getItemCount() {
        return dispositivos.size();
    }

    public class ViewHolderDispositivos extends RecyclerView.ViewHolder {
        TextView tipoD,estadoD;
        ImageView fotoD;
        public ViewHolderDispositivos(@NonNull View itemView) {
            super(itemView);
            tipoD = itemView.findViewById(R.id.tipoDispositivo);
            estadoD = itemView.findViewById(R.id.estadoDispositivo);
            fotoD = itemView.findViewById(R.id.fotoDispositivo);
        }
    }
}
