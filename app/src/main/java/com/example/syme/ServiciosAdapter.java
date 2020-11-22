package com.example.syme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServiciosViewHolder> {
    List<Servicios> listaServicios;

    public ServiciosAdapter(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    @NonNull
    @Override
    public ServiciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_servicios, parent, false);
        return new ServiciosAdapter.ServiciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiciosViewHolder holder, int position) {
        Servicios servicio = listaServicios.get(position);
        holder.desServicios.setText(servicio.getDesServicio());
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ServiciosViewHolder extends RecyclerView.ViewHolder {
        TextView desServicios;
        public ServiciosViewHolder(@NonNull View itemView) {
            super(itemView);
            desServicios = itemView.findViewById(R.id.desServicio);
        }
    }
}
