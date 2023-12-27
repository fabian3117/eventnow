package com.example.eventnow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventnow.R;
import com.example.eventnow.adapters.AdapterEmpresaEventosTotal;

public class todosMisEventosEmpresa extends Fragment {

    RecyclerView recyclerViewEmpresaEventosCreados;
    AdapterEmpresaEventosTotal adapterEmpresaEventosTotal=new AdapterEmpresaEventosTotal();
    public todosMisEventosEmpresa() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_todos_mis_eventos_empresa, container, false);
        recyclerViewEmpresaEventosCreados=v.findViewById(R.id.recyclerViewEmpresaEventosCreados);
        recyclerViewEmpresaEventosCreados.setAdapter(adapterEmpresaEventosTotal);
        recyclerViewEmpresaEventosCreados.setLayoutManager(new LinearLayoutManager(getContext()));
    return v;
    }
}