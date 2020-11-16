package com.example.syme.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.syme.MantenimientoFragment;
import com.example.syme.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private Button btnmantenimiento;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        btnmantenimiento = root.findViewById(R.id.mantenimiento);
        btnmantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MantenimientoFragment mantenimientoFragment = new MantenimientoFragment();
                fragmentTransaction.replace(R.id.contenedor,mantenimientoFragment).addToBackStack(null).commit();
            }
        });
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}