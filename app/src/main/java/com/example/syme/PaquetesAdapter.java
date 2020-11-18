package com.example.syme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaquetesAdapter extends RecyclerView.Adapter<PaquetesAdapter.ViewHolderPaquetes> {
    ArrayList<Paquetes> listaPaquetes;

    public PaquetesAdapter(ArrayList<Paquetes> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
    }

    @NonNull
    @Override
    public ViewHolderPaquetes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_paquetes, parent, false);
        return new ViewHolderPaquetes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaquetes holder, int position) {
        holder.numeroP.setText(listaPaquetes.get(position).getNumPaquete());
        holder.tituloP.setText(listaPaquetes.get(position).getTitPaquete());
        holder.descripcionP.setText(listaPaquetes.get(position).getDesPaquete());

    }

    @Override
    public int getItemCount() {
        return listaPaquetes.size();
    }

    public class ViewHolderPaquetes extends RecyclerView.ViewHolder {
        TextView numeroP,tituloP,descripcionP;
        public ViewHolderPaquetes(@NonNull View itemView) {
            super(itemView);
            numeroP = itemView.findViewById(R.id.numPaquete);
            tituloP = itemView.findViewById(R.id.titPaquete);
            descripcionP = itemView.findViewById(R.id.desPaquete);
        }
    }
}
