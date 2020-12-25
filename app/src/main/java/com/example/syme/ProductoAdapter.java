package com.example.syme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    ArrayList<Producto> productos;
    Context context;

    public ProductoAdapter(Context context, ArrayList<Producto> productos) {
        this.productos = productos;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_productos, parent, false);
        ProductoViewHolder holder = new ProductoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombreP.setText(producto.getNombreProductos());
        holder.precioP.setText(producto.getPrecioProducto());
        Glide.with(context).load(producto.getUrlFotoP()).into(holder.fotoP);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreP, precioP;
        ImageView fotoP;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreP = itemView.findViewById(R.id.nombreProducto);
            precioP = itemView.findViewById(R.id.precioProducto);
            fotoP = itemView.findViewById(R.id.fotoProducto);

        }
    }
    public void setFilter(ArrayList<Producto> productos){
        this.productos=new ArrayList<>();
        this.productos.addAll(productos);
        notifyDataSetChanged();
    }
}
