package com.example.eventnow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventnow.R;
import com.example.eventnow.models.eventosEmpresa;

import java.util.ArrayList;

public class AdapterEmpresaEventosTotal extends RecyclerView.Adapter<AdapterEmpresaEventosTotal.ViewHolder> {
    ArrayList<eventosEmpresa> eventos=new ArrayList<>();
    public void dataChange(ArrayList<eventosEmpresa> eventos){
        this.eventos=eventos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterEmpresaEventosTotal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclereventosvigentes, parent, false);
        return new AdapterEmpresaEventosTotal.ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEmpresaEventosTotal.ViewHolder holder, int position) {
        holder.nameOfEvent.setText(eventos.get(position).getEvento());
        holder.eventDate.setText(eventos.get(position).getFecha());
        holder.eventHoursInit.setText(eventos.get(position).getHoraInicio());
        holder.eventStatus.setText(eventos.get(position).isEstado()? "Aceptado":"Pendiente");
        holder.eventHoursFinish.setText(eventos.get(position).getHoraFinal());
        holder.task.setText(eventos.get(position).getTarea());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameOfEvent,eventStatus,eventDate,task;
        public Button eventHoursInit,eventHoursFinish;
        public CardView cardViewEventCreated;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfEvent=itemView.findViewById(R.id.nameOfEvent);
            eventDate=itemView.findViewById(R.id.eventDate);
            eventHoursInit=itemView.findViewById(R.id.eventHoursInit);
            eventStatus=itemView.findViewById(R.id.eventStatus);
            eventHoursFinish=itemView.findViewById(R.id.eventHoursFinish);
            task=itemView.findViewById(R.id.task);
            cardViewEventCreated=itemView.findViewById(R.id.cardViewEventCreated);
        }
    }
}
