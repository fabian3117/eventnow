package com.example.eventnow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventnow.R;
import com.example.eventnow.models.Eventoso;

import java.util.ArrayList;

public class adapterEventosAceptados extends RecyclerView.Adapter<adapterEventosAceptados.ViewHolder>{
    ArrayList<Eventoso> eventos=new ArrayList<>();


    public adapterEventosAceptados(ArrayList<Eventoso> eventos) {
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclereventosvigentes, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
