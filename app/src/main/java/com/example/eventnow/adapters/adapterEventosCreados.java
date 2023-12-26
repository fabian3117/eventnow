package com.example.eventnow.adapters;

import static android.content.Context.MODE_PRIVATE;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventnow.EmpresaMain;
import com.example.eventnow.MainActivity;
import com.example.eventnow.R;
import com.example.eventnow.models.EventoPasadoCreado;
import com.example.eventnow.models.eventosEmpresa;
import com.example.eventnow.utils.General;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.checkerframework.common.subtyping.qual.Bottom;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class adapterEventosCreados extends RecyclerView.Adapter<adapterEventosCreados.ViewHolder> {
    //todo adapter utilizado para los eventos que creo la empresa falta redireccionamientos y cargafirebase <--
    BottomSheetDialog sideSheetDialog;
    ArrayList<eventosEmpresa> eventos=new ArrayList<>();

    public adapterEventosCreados() {
        eventos=new ArrayList<>();
    }

    public adapterEventosCreados(ArrayList<eventosEmpresa> eventos) {
        this.eventos = eventos;
    }
    public void dataChange(ArrayList<eventosEmpresa> eventos){
        this.eventos=eventos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public adapterEventosCreados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclereventosvigentes, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEventosCreados.ViewHolder holder, int position) {

        holder.nameOfEvent.setText(eventos.get(position).getEvento());
        holder.eventDate.setText(eventos.get(position).getFecha());
        holder.eventHoursInit.setText(eventos.get(position).getHoraInicio());
        holder.eventStatus.setText(eventos.get(position).getNombre());
        holder.eventHoursFinish.setText(eventos.get(position).getHoraFinal());
        holder.task.setText(eventos.get(position).getTarea());
        //todo al tocar el carview abro un BootomSheetDialog con los datos del evento y mapa    <--
        holder.cardViewEventCreated.setOnClickListener(v->{
            LayoutInflater inflater = LayoutInflater.from(v.getContext());
            @SuppressLint("InflateParams") View bottomSheetView = inflater.inflate(R.layout.vista_evento_button, null);
            sideSheetDialog = new BottomSheetDialog(v.getContext());
            sideSheetDialog.setContentView(bottomSheetView);
            MapView map=sideSheetDialog.findViewById(R.id.map);
            Configuration.getInstance().load(v.getContext(), v.getContext().getSharedPreferences("OSM", MODE_PRIVATE));
            map.setBuiltInZoomControls(true);
            IMapController ControladorMapa=map.getController();
            GeoPoint startPoint = new GeoPoint(eventos.get(position).getLatitud(),eventos.get(position).getLongitud());
            Geocoder test=new Geocoder(v.getContext());
            GeoPoint punto = new GeoPoint(eventos.get(position).getLatitud(), eventos.get(position).getLongitud());
            Marker marker=new Marker(map);
            marker.setPosition(punto);
            marker.setIcon(ResourcesCompat.getDrawable(v.getResources(), R.drawable.point, null));
          //  marker.setIcon(R.drawable.point);
           // marker.setTitle("dddd");
           // marker.showInfoWindow();
            map.getOverlays().add(marker);
            map.invalidate();
//            map.getOverlays().add(mOverlay);
            try {
                List<Address> as=test.getFromLocation(eventos.get(position).getLatitud(),eventos.get(position).getLongitud(),1);
                Log.e("MIRA",as.get(0).getAddressLine(0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ControladorMapa.setZoom(General.zoomMapDefault);
            ControladorMapa.setCenter(startPoint);
            map.setMultiTouchControls(true);
            sideSheetDialog.show();

        });
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
