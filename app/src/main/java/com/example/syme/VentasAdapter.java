package com.example.syme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.ViewHolderVentas> {
    ArrayList<VentasP> listaVentas;

    public VentasAdapter(ArrayList<VentasP> listaVentas) {
        this.listaVentas = listaVentas;
    }

    @NonNull
    @Override
    public ViewHolderVentas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ventas, parent, false);
        return new ViewHolderVentas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVentas holder, int position) {
        VentasP venta=listaVentas.get(position);
        holder.categoria.setText(venta.getCategoria());
        holder.descripcion.setText(venta.getDescripcionV());
        if (venta.getCategoria().equalsIgnoreCase("")){
            holder.categoria.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (listaVentas==null){
            return 0;
        }else {
            return listaVentas.size();
        }
    }

    public class ViewHolderVentas extends RecyclerView.ViewHolder {
        TextView categoria,descripcion;
        public ViewHolderVentas(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.categoriaVentas);
            descripcion = itemView.findViewById(R.id.descripcionVentas);
        }
    }
}
